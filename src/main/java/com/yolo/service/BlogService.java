package com.yolo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.yolo.config.DbConfig;
import com.yolo.model.BlogModel;
import java.time.LocalDate;

public class BlogService {
    private Connection dbConn;
    private boolean isConnectionError = false;

    /**
     * Constructor that initializes the database connection. Sets the connection
     * error flag if the connection fails.
     */
    public BlogService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    /**
     * Retrieves a list of blog posts, optionally filtered by a search query
     */
    public List<BlogModel> getBlogPosts(String searchQuery) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return new ArrayList<>();
        }

        String query = "SELECT blogID, blogTitle, blogPublishedDate, blogDescription FROM Blog";
        
        // If there's a search query, modify the query to include a LIKE filter
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            query += " WHERE blogTitle LIKE ? OR blogDescription LIKE ?";
        }

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            // If a search query is provided, set the parameters for LIKE queries
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                String searchPattern = "%" + searchQuery + "%";
                stmt.setString(1, searchPattern);
                stmt.setString(2, searchPattern);
            }

            ResultSet result = stmt.executeQuery();
            List<BlogModel> blogPosts = new ArrayList<>();

            while (result.next()) {
                blogPosts.add(new BlogModel(
                    result.getInt("blogID"),
                    result.getString("blogTitle"),
                    result.getDate("blogPublishedDate").toLocalDate(),
                    result.getString("blogDescription")
                ));
            }
            return blogPosts;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}