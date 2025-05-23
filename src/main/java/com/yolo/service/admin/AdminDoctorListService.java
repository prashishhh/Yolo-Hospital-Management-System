package com.yolo.service.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yolo.config.DbConfig;
import com.yolo.model.DoctorModel;

public class AdminDoctorListService {
	
	private Connection dbConn;
	private boolean isConnectionError = false;
	
	/**
	 * Constructor that initializes the database connection. Sets the connection
	 * error flag if the connection fails.
	 */
	public AdminDoctorListService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			// Log and handle exceptions related to database connection
			ex.printStackTrace();
			isConnectionError = true;
		}
	}
	
	
	public List<DoctorModel> getDoctorListDetails(String searchQuery) {
		
		if (isConnectionError) {
            System.out.println("Connection Error!");
            return new ArrayList<>();
        }

        String query = "SELECT doctorID, doctorFirstName, doctorLastName, doctorExperience, doctorPhoneNumber, doctorSpecialization FROM Doctor";
        
     // If there's a search query, modify the query to include a LIKE filter
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            query += " WHERE doctorFirstName LIKE ? OR doctorLastName LIKE ? OR doctorSpecialization LIKE ?";
        }
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
        	
        	// If a search query is provided, set the parameters for LIKE queries
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                String searchPattern = "%" + searchQuery + "%";
                stmt.setString(1, searchPattern);
                stmt.setString(2, searchPattern);
                stmt.setString(3, searchPattern);
            }
            
            ResultSet result = stmt.executeQuery();
            List<DoctorModel> doctorInfoList = new ArrayList<>();

            while (result.next()) {
                
            	// Create and Add UserModel with userRole as Patient in the list
            	doctorInfoList.add(new DoctorModel(result.getInt("doctorID"), // doctor Id
            					result.getString("doctorFirstName"), // First Name
            					result.getString("doctorLastName"), // Last Name
            					result.getString("doctorExperience"), // Last Name
            					result.getString("doctorPhoneNumber"), // Last Name
            					result.getString("doctorSpecialization") // Last Name
            			));
            	
            }
            return doctorInfoList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public DoctorModel getDoctorDetails(Integer doctorID) {
        if (isConnectionError) {
            return null;
        }

        String sql = "SELECT * FROM Doctor WHERE doctorID = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
            stmt.setInt(1, doctorID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new DoctorModel(
                    rs.getInt("doctorID"),
                    rs.getString("doctorFirstName"),
                    rs.getString("doctorLastName"),
                    rs.getDate("dateOfBirth").toLocalDate(),
                    rs.getString("doctorGender"),
                    rs.getString("doctorPhoneNumber"),
                    rs.getString("doctorEmail"),
                    rs.getString("doctorCity"),
                    rs.getString("doctorDistrict"),
                    rs.getString("doctorMunicipality"),
                    rs.getString("doctorWard"),
                    rs.getString("doctorQualification"),
                    rs.getString("doctorExperience"),
                    rs.getString("doctorSpecialization"),
                    rs.getString("imagePath")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	/**
     * Deletes a doctor profile and related data from the database
     */
    public void deleteDoctorProfile(int doctorID) throws SQLException {
        if (isConnectionError) {
            throw new SQLException("Database connection error");
        }
        
        try {
            dbConn.setAutoCommit(false); // Start transaction
            
            // Delete from Appointment and related data
            String selectAppointments = "SELECT appointmentID FROM Appointment WHERE doctorID = ?";
            try (PreparedStatement stmt = dbConn.prepareStatement(selectAppointments)) {
                stmt.setInt(1, doctorID);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int appointmentID = rs.getInt("appointmentID");
                    
                    // Delete from UserAppointment
                    String deleteUserAppointment = "DELETE FROM UserAppointment WHERE appointmentID = ?";
                    try (PreparedStatement userAppStmt = dbConn.prepareStatement(deleteUserAppointment)) {
                        userAppStmt.setInt(1, appointmentID);
                        userAppStmt.executeUpdate();
                    }
                    
                    // Delete from Appointment
                    String deleteAppointment = "DELETE FROM Appointment WHERE appointmentID = ?";
                    try (PreparedStatement appStmt = dbConn.prepareStatement(deleteAppointment)) {
                        appStmt.setInt(1, appointmentID);
                        appStmt.executeUpdate();
                    }
                }
            }
            
            // Delete from Doctor
            String deleteDoctor = "DELETE FROM Doctor WHERE doctorID = ?";
            try (PreparedStatement stmt = dbConn.prepareStatement(deleteDoctor)) {
                stmt.setInt(1, doctorID);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("No doctor found with doctorID: " + doctorID);
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
