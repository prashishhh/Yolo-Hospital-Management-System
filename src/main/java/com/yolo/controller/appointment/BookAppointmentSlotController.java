package com.yolo.controller.appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.yolo.model.AppointmentModel;
import com.yolo.model.DoctorModel;
import com.yolo.service.appointment.BookAppointmentSlotService;
import com.yolo.util.SessionUtil;

/**
 * Servlet implementation class BookAppointmentSlotController.
 * This controller handles the booking of appointment slots.
 * It displays the appointment booking page and processes the booking.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/book-appointment-slot" })
public class BookAppointmentSlotController extends HttpServlet {
    // Serial version UID for version control of the serialized class
    private static final long serialVersionUID = 1L;
    
    private BookAppointmentSlotService bookAppointmentSlotService;
       
    /**
     * Constructor for BookAppointmentSlotController.
     * Initializes the servlet. The constructor is called when an instance of the servlet is created.
     */
    public BookAppointmentSlotController() {
        this.bookAppointmentSlotService = new BookAppointmentSlotService();
    }

    /**
     * Handles GET requests to display the appointment slot selection page.
     * 
     * Workflow:
     * - Check if user is logged in (session contains 'userID').
     * - Retrieve doctorID from session.
     * - Fetch doctor's details and available appointments from the service layer.
     * - Set data as request attributes for use in the JSP.
     * - Handle exceptions and show an error message if needed.
     * 
     * @param request  HttpServletRequest object for client request
     * @param response HttpServletResponse object for sending response
     * @throws ServletException in case of a servlet-specific error
     * @throws IOException      in case of input/output error
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer selectedDoctorID = (Integer) SessionUtil.getAttribute(request, "selectedDoctorID");
        String selectedDateStr = request.getParameter("selectedDate");

        if (selectedDoctorID == null) {
            response.sendRedirect(request.getContextPath() + "/book-appointment");
            return;
        }

        try {
            DoctorModel doctor = bookAppointmentSlotService.getDoctorById(selectedDoctorID);
            if (doctor == null) {
                request.setAttribute("error", "Doctor not found.");
            } else {
                // Generate the next 7 days (today + 6 days)
                LocalDate today = LocalDate.now();
                List<LocalDate> nextSevenDays = new ArrayList<>();
                
                for (int i = 0; i < 7; i++) {
                    nextSevenDays.add(today.plusDays(i));
                }
                request.setAttribute("nextSevenDays", nextSevenDays);
                
                // Set the month and year
                String currentMonth = today.format(DateTimeFormatter.ofPattern("MMMM, yyyy"));
                request.setAttribute("currentMonth", currentMonth);
                
                // Set selected date - either from parameter or default to today
                LocalDate selectedDate;
                if (selectedDateStr != null && !selectedDateStr.isEmpty()) {
                    selectedDate = LocalDate.parse(selectedDateStr);
                } else {
                    selectedDate = today;
                }
                request.setAttribute("selectedDate", selectedDate);
                
                // Get all available appointments for the doctor
                List<AppointmentModel> allAppointments = 
                    bookAppointmentSlotService.getAvailableAppointmentsByDoctorId(selectedDoctorID);
                
                if (allAppointments.isEmpty()) {
                    request.setAttribute("message", "No available slots for this doctor.");
                } else {
                    // Filter appointments for the selected date
                    List<AppointmentModel> selectedAppointments = new ArrayList<>();
                    for (AppointmentModel appointment : allAppointments) {
                        if (appointment.getAppointmentDate().equals(selectedDate)) {
                            selectedAppointments.add(appointment);
                        }
                    }
                    
                    request.setAttribute("doctor", doctor);
                    request.setAttribute("selectedAppointments", selectedAppointments);
                    
                    if (!selectedAppointments.isEmpty()) {
                        request.setAttribute("fee", selectedAppointments.get(0).getAppointmentFee());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load data. Please try again later.");
        }

        request.getRequestDispatcher("/WEB-INF/pages/appointment/book_appointment_slot.jsp").forward(request, response);
    }

    /**
     * Handles POST requests for appointment booking.
     * Since the "Book Appointment Slot" page does not need to process POST requests,
     * it simply forwards the request to the doGet method.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userID = (Integer) SessionUtil.getAttribute(request, "userID");
        Integer selectedDoctorID = (Integer) SessionUtil.getAttribute(request, "selectedDoctorID");
        String appointmentIDStr = request.getParameter("appointmentID");

        // Validate inputs
        if (userID == null || selectedDoctorID == null || appointmentIDStr == null || appointmentIDStr.isEmpty()) {
            request.setAttribute("error", "Invalid request. Please try again.");
            doGet(request, response);
            return;
        }

        try {
            int appointmentID = Integer.parseInt(appointmentIDStr);
            // Book the appointment (set status to Pending and link to user)
            boolean success = bookAppointmentSlotService.bookAppointment(appointmentID, userID);
            
            if (success) {
                // Store appointment details in session for payment processing
                AppointmentModel appointment = bookAppointmentSlotService.getAppointmentById(appointmentID);
                if (appointment != null) {
                    SessionUtil.setAttribute(request, "selectedAppointment", appointment);
                    // Redirect to payment page
                    response.sendRedirect(request.getContextPath() + "/payment");
                } else {
                    request.setAttribute("error", "Failed to retrieve appointment details.");
                    doGet(request, response);
                }
            } else {
                request.setAttribute("error", "Failed to book appointment. The slot may no longer be available.");
                doGet(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error occurred. Please try again later.");
            doGet(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid appointment ID.");
            doGet(request, response);
        }
    }
}