package com.yolo.controller.appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class PaymentController
 * Handles HTTP requests related to the payment process.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/payment" })
public class PaymentController extends HttpServlet {
    // Serial version UID for version control of the serialized class
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     * Initializes the servlet.
     */
    public PaymentController() {
        super();
        // TODO Auto-generated constructor stub (can be removed once no longer needed)
    }

    /**
     * Handles GET requests.
     * Redirects the request to the payment page (payment.jsp).
     * 
     * @param request The HttpServletRequest object that contains the request
     * @param response The HttpServletResponse object that contains the response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException If an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forwarding the request to the payment.jsp page for rendering
        request.getRequestDispatcher("/WEB-INF/pages/appointment/payment.jsp").forward(request, response);
    }

    /**
     * Handles POST requests.
     * Calls the doGet method for processing POST requests similarly.
     * 
     * @param request The HttpServletRequest object that contains the request
     * @param response The HttpServletResponse object that contains the response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException If an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Calling the doGet method to process the POST request
        doGet(request, response);
    }
}
