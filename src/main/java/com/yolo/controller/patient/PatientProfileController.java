package com.yolo.controller.patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import com.yolo.model.UserModel;
import com.yolo.service.patient.PatientEditProfileService;
import com.yolo.service.patient.PatientProfileService;
import com.yolo.util.SessionUtil;
import com.yolo.util.ValidationUtil;

/**
 * PatientProfileController is responsible for handling requests 
 * related to displaying a patient's profile information.
 * It forwards the request to the appropriate JSP page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/patient-profile" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	maxFileSize = 1024 * 1024 * 10, // 10MB
	maxRequestSize = 1024 * 1024 * 50) // 50MB
public class PatientProfileController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final PatientProfileService patientProfileService;
	
	private final PatientEditProfileService patientEditProfileService;

	/**
	 * Default constructor for PatientProfileController.
	 */
	public PatientProfileController() {
		
		this.patientProfileService = new PatientProfileService();
		this.patientEditProfileService = new PatientEditProfileService();
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
		
		// Get userID from session
        Integer userID = (Integer) SessionUtil.getAttribute(request, "userID");
        
        if (userID == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        request.setAttribute("patientDetails", patientProfileService.getPatientDetails(userID)); // Patient Details
        
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
		
		String action = request.getParameter("action");
		
		// Patient ID from the session
		Integer userID = (Integer) SessionUtil.getAttribute(request, "userID");
		
		if (userID == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
		
		if (action == null || action.trim().isEmpty()) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
	        return;
	    }
		
		switch (action) {
        case "editProfile":
        	
        	// Store current patient in session for pre-populating edit form
            UserModel patient = patientProfileService.getPatientDetails(userID);
            request.getSession().setAttribute("patient", patient);
            
            // Redirect to the edit profile controller
            response.sendRedirect(request.getContextPath() + "/patient-edit-profile");
            break;
            
        case "deleteProfile":
        	// Call doDelete method to handle the profile deletion
            doDelete(request, response);
            break;
        	
        default:
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
		}
	}
	
	/**
	 * Handles DELETE requests to remove a patient profile.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get userID from session
		Integer userID = (Integer) SessionUtil.getAttribute(request, "userID");
		
		if (userID == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in");
			return;
		}
		
		try {
			patientProfileService.deletePatientProfile(userID);
			// Clear session after deletion
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() + "/login?success=Profile+deleted+successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete patient profile");
		}
	}
}
