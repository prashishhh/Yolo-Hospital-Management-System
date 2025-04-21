package com.yolo.controller.appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class BookAppointmentSlotController.
 * This controller handles the booking of appointment slots.
 * It displays the appointment booking page and processes the booking.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/book-appointment-slot" })
public class BookAppointmentSlotController extends HttpServlet {
    // Serial version UID for version control of the serialized class
	private static final long serialVersionUID = 1L;
       
    /**
     * Constructor for BookAppointmentSlotController.
     * Initializes the servlet. The constructor is called when an instance of the servlet is created.
     */
    public BookAppointmentSlotController() {
        super();
        // Constructor code can be added if needed in the future
    }

    /**
     * Handles GET requests to display the appointment booking page.
     * Forwards the request to the book_appointment_slot.jsp page, where users can select appointment slots.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forwarding the request to the book_appointment_slot.jsp page
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
        // Forwarding POST requests to the GET method as no POST-specific actions are needed for this controller
		doGet(request, response);
	}
}
