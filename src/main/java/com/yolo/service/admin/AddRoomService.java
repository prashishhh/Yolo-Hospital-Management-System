package com.yolo.service.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.yolo.config.DbConfig;
import com.yolo.model.RoomModel;

/**
 * AddRoomService handles the addition of new rooms to the database.
 */
public class AddRoomService {

    private Connection dbConn;

    /**
     * Constructor initializes the database connection.
     */
    public AddRoomService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Adds a new room to the database.
     *
     * @param roomModel the room details to be added
     * @return Boolean indicating the success of the operation
     */
    public Boolean addRoom(RoomModel roomModel) {
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return null;
        }

        String insertQuery = "INSERT INTO Room (roomName, roomType) VALUES (?, ?)";

        try (PreparedStatement stmt = dbConn.prepareStatement(insertQuery)) {
            stmt.setString(1, roomModel.getRoomName());
            stmt.setString(2, roomModel.getRoomType());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during room addition: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}