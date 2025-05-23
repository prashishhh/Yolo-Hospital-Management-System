package com.yolo.service.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yolo.config.DbConfig;
import com.yolo.model.RoomModel;

public class AdminRoomListService {
	private Connection dbConn;
	private boolean isConnectionError = false;
	
	/**
	 * Constructor that initializes the database connection. Sets the connection
	 * error flag if the connection fails.
	 */
	public AdminRoomListService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			// Log and handle exceptions related to database connection
			ex.printStackTrace();
			isConnectionError = true;
		}
	}
	
	public List<RoomModel> getRoomListDetails(String searchQuery) {
		
		if(isConnectionError) {
			System.out.println("Connection Error");
			return new ArrayList<>();
		}
		
		String query = "SELECT roomID, roomName, roomType FROM Room";
		
//		Search Query
		if (searchQuery != null && !searchQuery.trim().isEmpty()) {
			query += " WHERE roomName LIKE ? OR roomType LIKE ?";
		}
		
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			
			if (searchQuery != null && !searchQuery.trim().isEmpty()) {
				String searchPattern = "%" + searchQuery + "%";
				stmt.setString(1, searchPattern);
				stmt.setString(2, searchPattern);
			}
			
			ResultSet result = stmt.executeQuery();
			List<RoomModel> roomList = new ArrayList<>();
			
			while (result.next()) {
				roomList.add(new RoomModel(result.getInt("roomID"),
							result.getString("roomName"),
							result.getString("roomType")
						));
			}
			
			return roomList;
			
		} catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}

}
