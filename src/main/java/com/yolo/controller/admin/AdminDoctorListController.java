package com.yolo.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.yolo.model.DoctorModel;
import com.yolo.model.UserModel;
import com.yolo.service.admin.AdminDoctorListService;
import com.yolo.util.SessionUtil;

/**
 * Servlet implementation class AdminDoctorListController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-doctor-list" })
public class AdminDoctorListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminDoctorListService adminDoctorListService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDoctorListController() {
        
    	this.adminDoctorListService = new AdminDoctorListService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the search query from the request
        String searchQuery = request.getParameter("search");
		
		request.setAttribute("doctorInfoList", adminDoctorListService.getDoctorListDetails(searchQuery)); // Get doctor info
		
		request.getRequestDispatcher("/WEB-INF/pages/admin/admin_doctor_list.jsp").forward(request, response);
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
	    
	    switch (action) {
	        case "editDoctor":
	            Integer doctorID = Integer.parseInt(request.getParameter("doctorID"));
	            DoctorModel doctor = adminDoctorListService.getDoctorDetails(doctorID);
	            if (doctor != null) {
	                SessionUtil.setAttribute(request, "doctor", doctor);
	                response.sendRedirect(request.getContextPath() + "/edit-doctor");
	                return; // Exit after redirect
	            } 
	            break;
	            
	        case "deleteDoctor":
                // Call doDelete method to handle the doctor deletion
                doDelete(request, response);
                return; // Exit after deletion
                
	        default:
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
	            return; // Exit after sending error
	    }
	    
	    // call doGet if no redirect or error was sent
	    doGet(request, response);
	}
	
	/**
     * Handles DELETE requests to remove a doctor profile
     */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get doctorID from request parameters
        String doctorIDParam = request.getParameter("doctorID");
        if (doctorIDParam == null || doctorIDParam.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Doctor ID is missing");
            return;
        }
        
        try {
            Integer doctorID = Integer.parseInt(doctorIDParam);
            adminDoctorListService.deleteDoctorProfile(doctorID);
            response.sendRedirect(request.getContextPath() + "/admin-doctor-list?success=Doctor+deleted+successfully");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Doctor ID format");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete doctor profile");
        }
    }

}
