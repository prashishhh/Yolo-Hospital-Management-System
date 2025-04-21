package com.yolo.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.yolo.util.CookieUtil;
import com.yolo.util.SessionUtil;

@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

	// Public Pages
	private static final String LOGIN = "/login";
	private static final String REGISTER = "/register";
	private static final String ROOT = "/";
	private static final String HOME = "/home";
	private static final String ABOUT_US = "/about-us";
	private static final String BLOGS = "/blog";
	private static final String LOGOUT = "/logout";

	// Patient Specific Page
	private static final String OUR_DOCTORS = "/our-doctors";
	private static final String PATIENT_APPOINTMENT = "/patient-appointment";
	private static final String PATIENT_DASHBOARD = "/patient-dashboard";
	private static final String PATIENT_EDIT_PROFILE = "/patient-edit-profile";
	private static final String PATIENT_PAYMENT_HISTORY = "/patient-payment-history";
	private static final String PATIENT_PROFILE = "/patient-profile";
	private static final String PATIENT_TREATMENT = "/patient-treatment";
	
	// Patient Booking Appointment Pages
	private static final String BOOK_APPOINTMENT = "/book-appointment";
	private static final String BOOK_APPOINTMENT_SLOT = "/book-appointment-slot";
	private static final String PAYMENT = "/payment";
	
	// Admin Specific Pages
	private static final String ADD_BLOG = "/add-blog";
	private static final String ADD_DOCTOR = "/add-doctor";
	private static final String ADD_ROOM = "/add-room";
	private static final String ADD_TREATMENT = "/add-treatment";
	private static final String ADMIN_APPOINTMENT_LIST = "/admin-appointment-list";
	private static final String ADMIN_DASHBOARD = "/admin-dashboard";
	private static final String ADMIN_DOCTOR_LIST = "/admin-doctor-list";
	private static final String ADMIN_PATIENT_LIST = "/admin-patient-list";
	private static final String ADMIN_PAYMENT_LIST = "/admin-payment-list";
	private static final String ADMIN_ROOM_LIST = "/admin-room-list";
	private static final String ADMIN_TREATMENT_LIST = "/admin-treatment-list";
	private static final String CREATE_APPOINTMENT = "/create-appointment";
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Optional initialization
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();

		// Allow access to static resources
		if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png") || uri.contains("/assets/")) {
			chain.doFilter(request, response);
			return;
		}

		boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;
		String userRole = CookieUtil.getCookie(req, "role") != null ? CookieUtil.getCookie(req, "role").getValue() : null;

		// Allow open/public routes
		if (uri.equals(ROOT) || uri.endsWith(LOGIN) || uri.endsWith(REGISTER) ||
			uri.endsWith(ABOUT_US) || uri.endsWith(OUR_DOCTORS) || uri.endsWith(BLOGS) || uri.endsWith(HOME) || uri.endsWith(LOGOUT)){
			chain.doFilter(request, response);
			return;
		}

		// When Admin is logged in
		if (isLoggedIn && "Admin".equals(userRole)) {
			if (uri.endsWith(LOGIN)) {
				res.sendRedirect(req.getContextPath() + ADMIN_DASHBOARD);
			} else if (uri.endsWith(ADD_BLOG) || uri.endsWith(ADD_DOCTOR) || uri.endsWith(ADD_ROOM) || uri.endsWith(ADD_TREATMENT)
		            || uri.endsWith(ADMIN_APPOINTMENT_LIST) || uri.endsWith(ADMIN_DASHBOARD)
		            || uri.endsWith(ADMIN_DOCTOR_LIST) || uri.endsWith(ADMIN_PATIENT_LIST)
		            || uri.endsWith(ADMIN_PAYMENT_LIST) || uri.endsWith(ADMIN_ROOM_LIST)
		            || uri.endsWith(ADMIN_TREATMENT_LIST) || uri.endsWith(CREATE_APPOINTMENT)) {
				chain.doFilter(request, response);
		    } else {
		        res.sendRedirect(req.getContextPath() + ADMIN_DASHBOARD);
			}
			return;
		} else if (isLoggedIn && "Patient".equals(userRole)) {
			// When Patient is Logged IN
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
				res.sendRedirect(req.getContextPath() + HOME);
			} else if (uri.endsWith(OUR_DOCTORS) || uri.endsWith(ABOUT_US) || uri.endsWith(BLOGS) ||
					   uri.endsWith(PATIENT_APPOINTMENT) || uri.endsWith(PATIENT_DASHBOARD)
		            || uri.endsWith(PATIENT_EDIT_PROFILE) || uri.endsWith(PATIENT_PAYMENT_HISTORY)
		            || uri.endsWith(PATIENT_PROFILE) || uri.endsWith(PATIENT_TREATMENT)
		            || uri.endsWith(BOOK_APPOINTMENT) || uri.endsWith(BOOK_APPOINTMENT_SLOT)
		            || uri.endsWith(PAYMENT)) {
				 chain.doFilter(request, response);
			} else {
		        res.sendRedirect(req.getContextPath() + ADMIN_DASHBOARD);
			}
			return;
		} else {
			// Not logged in
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER) || uri.endsWith(HOME) || uri.endsWith(ROOT)) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + LOGIN);
			}
		}

	}

	@Override
	public void destroy() {
		// Optional cleanup
	}
}
