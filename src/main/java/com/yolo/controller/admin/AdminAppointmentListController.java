package com.yolo.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.yolo.service.admin.AdminAppointmentListService;
import com.yolo.service.admin.AdminPatientListService;

/**
 * Servlet implementation class AdminAppointmentListController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-appointment-list" })
public class AdminAppointmentListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminAppointmentListService adminAppointmentListService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAppointmentListController() {
    	this.adminAppointmentListService = new AdminAppointmentListService(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the search query from the request
        String searchQuery = request.getParameter("search");
        
        request.setAttribute("appointmentDetailsList", adminAppointmentListService.getAppointmentDetails(searchQuery)); // Get Appointment info
		
		request.getRequestDispatcher("/WEB-INF/pages/admin/admin_appointment_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
