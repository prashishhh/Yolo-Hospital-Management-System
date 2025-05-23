package com.yolo.service.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yolo.config.DbConfig;
import com.yolo.model.AppointmentModel;
import com.yolo.model.PaymentModel;
import com.yolo.model.UserAppointmentModel;
import com.yolo.model.UserModel;

public class AdminPaymentListService {
	
	private Connection dbConn;
	private boolean isConnectionError = false;
	
	/**
	 * Constructor that initializes the database connection. Sets the connection
	 * error flag if the connection fails.
	 */
	public AdminPaymentListService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			isConnectionError = true;
		}
	}
	
	public List<UserAppointmentModel> getPaymentDetails(String searchQuery) {
	    if (isConnectionError) {
	        System.out.println("Connection Error!");
	        return new ArrayList<>();
	    }

	    String query = "SELECT " +
	            "u.firstName AS patientFirstName, u.lastName AS patientLastName, " +
	            "a.appointmentID, p.paymentID, p.paymentAmount, p.paymentStatus " +
	            "FROM Appointment a " +
	            "LEFT JOIN UserAppointment ua ON a.appointmentID = ua.appointmentID " +
	            "LEFT JOIN User u ON ua.userID = u.userID " +
	            "JOIN Payment p ON a.paymentID = p.paymentID ";
	    
	    // If there's a search query, modify the query to include a LIKE filter
	    if (searchQuery != null && !searchQuery.trim().isEmpty()) {
	        query += "WHERE u.firstName LIKE ? OR u.lastName LIKE ? OR CAST(a.appointmentID AS CHAR) LIKE ? OR CAST(p.paymentID AS CHAR) LIKE ?";
	    }

	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	    	
	    	// If a search query is provided, set the parameters for LIKE queries
	        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
	            String searchPattern = "%" + searchQuery + "%";
	            stmt.setString(1, searchPattern);
	            stmt.setString(2, searchPattern);
	            stmt.setString(3, searchPattern);
	            stmt.setString(4, searchPattern);
	        }
            
	        ResultSet result = stmt.executeQuery();
	        List<UserAppointmentModel> paymentDetailsList = new ArrayList<>();

	        while (result.next()) {
	            // Create UserModel for Patient
	            UserModel user = new UserModel();
	            String firstName = result.getString("patientFirstName");
	            String lastName = result.getString("patientLastName");

	            user.setFirstName(firstName);
	            user.setLastName(lastName);

	            // Create PaymentModel
	            PaymentModel payment = new PaymentModel();
	            payment.setPaymentID(result.getInt("paymentID"));
	            payment.setPaymentAmount(result.getFloat("paymentAmount"));
	            payment.setPaymentStatus(result.getString("paymentStatus"));

	            // Create AppointmentModel
	            AppointmentModel appointment = new AppointmentModel();
	            appointment.setAppointmentID(result.getInt("appointmentID"));
	            appointment.setPayment(payment);

	            // Create UserAppointmentModel and add to list
	            UserAppointmentModel userAppointment = new UserAppointmentModel(user, appointment);
	            paymentDetailsList.add(userAppointment);
	        }

	        return paymentDetailsList;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ArrayList<>();
	    }
	}
}