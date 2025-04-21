package com.yolo.controller.patient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * PatientPaymentHistoryController is responsible for handling patient payment history requests.
 * It manages the display and retrieval of a patient's past payment transactions, invoices,
 * and billing records, allowing patients to view their financial history with the medical facility.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/patient-payment-history"})
public class PatientPaymentHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Constructor for PatientPaymentHistoryController.
     * Initializes the servlet by calling the parent constructor.
     */
    public PatientPaymentHistoryController() {
        super();
        // No additional initialization required
    }

	/**
	 * Handles GET requests to the patient payment history page.
	 * Forwards the request to the patient_payment_history.jsp view which displays
	 * the patient's payment records, transaction history, and related financial information.
	 * 
	 * Ideally, this method should also retrieve payment data from a service layer
	 * and add it to the request attributes before forwarding to the JSP.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Forward to the payment history view
		// Future enhancement: Add payment history data to request attributes
		request.getRequestDispatcher("/WEB-INF/pages/patient/patient_payment_history.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests for payment history interactions.
	 * Currently delegates to doGet method, but can be extended to process
	 * filtering, sorting, or searching of payment records.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Currently forwards to the same view as doGet
		// This can be enhanced to handle filter/sort requests or payment record searches
		doGet(request, response);
	}
}