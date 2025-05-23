package com.yolo.service.admin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import com.yolo.config.DbConfig;
import com.yolo.model.AppointmentModel;

/**
 * CreateAppointmentService handles the creation of new appointment slots in the database.
 */
public class CreateAppointmentService {

    private Connection dbConn;

    /**
     * Constructor initializes the database connection.
     */
    public CreateAppointmentService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Creates a new appointment slot in the database.
     *
     * @param appointmentModel the appointment details to be added
     * @return Boolean indicating the success of the operation
     */
    public Boolean createAppointment(AppointmentModel appointmentModel) {
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return null;
        }

        String insertQuery = "INSERT INTO Appointment (appointmentStatus, appointmentDate, appointmentStartTime, appointmentEndTime, appointmentFee, doctorID, roomID) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = dbConn.prepareStatement(insertQuery)) {
            stmt.setString(1, appointmentModel.getAppointmentStatus());
            stmt.setDate(2, Date.valueOf(appointmentModel.getAppointmentDate()));
            stmt.setTime(3, Time.valueOf(appointmentModel.getAppointmentStartTime()));
            stmt.setTime(4, Time.valueOf(appointmentModel.getAppointmentEndTime()));
            stmt.setInt(5, appointmentModel.getAppointmentFee());
            stmt.setInt(6, appointmentModel.getDoctor().getDoctorID());
            stmt.setInt(7, appointmentModel.getRoom().getRoomID());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during appointment creation: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}