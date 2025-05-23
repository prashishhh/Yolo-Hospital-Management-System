package com.yolo.service.patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.yolo.config.DbConfig;
import com.yolo.model.AppointmentModel;
import com.yolo.model.DoctorModel;
import com.yolo.model.TreatmentModel;
import com.yolo.model.UserAppointmentModel;
import com.yolo.model.UserModel;

/**
 * Service class for interacting with the database to retrieve dashboard-related data for patients.
 * This class handles database connections and performs queries to fetch patient-specific information.
 */
public class PatientDashboardService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    /**
     * Constructor that initializes the database connection. Sets the connection
     * error flag if the connection fails.
     */
    public PatientDashboardService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    /**
     * Retrieves recent appointments for the logged-in patient.
     *
     * @param userID The ID of the logged-in patient.
     * @return A list of UserAppointmentModel objects containing appointment data.
     */
    public List<UserAppointmentModel> getRecentAppointments(int userID) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return new ArrayList<>();
        }

        String query = "SELECT " +
                "u.firstName AS patientFirstName, u.lastName AS patientLastName, " +
                "d.doctorFirstName, d.doctorLastName, d.doctorSpecialization, " +
                "a.appointmentDate, a.appointmentStartTime, a.appointmentEndTime, " +
                "a.appointmentStatus " +
                "FROM Appointment a " +
                "JOIN UserAppointment ua ON a.appointmentID = ua.appointmentID " +
                "JOIN User u ON ua.userID = u.userID " +
                "JOIN Doctor d ON a.doctorID = d.doctorID " +
                "WHERE ua.userID = ? " +
                "ORDER BY a.appointmentDate DESC, a.appointmentStartTime DESC " +
                "LIMIT 5";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            ResultSet result = stmt.executeQuery();
            List<UserAppointmentModel> appointmentList = new ArrayList<>();

            while (result.next()) {
                // Create UserModel for Patient
                UserModel user = new UserModel();
                user.setFirstName(result.getString("patientFirstName"));
                user.setLastName(result.getString("patientLastName"));

                // Create DoctorModel
                DoctorModel doctor = new DoctorModel();
                doctor.setDoctorFirstName(result.getString("doctorFirstName"));
                doctor.setDoctorLastName(result.getString("doctorLastName"));
                doctor.setDoctorSpecialization(result.getString("doctorSpecialization"));

                // Create AppointmentModel
                AppointmentModel appointment = new AppointmentModel();
                appointment.setAppointmentDate(result.getDate("appointmentDate").toLocalDate());
                appointment.setAppointmentStartTime(result.getTime("appointmentStartTime").toLocalTime());
                appointment.setAppointmentEndTime(result.getTime("appointmentEndTime").toLocalTime());
                appointment.setAppointmentStatus(result.getString("appointmentStatus"));
                appointment.setDoctor(doctor);

                // Create UserAppointmentModel and add to list
                UserAppointmentModel userAppointment = new UserAppointmentModel(user, appointment);
                appointmentList.add(userAppointment);
            }

            return appointmentList;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves recent treatment plans for the logged-in patient.
     *
     * @param userID The ID of the logged-in patient.
     * @return A list of TreatmentModel objects containing treatment data.
     */
    public List<TreatmentModel> getRecentTreatments(int userID) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return new ArrayList<>();
        }

        String query = "SELECT t.treatmentName, t.treatmentPlan, t.remark " +
                "FROM Treatment t " +
                "JOIN Appointment a ON t.treatmentID = a.treatmentID " +
                "JOIN UserAppointment ua ON a.appointmentID = ua.appointmentID " +
                "WHERE ua.userID = ? " +
                "ORDER BY a.appointmentDate DESC LIMIT 5";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            ResultSet result = stmt.executeQuery();
            List<TreatmentModel> treatmentList = new ArrayList<>();

            while (result.next()) {
                TreatmentModel treatment = new TreatmentModel();
                treatment.setTreatmentName(result.getString("treatmentName"));
                treatment.setTreatmentPlan(result.getString("treatmentPlan"));
                treatment.setTreatmentRemark(result.getString("remark"));
                treatmentList.add(treatment);
            }

            return treatmentList;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves the total number of upcoming appointments for the logged-in patient.
     *
     * @param userID The ID of the logged-in patient.
     * @return A string representing the count of upcoming appointments.
     */
    public String getTotalAppointments(int userID) {
        if (isConnectionError) {
            return "0";
        }

        String countQuery = "SELECT COUNT(*) AS totalAppointments " +
                "FROM UserAppointment ua " +
                "WHERE ua.userID = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(countQuery)) {
            stmt.setInt(1, userID);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return result.getString("totalAppointments");
            }
            return "";
        } catch (SQLException e) {
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * Retrieves the total number of active treatments for the logged-in patient.
     *
     * @param userID The ID of the logged-in patient.
     * @return A string representing the count of active treatments.
     */
    public String getTotalTreatments(int userID) {
        if (isConnectionError) {
            return "0";
        }

        String countQuery = "SELECT COUNT(*) AS totalTreatments " +
                "FROM Treatment t " +
                "JOIN Appointment a ON t.treatmentID = a.treatmentID " +
                "JOIN UserAppointment ua ON a.appointmentID = ua.appointmentID " +
                "WHERE ua.userID = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(countQuery)) {
            stmt.setInt(1, userID);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return result.getString("totalTreatments");
            }
            return "0";
        } catch (SQLException e) {
            e.printStackTrace();
            return "0";
        }
    }
}