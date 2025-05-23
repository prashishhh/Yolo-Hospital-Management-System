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
import com.yolo.model.TreatmentModel;
import com.yolo.model.UserModel;

public class AdminTreatmentListService {
	
	private Connection dbConn;
	private boolean isConnectionError = false;
	
	/**
	 * Constructor that initializes the database connection. Sets the connection
	 * error flag if the connection fails.
	 */
	public AdminTreatmentListService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			// Log and handle exceptions related to database connection
			ex.printStackTrace();
			isConnectionError = true;
		}
	}
	
	public List<AppointmentModel> getTreatmentListDetails(String searchQuery) {
		
		if (isConnectionError) {
            System.out.println("Connection Error!");
            return new ArrayList<>();
        }

        String query = "SELECT t.treatmentID, t.treatmentName, t.treatmentPlan, t.remark, "
        		+ "d.doctorID, d.doctorFirstName, d.doctorLastName, a.appointmentID "
        		+ "FROM Treatment t "
        		+ "JOIN Appointment a ON t.treatmentID = a.treatmentID "
        		+ "JOIN Doctor d ON a.doctorID = d.doctorID "
        		+ "ORDER BY t.treatmentID";
        
        // Search Query 
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            query += " WHERE t.treatmentName LIKE ? OR d.doctorFirstName LIKE ? OR d.doctorLastName LIKE ?";
        }
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
        	
        	// If a search query is provided, set the parameters for LIKE queries
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                String searchPattern = "%" + searchQuery + "%";
                stmt.setString(1, searchPattern);
                stmt.setString(2, searchPattern);
                stmt.setString(3, searchPattern);
            }
            
            ResultSet result = stmt.executeQuery();
            List<AppointmentModel> appointmentList = new ArrayList<>();

            while (result.next()) {
                
            	// Create DoctorModel
                DoctorModel doctor = new DoctorModel();
                doctor.setDoctorID(result.getInt("doctorID"));
                doctor.setDoctorFirstName(result.getString("doctorFirstName"));
                doctor.setDoctorLastName(result.getString("doctorLastName"));
                
                // Create TreatmentModel
                TreatmentModel treatment = new TreatmentModel();
                treatment.setTreatmentID(result.getInt("treatmentID"));
                treatment.setTreatmentName(result.getString("treatmentName"));
                treatment.setTreatmentPlan(result.getString("treatmentPlan"));
                treatment.setTreatmentRemark(result.getString("remark"));
                
                // Create AppointmentModel and set fields
                AppointmentModel appointment = new AppointmentModel();
                appointment.setAppointmentID(result.getInt("appointmentID"));
                appointment.setDoctor(doctor);
                appointment.setTreatment(treatment);
                
                appointmentList.add(appointment);
            	
            }
            return appointmentList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}

}
