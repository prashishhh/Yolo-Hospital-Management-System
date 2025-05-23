package com.yolo.service.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yolo.config.DbConfig;
import com.yolo.model.AppointmentModel;
import com.yolo.model.DoctorModel;
import com.yolo.model.RoomModel;
import com.yolo.model.UserAppointmentModel;
import com.yolo.model.UserModel;

public class AdminAppointmentListService {
	
	private Connection dbConn;
	private boolean isConnectionError = false;
	
	/**
	 * Constructor that initializes the database connection. Sets the connection
	 * error flag if the connection fails.
	 */
	public AdminAppointmentListService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			// Log and handle exceptions related to database connection
			ex.printStackTrace();
			isConnectionError = true;
		}
	}
	
	public List<UserAppointmentModel> getAppointmentDetails(String searchQuery) {
	    if (isConnectionError) {
	        System.out.println("Connection Error!");
	        return new ArrayList<>();
	    }

	    String query = "SELECT " +
	            "u.firstName AS patientFirstName, u.lastName AS patientLastName, " +
	            "d.doctorFirstName, d.doctorLastName, " +
	            "a.appointmentID, a.appointmentDate, a.appointmentStartTime, a.appointmentEndTime," +
	            "r.roomName, r.roomType, a.appointmentStatus " +
	            "FROM Appointment a " +
	            "LEFT JOIN UserAppointment ua ON a.appointmentID = ua.appointmentID " +
	            "LEFT JOIN User u ON ua.userID = u.userID " +
	            "JOIN Doctor d ON a.doctorID = d.doctorID " +
	            "JOIN Room r ON a.roomID = r.roomID "
	            + "ORDER BY a.appointmentID";
	    
	    // If there's a search query, modify the query to include a LIKE filter
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            query += "WHERE u.firstName LIKE ? OR u.lastName LIKE ? OR d.doctorFirstName LIKE ? OR d.doctorLastName LIKE ? OR r.roomName LIKE ? OR r.roomType LIKE ? OR a.appointmentStatus LIKE ?";
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
                stmt.setString(6, searchPattern);
                stmt.setString(7, searchPattern);
            }
            
	        ResultSet result = stmt.executeQuery();
	        List<UserAppointmentModel> appointmentDetailsList = new ArrayList<>();

	        while (result.next()) {
	            // Create UserModel for Patient
	            UserModel user = new UserModel();
	            String firstName = result.getString("patientFirstName");
	            String lastName = result.getString("patientLastName");

	            if (firstName != null && lastName != null) {
	                user.setFirstName(firstName);
	                user.setLastName(lastName);
	            } else {
	                user.setFirstName("----------");
	                user.setLastName("");
	            }

	            // Create DoctorModel
	            DoctorModel doctor = new DoctorModel();
	            doctor.setDoctorFirstName(result.getString("doctorFirstName"));
	            doctor.setDoctorLastName(result.getString("doctorLastName"));

	            // Create RoomModel
	            RoomModel room = new RoomModel();
	            room.setRoomName(result.getString("roomName"));
	            room.setRoomType(result.getString("roomType"));
	            
	            // Create AppointmentModel
	            AppointmentModel appointment = new AppointmentModel();
	            appointment.setAppointmentID(result.getInt("appointmentID"));
	            appointment.setAppointmentDate(result.getDate("appointmentDate").toLocalDate());
	            appointment.setAppointmentStartTime(result.getTime("appointmentStartTime").toLocalTime());
	            appointment.setAppointmentEndTime(result.getTime("a.appointmentEndTime").toLocalTime());
	            appointment.setAppointmentStatus(result.getString("appointmentStatus"));
	            appointment.setDoctor(doctor);
	            appointment.setRoom(room);

	            // Create UserAppointmentModel and add to list
	            UserAppointmentModel userAppointment = new UserAppointmentModel(user, appointment);
	            appointmentDetailsList.add(userAppointment);
	        }

	        return appointmentDetailsList;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
