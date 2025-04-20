package com.yolo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HomeController handles requests to the home page of the application.
 * It maps to both "/home" and root ("/") URLs and forwards requests to the home.jsp view.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/home", "/" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * Default constructor for HomeController.
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Handles HTTP GET requests and forwards them to the home.jsp page located
     * in /WEB-INF/pages/.
     *
     * @param request  The HttpServletRequest object that contains the request the client made.
     * @param response The HttpServletResponse object that contains the response the servlet returns.
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
