package com.yolo.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.yolo.service.admin.AdminDashboardService;

/**
 * Servlet implementation class AdminDashboardController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-dashboard" })
public class AdminDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Instance of AdminDashboardService for handling business logic
	private AdminDashboardService adminDashboardService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDashboardController() {
    	this.adminDashboardService = new AdminDashboardService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrieve data from the DashboardService
        request.setAttribute("patientList", adminDashboardService.getRecentPatients()); // Get recent patients
        
        request.setAttribute("doctorList", adminDashboardService.getRecentDoctors()); // Get recent doctors
        
        request.setAttribute("appointmentList", adminDashboardService.getRecentAppointment()); // Get Recent Appointment
        
		request.setAttribute("totalAppointment", adminDashboardService.getTotalAppointments()); // No of total appointments

		request.setAttribute("totalDoctor", adminDashboardService.getTotalDoctors()); // No of total doctors
		
		request.setAttribute("totalPatient", adminDashboardService.getTotalPatients()); // No of total patient
		
		request.setAttribute("totalIncome", adminDashboardService.getTotalIncome()); // No of total income
		
		request.getRequestDispatcher("/WEB-INF/pages/admin/admin_dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
