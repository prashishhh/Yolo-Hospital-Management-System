package com.yolo.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.yolo.config.DbConfig;
import com.yolo.model.UserModel;

/**
 * RegisterService handles the registration of new users(patients and admin). It manages database
 * interactions for student registration.
 */
public class RegisterService {
	
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public RegisterService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	/**
	 * Registers a new patient in the database.
	 *
	 * @param userModel the user(patient) details to be registered
	 * @return Boolean indicating the success of the operation
	 */
	public boolean registerPatient(UserModel userModel) {
		
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return false;
		}
		
	    String insertQuery = "INSERT INTO User (firstName, lastName, dateOfBirth, gender, phoneNumber, email, city, district, municipality, wardNo, userName, password, userRole, imagePath) "
	        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Patient', ?)";

	    try (PreparedStatement stmt = dbConn.prepareStatement(insertQuery)) {
	    	
	    	// Insert Patient details
	    	stmt.setString(1, userModel.getFirstName());
	        stmt.setString(2, userModel.getLastName());
	        stmt.setDate(3, Date.valueOf(userModel.getDateOfBirth()));
	        stmt.setString(4, userModel.getGender());
	        stmt.setString(5, userModel.getPhoneNumber());
	        stmt.setString(6, userModel.getEmail());
	        stmt.setString(7, userModel.getCity());
	        stmt.setString(8, userModel.getDistrict());
	        stmt.setString(9, userModel.getMunicipality());
	        stmt.setString(10, userModel.getWardNo());
	        stmt.setString(11, userModel.getUserName());
	        stmt.setString(12, userModel.getPassword());
	        stmt.setString(13, userModel.getImagePath());

	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	    	System.err.println("Error during patient registration: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}

}
