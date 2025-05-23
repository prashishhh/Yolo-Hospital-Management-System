package com.yolo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.yolo.config.DbConfig;
import com.yolo.model.DoctorModel;

public class OurDoctorsService {
    private Connection dbConn;
    private boolean isConnectionError = false;

    /**
     * Constructor that initializes the database connection. Sets the connection
     * error flag if the connection fails.
     */
    public OurDoctorsService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    /**
     * Retrieves a list of doctor profiles, optionally filtered by a search query
     */
    public List<DoctorModel> getDoctorList(String searchQuery) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return new ArrayList<>();
        }

        String query = "SELECT doctorID, doctorFirstName, doctorLastName, doctorQualification, doctorExperience, doctorSpecialization, imagePath FROM Doctor";

        // If there's a search query, modify the query to include a LIKE filter
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            query += " WHERE doctorFirstName LIKE ? OR doctorLastName LIKE ? OR doctorSpecialization LIKE ?";
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
            List<DoctorModel> doctorList = new ArrayList<>();

            while (result.next()) {
                doctorList.add(new DoctorModel(
                    result.getString("doctorFirstName"),
                    result.getInt("doctorID"),
                    result.getString("doctorLastName"),
                    result.getString("doctorQualification"),
                    result.getString("doctorSpecialization"),
                    result.getString("doctorExperience"),
                    result.getString("imagePath")
                ));
            }
            return doctorList;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}