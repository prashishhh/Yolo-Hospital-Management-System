package com.yolo.service.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.yolo.config.DbConfig;
import com.yolo.model.BlogModel;

/**
 * AddBlogService handles the addition of new blogs to the database and associates them with a user.
 */
public class AddBlogService {

    private Connection dbConn;

    /**
     * Constructor initializes the database connection.
     */
    public AddBlogService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Adds a new blog to the database and associates it with a user in the UserBlog table.
     *
     * @param blogModel the blog details to be added
     * @param userID the ID of the admin user adding the blog
     * @return Boolean indicating the success of the operation
     */
    public Boolean addBlog(BlogModel blogModel, int userID) {
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return null;
        }

        String insertBlogQuery = "INSERT INTO Blog (blogTitle, blogPublishedDate, blogDescription) VALUES (?, ?, ?)";
        String insertUserBlogQuery = "INSERT INTO UserBlog (userID, blogID) VALUES (?, ?)";

        try {
            dbConn.setAutoCommit(false); // Start transaction

            // Insert into Blog table
            try (PreparedStatement blogStmt = dbConn.prepareStatement(insertBlogQuery, Statement.RETURN_GENERATED_KEYS)) {
                blogStmt.setString(1, blogModel.getBlogTitle());
                blogStmt.setObject(2, blogModel.getBlogPublishedDate());
                blogStmt.setString(3, blogModel.getBlogDescription());

                int rowsAffected = blogStmt.executeUpdate();
                if (rowsAffected == 0) {
                    dbConn.rollback();
                    return false;
                }

                // Get the generated blogID
                try (ResultSet generatedKeys = blogStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int blogID = generatedKeys.getInt(1);

                        // Insert into UserBlog table to associate the blog with the user
                        try (PreparedStatement userBlogStmt = dbConn.prepareStatement(insertUserBlogQuery)) {
                            userBlogStmt.setInt(1, userID);
                            userBlogStmt.setInt(2, blogID);

                            if (userBlogStmt.executeUpdate() == 0) {
                                dbConn.rollback();
                                return false;
                            }
                        }
                    } else {
                        dbConn.rollback();
                        return false;
                    }
                }
            }

            dbConn.commit(); // Commit transaction
            return true;
        } catch (SQLException e) {
            System.err.println("Error during blog addition: " + e.getMessage());
            e.printStackTrace();
            try {
                dbConn.rollback(); // Rollback on error
            } catch (SQLException rollbackEx) {
                System.err.println("Rollback error: " + rollbackEx.getMessage());
                rollbackEx.printStackTrace();
            }
            return false;
        } finally {
            try {
                dbConn.setAutoCommit(true); // Reset auto-commit
            } catch (SQLException e) {
                System.err.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }
}