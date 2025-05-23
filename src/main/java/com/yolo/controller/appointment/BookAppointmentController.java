package com.yolo.controller.appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.yolo.model.DoctorModel;
import com.yolo.service.appointment.BookAppointmentService;
import com.yolo.util.SessionUtil;

/**
 * Servlet Controller for handling the appointment booking page.
 * 
 * URL mapping: /book-appointment
 * 
 * Responsibilities:
 * - Ensures the user is authenticated before accessing the booking page.
 * - Fetches a list of available doctors using the BookAppointmentService.
 * - Forwards the data to the JSP view for rendering.
 * 
 * Access Control:
 * - Redirects to the login page if the user session is not found.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/book-appointment" })
public class BookAppointmentController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BookAppointmentService bookAppointmentService;

    /**
     * Default constructor initializes the appointment service.
     */
    public BookAppointmentController() {
        this.bookAppointmentService = new BookAppointmentService();
    }

    /**
     * Handles GET requests to display the appointment booking form.
     * 
     * Workflow:
     * - Check if user is logged in (session contains 'userID').
     * - Fetch list of doctors from the database via service layer.
     * - Set doctor list as a request attribute for use in the JSP.
     * - Handle exceptions gracefully and show an error message if needed.
     * 
     * @param request  HttpServletRequest object for client request
     * @param response HttpServletResponse object for sending response
     * @throws ServletException in case of a servlet-specific error
     * @throws IOException      in case of input/output error
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userID = (Integer) SessionUtil.getAttribute(request, "userID");

        // Redirect to login if session is not found
        if (userID == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            request.setAttribute("doctors", bookAppointmentService.getAllDoctors());
        } catch (SQLException e) {
            // Log and handle database-related exceptions
            e.printStackTrace();
            request.setAttribute("error", "Failed to load doctors. Please try again later.");
        }

        // Forward to the booking form JSP
        request.getRequestDispatcher("/WEB-INF/pages/appointment/book_appointment.jsp").forward(request, response);
    }
    
    /**
     * Handles POST requests from the book appointment form.
     * 
     * Workflow:
     * - Retrieves the doctorID from the form submission.
     * - Stores the doctorID in the session.
     * - Redirects to the book appointment slot page.
     * 
     * @param request  HttpServletRequest object for client request
     * @param response HttpServletResponse object for sending response
     * @throws ServletException in case of a servlet-specific error
     * @throws IOException      in case of input/output error
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userID = (Integer) SessionUtil.getAttribute(request, "userID");

        // Redirect to login if session is not found
        if (userID == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String doctorIDStr = request.getParameter("doctorID");
        if (doctorIDStr != null && !doctorIDStr.isEmpty()) {
            try {
                int doctorID = Integer.parseInt(doctorIDStr);
                // Store doctorID in session
                SessionUtil.setAttribute(request, "selectedDoctorID", doctorID);
                // Redirect to the slot selection page
                response.sendRedirect(request.getContextPath() + "/book-appointment-slot");
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid Doctor ID.");
                request.getRequestDispatcher("/WEB-INF/pages/appointment/book_appointment.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Doctor ID is missing.");
            request.getRequestDispatcher("/WEB-INF/pages/appointment/book_appointment.jsp").forward(request, response);
        }
    }
}
