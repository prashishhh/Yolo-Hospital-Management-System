package com.yolo.controller.patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.yolo.service.patient.PatientTreatmentService;
import com.yolo.util.SessionUtil;

/**
 * PatientTreatmentController is responsible for handling requests 
 * related to a patient's treatment view. 
 * It forwards the request to the JSP page displaying the treatment details.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/patient-treatment" })
public class PatientTreatmentController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private PatientTreatmentService patientTreatmentService;

	/**
	 * Default constructor for PatientTreatmentController.
	 */
	public PatientTreatmentController() {
		this.patientTreatmentService = new PatientTreatmentService();
	}
	/**
	 * Handles GET requests to display the patient treatment page.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer userID = (Integer) SessionUtil.getAttribute(request, "userID");
		// Get the search query from the request
        String searchQuery = request.getParameter("search");
        
        // Get treatment details for the logged-in patient
        request.setAttribute("treatmentDetailsList", patientTreatmentService.getPatientTreatmentDetails(userID, searchQuery));
        
		request.getRequestDispatcher("/WEB-INF/pages/patient/patient_treatment.jsp").forward(request, response);
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
