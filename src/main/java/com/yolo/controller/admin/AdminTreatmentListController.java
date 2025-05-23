package com.yolo.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.yolo.service.admin.AdminTreatmentListService;

/**
 * Servlet implementation class AdminTreatmentListController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-treatment-list" })
public class AdminTreatmentListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminTreatmentListService adminTreatmentListService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminTreatmentListController() {
        this.adminTreatmentListService = new AdminTreatmentListService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the search query from the request
        String searchQuery = request.getParameter("search");
        
        // Set the treatment list as a request attribute
        request.setAttribute("treatmentDetailsList", adminTreatmentListService.getTreatmentListDetails(searchQuery));
		request.getRequestDispatcher("/WEB-INF/pages/admin/admin_treatment_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
