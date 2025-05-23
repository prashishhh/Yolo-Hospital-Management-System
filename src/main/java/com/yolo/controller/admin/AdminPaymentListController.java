package com.yolo.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.yolo.service.admin.AdminPaymentListService;

/**
 * Servlet implementation class AdminPaymentListController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-payment-list" })
public class AdminPaymentListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminPaymentListService adminPaymentListService;
       
    /**
     * Constructor initializing the service
     */
    public AdminPaymentListController() {
        this.adminPaymentListService = new AdminPaymentListService();
    }

	/**
	 * Handles GET requests to display the payment list
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the search query from the request
        String searchQuery = request.getParameter("search");
        
        // Get payment details and set as request attribute
        request.setAttribute("paymentDetailsList", adminPaymentListService.getPaymentDetails(searchQuery));
		
		// Forward to the JSP
		request.getRequestDispatcher("/WEB-INF/pages/admin/admin_payment_list.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by delegating to doGet
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}