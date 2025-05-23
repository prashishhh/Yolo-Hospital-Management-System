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
import com.yolo.model.TreatmentModel;
import com.yolo.model.UserAppointmentModel;
import com.yolo.model.UserModel;

public class PatientTreatmentService {
    
    private Connection dbConn;
    private boolean isConnectionError = false;
    
    /**
     * Constructor that initializes the database connection
     */
    public PatientTreatmentService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }
    
    /**
     * Retrieves treatment details for a specific patient
     * @param userID The ID of the patient
     * @param searchQuery Optional search query for filtering
     * @return List of UserAppointmentModel containing treatment details
     */
    public List<UserAppointmentModel> getPatientTreatmentDetails(Integer userID, String searchQuery) {
        if (isConnectionError || userID == null) {
            System.out.println("Connection Error or Invalid User ID!");
            return new ArrayList<>();
        }

        String query = "SELECT " +
                "u.firstName AS patientFirstName, u.lastName AS patientLastName, " +
                "d.doctorFirstName, d.doctorLastName, " +
                "t.treatmentID, t.treatmentName, t.treatmentPlan, t.remark, " +
                "a.appointmentID " +
                "FROM Treatment t " +
                "JOIN Appointment a ON t.treatmentID = a.treatmentID " +
                "JOIN UserAppointment ua ON a.appointmentID = ua.appointmentID " +
                "JOIN User u ON ua.userID = u.userID " +
                "JOIN Doctor d ON a.doctorID = d.doctorID " +
                "WHERE ua.userID = ?";
        
        // Add search conditions if searchQuery is provided
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            query += " AND (t.treatmentName LIKE ? OR t.treatmentPlan LIKE ? " +
                    "OR t.remark LIKE ? OR d.doctorFirstName LIKE ? OR d.doctorLastName LIKE ?)";
        }

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            
            // Set search parameters if applicable
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                String searchPattern = "%" + searchQuery + "%";
                stmt.setString(2, searchPattern);
                stmt.setString(3, searchPattern);
                stmt.setString(4, searchPattern);
                stmt.setString(5, searchPattern);
                stmt.setString(6, searchPattern);
            }
            
            ResultSet result = stmt.executeQuery();
            List<UserAppointmentModel> treatmentDetailsList = new ArrayList<>();

            while (result.next()) {
                // Create UserModel for Patient
                UserModel user = new UserModel();
                user.setFirstName(result.getString("patientFirstName"));
                user.setLastName(result.getString("patientLastName"));

                // Create DoctorModel
                DoctorModel doctor = new DoctorModel();
                doctor.setDoctorFirstName(result.getString("doctorFirstName"));
                doctor.setDoctorLastName(result.getString("doctorLastName"));

                // Create TreatmentModel
                TreatmentModel treatment = new TreatmentModel();
                treatment.setTreatmentID(result.getInt("treatmentID"));
                treatment.setTreatmentName(result.getString("treatmentName"));
                treatment.setTreatmentPlan(result.getString("treatmentPlan"));
                treatment.setTreatmentRemark(result.getString("remark"));
                
                // Create AppointmentModel
                AppointmentModel appointment = new AppointmentModel();
                appointment.setAppointmentID(result.getInt("appointmentID"));
                appointment.setDoctor(doctor);
                appointment.setTreatment(treatment);

                // Create UserAppointmentModel and add to list
                UserAppointmentModel userAppointment = new UserAppointmentModel(user, appointment);
                treatmentDetailsList.add(userAppointment);
            }

            return treatmentDetailsList;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}