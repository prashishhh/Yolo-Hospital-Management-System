package com.yolo.service.appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.yolo.config.DbConfig;

public class PaymentService {
	
	private Connection dbConn;
    private boolean isConnectionError = false;

    public PaymentService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }
    
    /**
     * Creates a new payment record with the appointment fee and status 'Completed'.
     * @param appointmentID The ID of the associated appointment
     * @param amount The appointment fee
     * @return The generated paymentID
     * @throws SQLException If a database error occurs
     */
    public int createPayment(int appointmentID, float amount) throws SQLException {
        if (isConnectionError) {
            throw new SQLException("Database connection error");
        }

        String query = "INSERT INTO Payment (paymentAmount, paymentStatus) VALUES (?, ?)";
        try (PreparedStatement stmt = dbConn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setFloat(1, amount);
            stmt.setString(2, "Completed"); // Payment is completed immediately
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to create payment, no ID obtained.");
                }
            }
        }
    }
    
    /**
     * Updates the appointment status and links it to the payment.
     * @param appointmentID The ID of the appointment
     * @param paymentID The ID of the payment
     * @param status The new status ("Booked")
     * @return True if successful, false otherwise
     * @throws SQLException If a database error occurs
     */
    public boolean updateAppointmentStatusAndPayment(int appointmentID, int paymentID, String status) throws SQLException {
        if (isConnectionError) {
            throw new SQLException("Database connection error");
        }

        String query = "UPDATE Appointment SET appointmentStatus = ?, paymentID = ? " +
                       "WHERE appointmentID = ? AND appointmentStatus = 'Pending'";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, paymentID);
            stmt.setInt(3, appointmentID);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    /**
     * Cancels a booking by reverting the appointment status to 'Available' and removing the UserAppointment entry.
     * @param appointmentID The ID of the appointment
     * @param userID The ID of the user
     * @return True if successful, false otherwise
     * @throws SQLException If a database error occurs
     */
    public boolean cancelBooking(int appointmentID, int userID) throws SQLException {
        if (isConnectionError) {
            throw new SQLException("Database connection error");
        }

        dbConn.setAutoCommit(false);
        try {
            // Update Appointment to Available and clear paymentID
            String updateQuery = "UPDATE Appointment SET appointmentStatus = 'Available', paymentID = NULL " +
                                 "WHERE appointmentID = ? AND appointmentStatus = 'Pending'";
            try (PreparedStatement stmt = dbConn.prepareStatement(updateQuery)) {
                stmt.setInt(1, appointmentID);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    dbConn.rollback();
                    return false;
                }
            }

            // Delete UserAppointment entry
            String deleteQuery = "DELETE FROM UserAppointment WHERE appointmentID = ? AND userID = ?";
            try (PreparedStatement stmt = dbConn.prepareStatement(deleteQuery)) {
                stmt.setInt(1, appointmentID);
                stmt.setInt(2, userID);
                stmt.executeUpdate();
            }

            dbConn.commit();
            return true;
        } catch (SQLException e) {
            dbConn.rollback();
            throw e;
        } finally {
            dbConn.setAutoCommit(true);
        }
    }
    
}
