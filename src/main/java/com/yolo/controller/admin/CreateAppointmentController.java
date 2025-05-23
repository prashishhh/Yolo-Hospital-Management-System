package com.yolo.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.yolo.config.DbConfig;
import com.yolo.model.AppointmentModel;
import com.yolo.model.DoctorModel;
import com.yolo.model.RoomModel;
import com.yolo.service.admin.CreateAppointmentService;
import com.yolo.util.ValidationUtil;

/**
 * Servlet implementation class CreateAppointmentController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/create-appointment" })
public class CreateAppointmentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CreateAppointmentService createAppointmentService = new CreateAppointmentService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAppointmentController() {
        super();
    }

    /**
     * Handles GET requests to display the create appointment form with dynamic doctor and room lists.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Fetch doctors and rooms from the database
            List<DoctorModel> doctors = getDoctors();
            List<RoomModel> rooms = getRooms();

            // Set attributes for JSP
            request.setAttribute("doctors", doctors);
            request.setAttribute("rooms", rooms);

            request.getRequestDispatcher("/WEB-INF/pages/admin/create_appointment.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            handleError(request, response, "Error loading doctors or rooms. Please try again later!");
            e.printStackTrace();
        }
    }

    /**
     * Handles POST requests for creating an appointment slot. Validates input and saves the appointment details.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Validate form input
            String validationMessage = validateAppointmentForm(request);
            if (validationMessage != null) {
                handleError(request, response, validationMessage);
                return;
            }

            // Extract AppointmentModel from request
            AppointmentModel appointmentModel = extractAppointmentModel(request);

            // Attempt to create appointment
            Boolean isCreated = createAppointmentService.createAppointment(appointmentModel);

            if (isCreated == null) {
                handleError(request, response, "Server is under maintenance. Please try again later!");
            } else if (isCreated) {
                handleSuccess(request, response, "Appointment slot successfully created!", "/WEB-INF/pages/admin/admin_appointment_list.jsp");
            } else {
                handleError(request, response, "Could not create appointment slot. Please try again later!");
            }
        } catch (Exception e) {
            handleError(request, response, "An unexpected error occurred. Please try again later!");
            e.printStackTrace();
        }
    }

    /**
     * Validates the appointment form input.
     */
    private String validateAppointmentForm(HttpServletRequest request) {
        String appointmentDate = request.getParameter("appointmentDate");
        String appointmentStartTime = request.getParameter("appointmentStartTime");
        String appointmentEndTime = request.getParameter("appointmentEndTime");
        String appointmentFee = request.getParameter("appointmentFee");
        String doctorID = request.getParameter("doctorID");
        String roomID = request.getParameter("roomID");

        // Check for null or empty fields
        if (ValidationUtil.isNullOrEmpty(appointmentDate))
            return "Appointment date is required.";
        if (ValidationUtil.isNullOrEmpty(appointmentStartTime))
            return "Start time is required.";
        if (ValidationUtil.isNullOrEmpty(appointmentEndTime))
            return "End time is required.";
        if (ValidationUtil.isNullOrEmpty(appointmentFee))
            return "Appointment fee is required.";
        if (ValidationUtil.isNullOrEmpty(doctorID))
            return "Doctor selection is required.";
        if (ValidationUtil.isNullOrEmpty(roomID))
            return "Room selection is required.";

        // Validate date
        LocalDate date;
        try {
            date = LocalDate.parse(appointmentDate);
            if (date.isBefore(LocalDate.now()))
                return "Appointment date cannot be in the past.";
        } catch (Exception e) {
            return "Invalid date format. Please use YYYY-MM-DD.";
        }

        // Validate times
        try {
            LocalTime startTime = LocalTime.parse(appointmentStartTime);
            LocalTime endTime = LocalTime.parse(appointmentEndTime);
            if (!startTime.isBefore(endTime))
                return "End time must be after start time.";
            if (startTime.isBefore(LocalTime.of(10, 0)) || startTime.isAfter(LocalTime.of(16, 30)))
                return "Start time must be between 10:00 and 16:30.";
            if (endTime.isBefore(LocalTime.of(10, 30)) || endTime.isAfter(LocalTime.of(17, 0)))
                return "End time must be between 10:30 and 17:00.";
        } catch (Exception e) {
            return "Invalid time format. Please use HH:MM.";
        }

        // Validate fee
        try {
            int fee = Integer.parseInt(appointmentFee);
            if (fee <= 0)
                return "Appointment fee must be a positive number.";
        } catch (NumberFormatException e) {
            return "Appointment fee must be a valid number.";
        }

        // Validate doctorID and roomID
        try {
            Integer.parseInt(doctorID);
            Integer.parseInt(roomID);
        } catch (NumberFormatException e) {
            return "Invalid doctor or room selection.";
        }

        return null; // All validations passed
    }

    /**
     * Extracts appointment details from the request and creates an AppointmentModel.
     */
    private AppointmentModel extractAppointmentModel(HttpServletRequest request) {
        String appointmentStatus = "Available"; // Default status for new appointment slots
        LocalDate appointmentDate = LocalDate.parse(request.getParameter("appointmentDate"));
        LocalTime appointmentStartTime = LocalTime.parse(request.getParameter("appointmentStartTime"));
        LocalTime appointmentEndTime = LocalTime.parse(request.getParameter("appointmentEndTime"));
        int appointmentFee = Integer.parseInt(request.getParameter("appointmentFee"));
        int doctorID = Integer.parseInt(request.getParameter("doctorID"));
        int roomID = Integer.parseInt(request.getParameter("roomID"));

        // Create DoctorModel and RoomModel with minimal required fields
        DoctorModel doctor = new DoctorModel();
        doctor.setDoctorID(doctorID);
        RoomModel room = new RoomModel();
        room.setRoomID(roomID);

        // Create and return AppointmentModel
        return new AppointmentModel(appointmentStatus, appointmentDate, appointmentStartTime, appointmentEndTime,
                appointmentFee, doctor, room, null, null);
    }

    /**
     * Fetches list of doctors from the database.
     */
    private List<DoctorModel> getDoctors() throws SQLException, ClassNotFoundException {
        List<DoctorModel> doctors = new ArrayList<>();
        String query = "SELECT doctorID, doctorFirstName, doctorLastName, doctorSpecialization FROM Doctor";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                doctors.add(new DoctorModel(
                        rs.getInt("doctorID"),
                        rs.getString("doctorFirstName"),
                        rs.getString("doctorLastName"),
                        rs.getString("doctorSpecialization"),
                        null
                ));
            }
        }
        return doctors;
    }

    /**
     * Fetches list of rooms from the database.
     */
    private List<RoomModel> getRooms() throws SQLException, ClassNotFoundException {
        List<RoomModel> rooms = new ArrayList<>();
        String query = "SELECT roomID, roomName, roomType FROM Room";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                rooms.add(new RoomModel(
                        rs.getInt("roomID"),
                        rs.getString("roomName"),
                        rs.getString("roomType")
                ));
            }
        }
        return rooms;
    }

    /**
     * Handles successful appointment creation.
     */
    private void handleSuccess(HttpServletRequest request, HttpServletResponse response, String message, String redirectPage)
            throws ServletException, IOException {
        request.setAttribute("success", message);
        request.getRequestDispatcher(redirectPage).forward(request, response);
    }

    /**
     * Handles errors during appointment creation.
     */
    private void handleError(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute("error", message);
        request.setAttribute("appointmentDate", request.getParameter("appointmentDate"));
        request.setAttribute("appointmentStartTime", request.getParameter("appointmentStartTime"));
        request.setAttribute("appointmentEndTime", request.getParameter("appointmentEndTime"));
        request.setAttribute("appointmentFee", request.getParameter("appointmentFee"));
        request.setAttribute("doctorID", request.getParameter("doctorID"));
        request.setAttribute("roomID", request.getParameter("roomID"));
        // Reload doctors and rooms
        try {
            request.setAttribute("doctors", getDoctors());
            request.setAttribute("rooms", getRooms());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/pages/admin/create_appointment.jsp").forward(request, response);
    }
}