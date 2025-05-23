package com.yolo.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.yolo.controller.patient.PatientProfileController;
import com.yolo.service.admin.AdminPatientListService;

/**
 * Servlet implementation class AdminPatientListController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-patient-list" })
public class AdminPatientListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminPatientListService adminPatientListService;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPatientListController() {
    	
    	this.adminPatientListService = new AdminPatientListService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the search query from the request
        String searchQuery = request.getParameter("search");
        
        request.setAttribute("patientInfoList", adminPatientListService.getPatientListDetails(searchQuery)); // Get Patient info
		
		request.getRequestDispatcher("/WEB-INF/pages/admin/admin_patient_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");        
        
	    if (action == null || action.trim().isEmpty()) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
	        return;
	    }
	    
	    switch(action) {
	    case "deletePatient":
            // Call doDelete method to handle the patient deletion
            doDelete(request, response);
            return; // Exit after deletion
            
        default:
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
            return; // Exit after sending error
	    }
	}
	
	/**
     * Handles DELETE requests to remove a patient profile
     */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get userID from request parameters
        String userIDParam = request.getParameter("userID");
        if (userIDParam == null || userIDParam.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is missing");
            return;
        }
        
        try {
            Integer userID = Integer.parseInt(userIDParam);
            adminPatientListService.deletePatientProfile(userID);
            response.sendRedirect(request.getContextPath() + "/admin-patient-list?success=Patient+deleted+successfully");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid User ID format");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete patient profile");
        }
    }

}
