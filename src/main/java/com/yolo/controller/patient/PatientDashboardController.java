package com.yolo.controller.patient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * PatientDashboardController is responsible for handling patient dashboard requests.
 * It manages the display and processing of the patient dashboard page, which provides
 * patients with access to their personal information and medical services.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/patient-dashboard"})
public class PatientDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Constructor for PatientDashboardController.
     * Initializes the servlet by calling the parent constructor.
     */
    public PatientDashboardController() {
        super();
        // No additional initialization required
    }

	/**
	 * Handles GET requests to the patient dashboard page.
	 * Forwards the request to the patient_dashboard.jsp view which displays
	 * the patient's information, appointments, and available services.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/patient/patient_dashboard.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests for patient dashboard interactions.
	 * Currently delegates to doGet method, but can be extended to process 
	 * dashboard form submissions or AJAX requests.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Currently forwards to the same view as doGet
		// This can be enhanced to process dashboard interaction data
		doGet(request, response);
	}
}