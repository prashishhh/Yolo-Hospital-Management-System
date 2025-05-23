package com.yolo.controller.patient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.yolo.service.patient.PatientDashboardService;
import com.yolo.util.SessionUtil;

/**
 * PatientDashboardController is responsible for handling patient dashboard requests.
 * It manages the display and processing of the patient dashboard page, which provides
 * patients with access to their personal information and medical services.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/patient-dashboard" })
public class PatientDashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Instance of PatientDashboardService for handling business logic
    private PatientDashboardService patientDashboardService;

    /**
     * Constructor for PatientDashboardController.
     * Initializes the servlet and the PatientDashboardService.
     */
    public PatientDashboardController() {
        super();
        this.patientDashboardService = new PatientDashboardService();
    }

    /**
     * Handles GET requests to the patient dashboard page.
     * Retrieves patient-specific data and forwards the request to the patient_dashboard.jsp view.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	// Get userID from session
        Integer userID = (Integer) SessionUtil.getAttribute(request, "userID");

        if (userID == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Retrieve data from the PatientDashboardService
        request.setAttribute("appointmentList", patientDashboardService.getRecentAppointments(userID));
        request.setAttribute("treatmentList", patientDashboardService.getRecentTreatments(userID));
        request.setAttribute("totalAppointments", patientDashboardService.getTotalAppointments(userID));
        request.setAttribute("totalTreatments", patientDashboardService.getTotalTreatments(userID));

        // Forward to the patient dashboard JSP
        request.getRequestDispatcher("/WEB-INF/pages/patient/patient_dashboard.jsp").forward(request, response);
    }

    /**
     * Handles POST requests for patient dashboard interactions.
     * Delegates to doGet method for now, but can be extended for form submissions.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}