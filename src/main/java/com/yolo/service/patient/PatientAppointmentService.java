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
import com.yolo.model.RoomModel;
import com.yolo.model.UserAppointmentModel;
import com.yolo.model.UserModel;

public class PatientAppointmentService {
    
    private Connection dbConn;
    private boolean isConnectionError = false;
    
    /**
     * Constructor that initializes the database connection
     */
    public PatientAppointmentService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }
    
    /**
     * Retrieves appointment details for a specific patient
     * @param userID The ID of the patient
     * @param searchQuery Optional search query for filtering
     * @return List of UserAppointmentModel containing appointment details
     */
    public List<UserAppointmentModel> getPatientAppointmentDetails(Integer userID, String searchQuery) {
        if (isConnectionError || userID == null) {
            System.out.println("Connection Error or Invalid User ID!");
            return new ArrayList<>();
        }

        String query = "SELECT " +
                "u.firstName AS patientFirstName, u.lastName AS patientLastName, " +
                "d.doctorFirstName, d.doctorLastName, " +
                "a.appointmentID, a.appointmentDate, a.appointmentStartTime, a.appointmentEndTime, " +
                "r.roomName, r.roomType, a.appointmentStatus " +
                "FROM Appointment a " +
                "JOIN UserAppointment ua ON a.appointmentID = ua.appointmentID " +
                "JOIN User u ON ua.userID = u.userID " +
                "JOIN Doctor d ON a.doctorID = d.doctorID " +
                "JOIN Room r ON a.roomID = r.roomID " +
                "WHERE ua.userID = ?";
        
        // Add search conditions if searchQuery is provided
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            query += " AND (d.doctorFirstName LIKE ? OR d.doctorLastName LIKE ? " +
                    "OR r.roomName LIKE ? OR r.roomType LIKE ? OR a.appointmentStatus LIKE ?)";
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
            List<UserAppointmentModel> appointmentDetailsList = new ArrayList<>();

            while (result.next()) {
                // Create UserModel for Patient
                UserModel user = new UserModel();
                user.setFirstName(result.getString("patientFirstName"));
                user.setLastName(result.getString("patientLastName"));

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
                appointment.setAppointmentEndTime(result.getTime("appointmentEndTime").toLocalTime());
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
            return new ArrayList<>();
        }
    }
}