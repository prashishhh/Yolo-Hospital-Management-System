package com.yolo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class OurDoctorsController.
 * This controller is responsible for handling requests to display the "Our Doctors" page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/our-doctors" })
public class OurDoctorsController extends HttpServlet {
    // Serial version UID for version control of the serialized class
	private static final long serialVersionUID = 1L;
       
    /**
     * Constructor for OurDoctorsController.
     * Initializes the servlet. The constructor is called when an instance of the servlet is created.
     */
    public OurDoctorsController() {
        super();
        // Constructor code can be added if needed in the future
    }

    /**
     * Handles GET requests to display the "Our Doctors" page.
     * Forwards the request to the our_doctors.jsp page, where the list of doctors is displayed.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forwarding the request to the our_doctors.jsp page
		request.getRequestDispatcher("/WEB-INF/pages/our_doctors.jsp").forward(request, response);
	}

    /**
     * Handles POST requests to forward to the GET method.
     * Since the "Our Doctors" page does not need to process POST requests, it simply forwards the request to the doGet method.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forwarding POST requests to the GET method as no POST processing is needed
		doGet(request, response);
	}
}
