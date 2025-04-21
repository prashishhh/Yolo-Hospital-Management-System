package com.yolo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yolo.config.DbConfig;
import com.yolo.model.UserModel;
import com.yolo.util.PasswordUtil;

/**
 * Service class for handling login operations. Connects to the database,
 * verifies user credentials, and returns login status.
 */
public class LoginService {
	private Connection dbConn;
	
	private boolean isConnectionError = false;
	
	/**
	 * Constructor initializes the database connection. 
	 * Sets the connection error flag if the connection fails.
	 */
	public LoginService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			isConnectionError = true;
		}
	}
	
	/**
	 * Validates the user credentials against the database records for the hospital system.
	 *
	 * @param userModel the UserModel object containing user credentials (username and password)
	 * @return true if the user credentials are valid, false otherwise; null if a connection error occurs
	 */
	public Boolean loginUser(UserModel userModel) {
	    if (isConnectionError) {
	        System.err.println("Database connection error: Connection is not available!");
	        return null;
	    }

	    String query = "SELECT username, password, userRole FROM User WHERE username = ?";
	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        stmt.setString(1, userModel.getUserName());
	        ResultSet result = stmt.executeQuery();

	        if (result.next()) {
	            return validatePassword(result, userModel);
	        }
	    } catch (SQLException e) {
	        System.err.println("Error during login validation: " + e.getMessage());
	        e.printStackTrace();
	        return null;
	    }

	    return false; // No matching username found
	}
	
	/**
	 * Validates the provided password against the stored password in the database.
	 *
	 * @param result the ResultSet containing the stored username and password from the database
	 * @param userModel the UserModel object with the user credentials
	 * @return true if the password matches, false otherwise
	 * @throws SQLException if an database error occurs while accessing the ResultSet
	 */
	private boolean validatePassword(ResultSet result, UserModel userModel) throws SQLException {
	    String dbUsername = result.getString("username");
	    String dbPassword = result.getString("password");
	    String dbRole = result.getString("userRole");
	    
	    if (dbUsername.equals(userModel.getUserName()) &&
	            PasswordUtil.decrypt(dbPassword, dbUsername).equals(userModel.getPassword())) {
	            userModel.setUserRole(dbRole); // set the role in UserModel
	            return true;
	        }

	        return false;
	}
}
