package com.yolo.service.admin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.yolo.config.DbConfig;
import com.yolo.model.DoctorModel;

/**
 * EditDoctorService handles database operations for updating doctor profiles.
 */
public class EditDoctorService {
    private Connection dbConn;
    private boolean isConnectionError = false;

    public EditDoctorService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    /**
     * Updates doctor information in the database.
     * @param doctorModel The DoctorModel containing updated doctor data.
     * @return True if update succeeds, false if it fails, null if there's a connection error.
     */
    public Boolean updateDoctorInfo(DoctorModel doctorModel) {
        if (isConnectionError) {
            System.out.println("Database connection error");
            return null;
        }

        String updateSQL = "UPDATE Doctor SET doctorFirstName = ?, doctorLastName = ?, dateOfBirth = ?, " +
                          "doctorGender = ?, doctorPhoneNumber = ?, doctorEmail = ?, doctorCity = ?, doctorDistrict = ?, " +
                          "doctorMunicipality = ?, doctorWard = ?, doctorQualification = ?, doctorExperience = ?, " +
                          "doctorSpecialization = ?, imagePath = ? WHERE doctorID = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(updateSQL)) {
            stmt.setString(1, doctorModel.getDoctorFirstName());
            stmt.setString(2, doctorModel.getDoctorLastName());
            stmt.setDate(3, Date.valueOf(doctorModel.getDoctorDateOfBirth()));
            stmt.setString(4, doctorModel.getDoctorGender());
            stmt.setString(5, doctorModel.getDoctorPhoneNumber());
            stmt.setString(6, doctorModel.getDoctorEmail());
            stmt.setString(7, doctorModel.getDoctorCity());
            stmt.setString(8, doctorModel.getDoctorDistrict());
            stmt.setString(9, doctorModel.getDoctorMunicipality());
            stmt.setString(10, doctorModel.getDoctorWardNo());
            stmt.setString(11, doctorModel.getDoctorQualification());
            stmt.setString(12, doctorModel.getDoctorExperience());
            stmt.setString(13, doctorModel.getDoctorSpecialization());
            stmt.setString(14, doctorModel.getImagePath());
            stmt.setInt(15, doctorModel.getDoctorID());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}