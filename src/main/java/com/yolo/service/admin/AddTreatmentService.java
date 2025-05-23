package com.yolo.service.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yolo.config.DbConfig;
import com.yolo.model.AppointmentModel;
import com.yolo.model.DoctorModel;
import com.yolo.model.TreatmentModel;
import com.yolo.model.UserAppointmentModel;
import com.yolo.model.UserModel;

public class AddTreatmentService {
    private Connection dbConn;

    public AddTreatmentService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<UserAppointmentModel> getBookedAppointments() {
        List<UserAppointmentModel> bookedAppointments = new ArrayList<>();
        String query = "SELECT a.appointmentID, a.appointmentDate, a.appointmentStartTime, a.appointmentEndTime, a.appointmentFee, " +
                       "u.userID, u.firstName, u.lastName, " +
                       "d.doctorID, d.doctorFirstName, d.doctorLastName " +
                       "FROM Appointment a " +
                       "JOIN UserAppointment ua ON a.appointmentID = ua.appointmentID " +
                       "JOIN User u ON ua.userID = u.userID " +
                       "JOIN Doctor d ON a.doctorID = d.doctorID " +
                       "WHERE a.appointmentStatus = 'Booked'";
        try (PreparedStatement stmt = dbConn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DoctorModel doctor = new DoctorModel(
                    rs.getInt("doctorID"),
                    rs.getString("doctorFirstName"),
                    rs.getString("doctorLastName")
                );
                AppointmentModel appointment = new AppointmentModel(
                    rs.getInt("appointmentID"),
                    "Booked",
                    rs.getDate("appointmentDate").toLocalDate(),
                    rs.getTime("appointmentStartTime").toLocalTime(),
                    rs.getTime("appointmentEndTime").toLocalTime(),
                    rs.getInt("appointmentFee"),
                    doctor, null, null, null
                );
                UserModel user = new UserModel(
                    rs.getInt("userID"),
                    rs.getString("firstName"),
                    rs.getString("lastName")
                );
                UserAppointmentModel userAppointment = new UserAppointmentModel(user, appointment);
                bookedAppointments.add(userAppointment);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching booked appointments: " + e.getMessage());
            e.printStackTrace();
        }
        return bookedAppointments;
    }

    public Integer addTreatment(TreatmentModel treatment) {
        String insertQuery = "INSERT INTO Treatment (treatmentName, treatmentPlan, remark) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = dbConn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, treatment.getTreatmentName());
            stmt.setString(2, treatment.getTreatmentPlan());
            stmt.setString(3, treatment.getTreatmentRemark());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                return null;
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
            return null;
        } catch (SQLException e) {
            System.err.println("Error adding treatment: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateAppointmentWithTreatment(int appointmentID, int treatmentID) {
        String updateQuery = "UPDATE Appointment SET treatmentID = ?, appointmentStatus = 'Completed' " +
                             "WHERE appointmentID = ? AND appointmentStatus = 'Booked'";
        try (PreparedStatement stmt = dbConn.prepareStatement(updateQuery)) {
            stmt.setInt(1, treatmentID);
            stmt.setInt(2, appointmentID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating appointment: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}