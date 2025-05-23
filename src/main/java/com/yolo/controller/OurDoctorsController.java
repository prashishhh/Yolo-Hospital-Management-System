package com.yolo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.yolo.service.OurDoctorsService;

/**
 * Servlet implementation class OurDoctorsController
 * This controller handles requests to display the "Our Doctors" page
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/our-doctors" })
public class OurDoctorsController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OurDoctorsService ourDoctorsService;

    /**
     * Constructor initializing the service
     */
    public OurDoctorsController() {
        this.ourDoctorsService = new OurDoctorsService();
    }

    /**
     * Handles GET requests to display the list of doctors
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the search query from the request
        String searchQuery = request.getParameter("search");

        // Get doctor profiles and set as request attribute
        request.setAttribute("doctorList", ourDoctorsService.getDoctorList(searchQuery));

        // Forward to the JSP
        request.getRequestDispatcher("/WEB-INF/pages/our_doctors.jsp").forward(request, response);
    }

    /**
     * Handles POST requests by delegating to doGet
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}