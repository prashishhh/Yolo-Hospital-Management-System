package com.yolo.service.patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yolo.config.DbConfig;
import com.yolo.model.UserModel;

public class PatientProfileService {
	
	private Connection dbConn;
	private boolean isConnectionError = false;
	
	/**
	 * Constructor that initializes the database connection. Sets the connection
	 * error flag if the connection fails.
	 */
	public PatientProfileService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			isConnectionError = true;
		}
	}
	
	public UserModel getPatientDetails(int userID) {
		
		if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        String query = "SELECT userID, firstName, lastName, dateOfBirth, gender, phoneNumber, email, city, district, municipality, wardNo, username, imagePath " 
        				+ "FROM User WHERE userID = ? AND userRole = 'Patient'";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
        	stmt.setInt(1, userID); 
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                
            	return new UserModel(
            					result.getInt("userID"),
            					result.getString("firstName"), 
            					result.getString("lastName"),
            					result.getDate("dateOfBirth").toLocalDate(), 
            					result.getString("gender"),
            					result.getString("phoneNumber"),
            					result.getString("email"),
            					result.getString("city"),
            					result.getString("district"),
            					result.getString("municipality"), 
            					result.getString("wardNo"), 
            					result.getString("userName"),
            					result.getString("imagePath")
            			);
            	
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	/**
	 * Deletes a patient profile and related data from the database.
	 *
	 * @param userID The ID of the patient to delete
	 * @throws SQLException if a database error occurs
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
			
			// Delete from UserAppointment and related Appointment records
			String selectAppointments = "SELECT appointmentID FROM UserAppointment WHERE userID = ?";
			try (PreparedStatement stmt = dbConn.prepareStatement(selectAppointments)) {
				stmt.setInt(1, userID);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int appointmentID = rs.getInt("appointmentID");
					// Delete from Appointment
					String deleteAppointment = "DELETE FROM Appointment WHERE appointmentID = ?";
					try (PreparedStatement appStmt = dbConn.prepareStatement(deleteAppointment)) {
						appStmt.setInt(1, appointmentID);
						appStmt.executeUpdate();
					}
				}
			}
			
			// Delete from UserAppointment
			String deleteUserAppointment = "DELETE FROM UserAppointment WHERE userID = ?";
			try (PreparedStatement stmt = dbConn.prepareStatement(deleteUserAppointment)) {
				stmt.setInt(1, userID);
				stmt.executeUpdate();
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
