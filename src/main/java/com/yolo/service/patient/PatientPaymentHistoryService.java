package com.yolo.service.patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yolo.config.DbConfig;
import com.yolo.model.AppointmentModel;
import com.yolo.model.DoctorModel;
import com.yolo.model.PaymentModel;
import com.yolo.model.UserAppointmentModel;
import com.yolo.model.UserModel;

public class PatientPaymentHistoryService {
    
    private Connection dbConn;
    private boolean isConnectionError = false;
    
    /**
     * Constructor that initializes the database connection
     */
    public PatientPaymentHistoryService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }
    
    /**
     * Retrieves payment details for a specific patient
     * @param userID The ID of the patient
     * @param searchQuery Optional search query for filtering
     * @return List of UserAppointmentModel containing payment details
     */
    public List<UserAppointmentModel> getPatientPaymentDetails(Integer userID, String searchQuery) {
        if (isConnectionError || userID == null) {
            System.out.println("Connection Error or Invalid User ID!");
            return new ArrayList<>();
        }

        String query = "SELECT " +
                "u.firstName AS patientFirstName, u.lastName AS patientLastName, " +
                "p.paymentID, p.paymentAmount, a.appointmentID, a.appointmentDate " +
                "FROM Payment p " +
                "JOIN Appointment a ON p.paymentID = a.paymentID " +
                "JOIN UserAppointment ua ON a.appointmentID = ua.appointmentID " +
                "JOIN User u ON ua.userID = u.userID " +
                "WHERE ua.userID = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            
            ResultSet result = stmt.executeQuery();
            List<UserAppointmentModel> paymentDetailsList = new ArrayList<>();

            while (result.next()) {
                // Create UserModel for Patient
                UserModel user = new UserModel();
                user.setFirstName(result.getString("patientFirstName"));
                user.setLastName(result.getString("patientLastName"));

                // Create PaymentModel
                PaymentModel payment = new PaymentModel();
                payment.setPaymentID(result.getInt("paymentID"));
                payment.setPaymentAmount(result.getFloat("paymentAmount"));

                // Create AppointmentModel
                AppointmentModel appointment = new AppointmentModel();
                appointment.setAppointmentID(result.getInt("appointmentID"));
                appointment.setAppointmentDate(result.getDate("appointmentDate").toLocalDate());
                appointment.setPayment(payment);

                // Create UserAppointmentModel and add to list
                UserAppointmentModel userAppointment = new UserAppointmentModel(user, appointment);
                paymentDetailsList.add(userAppointment);
            }

            return paymentDetailsList;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}