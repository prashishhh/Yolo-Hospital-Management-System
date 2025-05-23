package com.yolo.service.admin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.yolo.config.DbConfig;
import com.yolo.model.DoctorModel;

/**
 * AddDoctorService handles the addition of new doctors to the database.
 */
public class AddDoctorService {

    private Connection dbConn;

    /**
     * Constructor initializes the database connection.
     */
    public AddDoctorService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Adds a new doctor to the database.
     *
     * @param doctorModel the doctor details to be added
     * @return Boolean indicating the success of the operation
     */
    public Boolean addDoctor(DoctorModel doctorModel) {
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return null;
        }

        String insertQuery = "INSERT INTO Doctor (doctorFirstName, doctorLastName, dateOfBirth, doctorGender, " +
                "doctorPhoneNumber, doctorEmail, doctorCity, doctorDistrict, doctorMunicipality, doctorWard, " +
                "doctorQualification, doctorExperience, doctorSpecialization, imagePath) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = dbConn.prepareStatement(insertQuery)) {
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

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during doctor addition: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}