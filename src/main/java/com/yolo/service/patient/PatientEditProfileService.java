package com.yolo.service.patient;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.yolo.config.DbConfig;
import com.yolo.model.UserModel;

public class PatientEditProfileService {
	
	private Connection dbConn;
	private boolean isConnectionError = false;
	
	/**
	 * Constructor that initializes the database connection. Sets the connection
	 * error flag if the connection fails.
	 */
	public PatientEditProfileService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			// Log and handle exceptions related to database connection
			ex.printStackTrace();
			isConnectionError = true;
		}
	}
	
	public Boolean updatePatientInfo(UserModel userModel) {
		if (isConnectionError) {
			return null;
		}

		

		String updateSQL = "UPDATE User SET firstName = ?, lastName = ?, dateOfBirth = ?, " +
                "gender = ?, phoneNumber = ?, email = ?, city = ?, district = ?, " +
                "municipality = ?, wardNo = ?, imagePath = ? WHERE userID = ?";

		try (PreparedStatement stmt = dbConn.prepareStatement(updateSQL)) {
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
	        stmt.setString(11, userModel.getImagePath());	        
	        stmt.setInt(12, userModel.getUserId());
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			// Log and handle SQL exceptions
			e.printStackTrace();
			return null;
		}
	}
}
