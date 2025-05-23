package com.yolo.service.appointment;

import java.time.LocalDate;

import com.yolo.config.DbConfig;
import com.yolo.model.AppointmentModel;
import com.yolo.model.DoctorModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookAppointmentSlotService {
    private Connection dbConn;
    private boolean isConnectionError = false;

    public BookAppointmentSlotService() {
        try {
        	dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    public DoctorModel getDoctorById(int doctorID) throws SQLException {
        if (isConnectionError) {
            throw new SQLException("Database connection error");
        }

        String query = "SELECT doctorID, doctorFirstName, doctorLastName, doctorQualification, doctorSpecialization, imagePath " +
                       "FROM Doctor WHERE doctorID = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, doctorID);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    return new DoctorModel(
                    		result.getString("doctorFirstName"),
                    	    result.getString("doctorLastName"),
                    	    result.getString("doctorQualification"),
                    	    result.getString("doctorSpecialization"),
                    	    result.getString("imagePath"),
                    	    result.getInt("doctorID")
                    );
                }
                return null;
            }
        }
    }
    
    public List<AppointmentModel> getAvailableAppointmentsByDoctorId(int doctorID) throws SQLException {
        if (isConnectionError) {
            throw new SQLException("Database connection error");
        }

        String query = "SELECT appointmentID, appointmentDate, appointmentStartTime, appointmentEndTime, appointmentFee " +
                       "FROM Appointment WHERE doctorID = ? AND appointmentStatus = 'Available'";
        List<AppointmentModel> appointments = new ArrayList<>();

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, doctorID);
            try (ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    AppointmentModel appointment = new AppointmentModel(
                        result.getInt("appointmentID"),
                        result.getDate("appointmentDate").toLocalDate(),
                        result.getTime("appointmentStartTime").toLocalTime(),
                        result.getTime("appointmentEndTime").toLocalTime(),
                        result.getInt("appointmentFee")
                    );
                    appointments.add(appointment);
                }
            }
            return appointments;
        }
    }
    
    /**
     * Books an appointment by updating its status to Pending and linking it to a user in UserAppointment table.
     *
     * @param appointmentID The ID of the appointment to book
     * @param userID The ID of the user booking the appointment
     * @return true if booking is successful, false otherwise
     * @throws SQLException if a database error occurs
     */
    public boolean bookAppointment(int appointmentID, int userID) throws SQLException {
        if (isConnectionError) {
            throw new SQLException("Database connection error");
        }

        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            throw new SQLException("Database connection is not available.");
        }

        // Use transaction to ensure atomicity
        dbConn.setAutoCommit(false);
        try {
            // Update Appointment table 
            String updateSQL = "UPDATE Appointment SET appointmentStatus = ? WHERE appointmentID = ? AND appointmentStatus = ?";
            try (PreparedStatement stmt = dbConn.prepareStatement(updateSQL)) {
                stmt.setString(1, "Pending");
                stmt.setInt(2, appointmentID);
                stmt.setString(3, "Available");
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    dbConn.rollback();
                    return false; // Appointment was not available
                }
            }

            // Insert into UserAppointment table 
            String insertQuery = "INSERT INTO UserAppointment (userID, appointmentID) VALUES (?, ?)";
            try (PreparedStatement stmt = dbConn.prepareStatement(insertQuery)) {
                stmt.setInt(1, userID);
                stmt.setInt(2, appointmentID);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    dbConn.rollback();
                    return false; // Insert failed
                }
            }

            dbConn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Error during appointment booking: " + e.getMessage());
            e.printStackTrace();
            dbConn.rollback();
            throw e;
        } finally {
            dbConn.setAutoCommit(true);
        }
    }
    
    /**
     * Retrieves an appointment by its ID.
     *
     * @param appointmentID The ID of the appointment to retrieve
     * @return AppointmentModel if found, null otherwise
     * @throws SQLException if a database error occurs
     */
    public AppointmentModel getAppointmentById(int appointmentID) throws SQLException {
        if (isConnectionError) {
            throw new SQLException("Database connection error");
        }

        String query = "SELECT appointmentID, appointmentDate, appointmentStartTime, appointmentEndTime, appointmentFee " +
                       "FROM Appointment WHERE appointmentID = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, appointmentID);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    return new AppointmentModel(
                        result.getInt("appointmentID"),
                        result.getDate("appointmentDate").toLocalDate(),
                        result.getTime("appointmentStartTime").toLocalTime(),
                        result.getTime("appointmentEndTime").toLocalTime(),
                        result.getInt("appointmentFee")
                    );
                }
                return null;
            }
        }
    }

}
