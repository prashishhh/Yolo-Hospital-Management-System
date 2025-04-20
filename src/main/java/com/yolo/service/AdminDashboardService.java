package com.yolo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.yolo.config.DbConfig;
import com.yolo.model.UserModel;

/**
 * Service class for interacting with the database to retrieve dashboard-related data for admin.
 * This class handles database connections and performs queries to fetch hospital system related information.
 */
public class AdminDashboardService {
	
	private Connection dbConn;
	private boolean isConnectionError = false;
	
	/**
	 * Constructor that initializes the database connection. Sets the connection
	 * error flag if the connection fails.
	 */
	public AdminDashboardService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			// Log and handle exceptions related to database connection
			ex.printStackTrace();
			isConnectionError = true;
		}
	}
	
	/**
     * Retrieves recent patient information from the database.
     * 
     * @return A list of UserModel objects containing patient data.
     */
	public List<UserModel> getRecentPatients() {
		
		if (isConnectionError) {
            System.out.println("Connection Error!");
            return new ArrayList<>();
        }

        String query = "SELECT firstName, lastName, gender, dateOfBirth, municipality, wardNo, imagePath " 
        				+ "FROM User WHERE userRole = 'Patient' ORDER BY userID DESC LIMIT 5";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            List<UserModel> patientList = new ArrayList<>();

            while (result.next()) {
                
            	// Create and Add UserModel with userRole as Patient in the list
            	patientList.add(new UserModel(result.getString("firstName"), // First Name
            					result.getString("lastName"), // Last Name
            					result.getString("gender"),
            					result.getDate("dateOfBirth").toLocalDate(), // Date of Birth
            					result.getString("municipality"), // Municipality
            					result.getString("wardNo"), //Ward No
            					result.getString("imagePath")
            			));
            	
            }
            return patientList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
		
		
	}
	

}
