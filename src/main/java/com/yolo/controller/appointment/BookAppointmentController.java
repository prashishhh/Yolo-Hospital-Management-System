package com.yolo.controller.appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class BookAppointmentController.
 * This controller is responsible for displaying and processing the appointment booking page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/book-appointment" })
public class BookAppointmentController extends HttpServlet {
    // Serial version UID for version control of the serialized class
	private static final long serialVersionUID = 1L;
       
    /**
     * Constructor for BookAppointmentController.
     * Initializes the servlet. This is invoked when an instance of the servlet is created.
     */
    public BookAppointmentController() {
        super();
        // Constructor code can be added if needed in the future
    }

    /**
     * Handles GET requests to display the appointment booking page.
     * Forwards the request to the book_appointment.jsp page where users can book appointments.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forwarding the request to the book_appointment.jsp page
		request.getRequestDispatcher("/WEB-INF/pages/appointment/book_appointment.jsp").forward(request, response);
	}

    /**
     * Handles POST requests for appointment booking.
     * This method does not process POST-specific logic but forwards to the doGet method.
     * This is to allow the page to be reloaded after the POST action.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forwarding POST requests to the GET method as no specific actions are needed for this controller
		doGet(request, response);
	}
}
