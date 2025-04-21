package com.yolo.controller.patient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * PatientEditProfileController is responsible for handling patient profile editing operations.
 * It manages the display and processing of the patient profile edit page, allowing patients
 * to view and update their personal information, contact details, and preferences.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/patient-edit-profile"})
public class PatientEditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Constructor for PatientEditProfileController.
     * Initializes the servlet by calling the parent constructor.
     */
    public PatientEditProfileController() {
        super();
        // No additional initialization required
    }

	/**
	 * Handles GET requests to the patient profile edit page.
	 * Forwards the request to the patient_edit_profile.jsp view which displays
	 * the form for editing patient profile information.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/patient/patient_edit_profile.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests for patient profile updates.
	 * Currently delegates to doGet method, but should be extended to process 
	 * profile form submissions, validate input data, and update the patient record.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Currently forwards to the same view as doGet
		// This should be enhanced to process and validate profile updates
		// and redirect to a confirmation page or back to the dashboard
		doGet(request, response);
	}
}