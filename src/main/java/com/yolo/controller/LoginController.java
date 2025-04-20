package com.yolo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.yolo.model.UserModel;
import com.yolo.service.LoginService;
import com.yolo.util.CookieUtil;
import com.yolo.util.SessionUtil;

/**
 * LoginController is responsible for handling login requests. 
 * It interacts with the LoginService to authenticate users.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final LoginService loginService;

	/**
	 * Constructor initializes the LoginService.
	 */
	public LoginController() {
		this.loginService = new LoginService();
	}
       
  
	/**
	 * Handles GET requests to the login page.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}

	
	/**
	 * Handles POST requests for user login.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserModel userModel = new UserModel(username, password);
		Boolean loginStatus = loginService.loginUser(userModel);
		
		if (loginStatus != null && loginStatus) {
			SessionUtil.setAttribute(request, "username", username);
			if (username.equals("admin")) {
				SessionUtil.setAttribute(request, "userRole", "Admin");
				CookieUtil.addCookie(response, "role", "Admin", 5 * 30);
				response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard"); // Redirect to /admin dashboard
			} else {
				SessionUtil.setAttribute(request, "userRole", "Patient");
				CookieUtil.addCookie(response, "role", "Patient", 5 * 30);
				response.sendRedirect(request.getContextPath() + "/home"); // Redirect to /home
			}
		} else {
			handleLoginFailure(request, response, loginStatus);
		}

	}

	/**
	 * Handles login failures by setting attributes and forwarding to the login
	 * page.
	 *
	 * @param req         HttpServletRequest object
	 * @param resp        HttpServletResponse object
	 * @param loginStatus Boolean indicating the login status
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	private void handleLoginFailure(HttpServletRequest request, HttpServletResponse response, Boolean loginStatus) {
		
		String errorMessage;
		
		if (loginStatus == null) {
			errorMessage = "Our server is under maintenance. Please try again later!";
		} else {
			errorMessage = "User credential mismatch. Please try again!";
		}
		
		request.setAttribute("error", errorMessage);
		
		try {
			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

}
