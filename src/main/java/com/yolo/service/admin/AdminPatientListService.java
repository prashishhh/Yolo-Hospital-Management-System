package com.yolo.service.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yolo.config.DbConfig;
import com.yolo.model.UserModel;

public class AdminPatientListService {
	
	private Connection dbConn;
	private boolean isConnectionError = false;
	
	/**
	 * Constructor that initializes the database connection. Sets the connection
	 * error flag if the connection fails.
	 */
	public AdminPatientListService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			// Log and handle exceptions related to database connection
			ex.printStackTrace();
			isConnectionError = true;
		}
	}
	
	public List<UserModel> getPatientListDetails(String searchQuery) {
		
		if (isConnectionError) {
            System.out.println("Connection Error!");
            return new ArrayList<>();
        }

        String query = "SELECT userID, firstName, lastName, dateOfBirth, gender, phoneNumber, municipality, wardNo FROM User Where userRole='Patient'";
        
        // If there's a search query, modify the query to include a LIKE filter
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            query += "AND (firstName LIKE ? OR lastName LIKE ? OR gender LIKE ? OR municipality LIKE ? OR wardNo LIKE ?)";
        }
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
        	
        	// If a search query is provided, set the parameters for LIKE queries
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                String searchPattern = "%" + searchQuery + "%";
                stmt.setString(1, searchPattern);
                stmt.setString(2, searchPattern);
                stmt.setString(3, searchPattern);
                stmt.setString(4, searchPattern);
                stmt.setString(5, searchPattern);
            }
            
            ResultSet result = stmt.executeQuery();
            List<UserModel> patientInfoList = new ArrayList<>();

            while (result.next()) {
                
            	// Create and Add UserModel with userRole as Patient in the list
            	patientInfoList.add(new UserModel(result.getInt("userID"), // user Id
            					result.getString("firstName"), // First Name
            					result.getString("lastName"), // Last Name
            					result.getDate("dateOfBirth").toLocalDate(), // Date of Birth
            					result.getString("gender"), // gender
            					result.getString("phoneNumber"), // phone Number
            					result.getString("municipality"), // municipality
            					result.getString("wardNo") // ward no
            			));
            	
            }
            return patientInfoList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	/**
     * Deletes a patient profile and related data from the database
     */
	public void deletePatientProfile(int userID) throws SQLException {
	    if (isConnectionError) {
	        throw new SQLException("Database connection error");
	    }
	    
	    try {
	        dbConn.setAutoCommit(false); // Start transaction
	        
	        // Delete from UserBlog
	        String deleteUserBlog = "DELETE FROM UserBlog WHERE userID = ?";
	        try (PreparedStatement stmt = dbConn.prepareStatement(deleteUserBlog)) {
	            stmt.setInt(1, userID);
	            stmt.executeUpdate();
	        }
	        
	        // Delete from UserAppointment first
	        String deleteUserAppointment = "DELETE FROM UserAppointment WHERE userID = ?";
	        try (PreparedStatement stmt = dbConn.prepareStatement(deleteUserAppointment)) {
	            stmt.setInt(1, userID);
	            stmt.executeUpdate();
	        }
	        
	        // Then delete from Appointment for the related appointmentIDs
	        String selectAppointments = "SELECT appointmentID FROM UserAppointment WHERE userID = ?";
	        try (PreparedStatement stmt = dbConn.prepareStatement(selectAppointments)) {
	            stmt.setInt(1, userID);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                int appointmentID = rs.getInt("appointmentID");
	                String deleteAppointment = "DELETE FROM Appointment WHERE appointmentID = ?";
	                try (PreparedStatement appStmt = dbConn.prepareStatement(deleteAppointment)) {
	                    appStmt.setInt(1, appointmentID);
	                    appStmt.executeUpdate();
	                }
	            }
	        }
	        
	        // Delete from User
	        String deleteUser = "DELETE FROM User WHERE userID = ? AND userRole = 'Patient'";
	        try (PreparedStatement stmt = dbConn.prepareStatement(deleteUser)) {
	            stmt.setInt(1, userID);
	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected == 0) {
	                throw new SQLException("No patient found with userID: " + userID);
	            }
	        }
	        
	        dbConn.commit(); // Commit transaction
	    } catch (SQLException e) {
	        dbConn.rollback(); // Rollback on error
	        throw e;
	    } finally {
	        dbConn.setAutoCommit(true); // Restore auto-commit
	    }
	}

}
