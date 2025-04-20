package com.yolo.controller.patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * PatientProfileController is responsible for handling requests 
 * related to displaying a patient's profile information.
 * It forwards the request to the appropriate JSP page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/patient-profile" })
public class PatientProfileController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor for PatientProfileController.
	 */
	public PatientProfileController() {
		super();
	}

	/**
	 * Handles GET requests to display the patient profile page.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/patient/patient_profile.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by redirecting them to the GET handler.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}