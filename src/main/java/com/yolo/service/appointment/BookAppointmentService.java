package com.yolo.service.appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.yolo.config.DbConfig;
import com.yolo.model.DoctorModel;

public class BookAppointmentService {
    private Connection dbConn;
    private boolean isConnectionError = false;

    public BookAppointmentService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    public List<DoctorModel> getAllDoctors() throws SQLException {
        if (isConnectionError) {
            throw new SQLException("Database connection error");
        }

        String query = "SELECT doctorID, doctorFirstName, doctorLastName, doctorSpecialization, imagePath FROM Doctor";
        List<DoctorModel> doctors = new ArrayList<>();

        try (PreparedStatement stmt = dbConn.prepareStatement(query);
        	     ResultSet result = stmt.executeQuery()) {
        	    while (result.next()) {
        	        doctors.add(new DoctorModel(
        	            result.getInt("doctorID"),
        	            result.getString("doctorFirstName"),
        	            result.getString("doctorLastName"),
        	            result.getString("doctorSpecialization"),
        	            result.getString("imagePath")
        	        ));
        	    }
        	return doctors;
        	} catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    } 
}