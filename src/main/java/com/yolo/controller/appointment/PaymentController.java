package com.yolo.controller.appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.yolo.model.AppointmentModel;
import com.yolo.model.UserModel;
import com.yolo.service.appointment.PaymentService;
import com.yolo.util.SessionUtil;

/**
 * Servlet implementation class PaymentController
 * Handles HTTP requests related to the payment process.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/payment" })
public class PaymentController extends HttpServlet {
    // Serial version UID for version control of the serialized class
    private static final long serialVersionUID = 1L;
    
    private PaymentService paymentService;

    /**
     * Default constructor.
     * Initializes the servlet.
     */
    public PaymentController() {
    	this.paymentService = new PaymentService();
    }

    /**
     * Handles GET requests.
     * Redirects the request to the payment page (payment.jsp).
     * 
     * @param request The HttpServletRequest object that contains the request
     * @param response The HttpServletResponse object that contains the response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException If an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Retrieve the selected appointment from the session
        AppointmentModel selectedAppointment = (AppointmentModel) SessionUtil.getAttribute(request, "selectedAppointment");
        
        if (selectedAppointment == null) {
        	response.sendRedirect(request.getContextPath() + "/book-appointment");
            return;
        }
        
        // Set the appointment as a request attribute for the JSP
        request.setAttribute("selectedAppointment", selectedAppointment);
        
        request.getRequestDispatcher("/WEB-INF/pages/appointment/payment.jsp").forward(request, response);
    }

    /**
     * Handles POST requests.
     * Calls the doGet method for processing POST requests similarly.
     * 
     * @param request The HttpServletRequest object that contains the request
     * @param response The HttpServletResponse object that contains the response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException If an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("value"); // Get the button value ("pay" or "cancel")
        Integer userID = (Integer) SessionUtil.getAttribute(request, "userID");
        AppointmentModel selectedAppointment = (AppointmentModel) SessionUtil.getAttribute(request, "selectedAppointment");

        // Validate session data
        if (userID == null || selectedAppointment == null) {
            request.setAttribute("error", "Invalid session data. Please try again.");
            request.getRequestDispatcher("/WEB-INF/pages/appointment/payment.jsp").forward(request, response);
            return;
        }

        try {
            if ("pay".equals(action)) {
                // Process payment
                int appointmentID = selectedAppointment.getAppointmentID();
                float amount = selectedAppointment.getAppointmentFee();

                // Create payment record
                int paymentID = paymentService.createPayment(appointmentID, amount);

                // Update appointment status to "Booked" and link payment
                boolean success = paymentService.updateAppointmentStatusAndPayment(appointmentID, paymentID, "Booked");

                if (success) {
                    // Clear session attributes
                    SessionUtil.removeAttribute(request, "selectedAppointment");
                    SessionUtil.removeAttribute(request, "selectedDoctorID");
                    // Redirect to a confirmation page (you may need to create this page)
                    response.sendRedirect(request.getContextPath() + "/patient-dashboard");
                } else {
                    request.setAttribute("error", "Failed to complete payment. Please try again.");
                    request.getRequestDispatcher("/WEB-INF/pages/appointment/payment.jsp").forward(request, response);
                }
            } else if ("cancel".equals(action)) {
                // Cancel booking
                int appointmentID = selectedAppointment.getAppointmentID();
                boolean success = paymentService.cancelBooking(appointmentID, userID);

                if (success) {
                    // Clear session attributes
                    SessionUtil.removeAttribute(request, "selectedAppointment");
                    // Redirect back to book appointment slot page
                    response.sendRedirect(request.getContextPath() + "/book-appointment");
                } else {
                    request.setAttribute("error", "Failed to cancel booking. Please try again.");
                    request.getRequestDispatcher("/WEB-INF/pages/appointment/payment.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Invalid action.");
                request.getRequestDispatcher("/WEB-INF/pages/appointment/payment.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error occurred. Please try again later.");
            request.getRequestDispatcher("/WEB-INF/pages/appointment/payment.jsp").forward(request, response);
        }
    }
}
