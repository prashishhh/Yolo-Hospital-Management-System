package com.yolo.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import com.yolo.model.BlogModel;
import com.yolo.service.admin.AddBlogService;
import com.yolo.util.ValidationUtil;

/**
 * Servlet implementation class AddBlogController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/add-blog" })
public class AddBlogController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private final AddBlogService addBlogService;
    private static final int ADMIN_USER_ID = 12;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBlogController() {
        this.addBlogService = new AddBlogService();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/admin/add_blog.jsp").forward(request, response);
    }

    /**
     * Handles POST requests for adding a blog. Validates input and saves the blog details.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Validate form input
            String validationMessage = validateBlogForm(request);
            if (validationMessage != null) {
                handleError(request, response, validationMessage);
                return;
            }

            // Extract BlogModel from request
            BlogModel blogModel = extractBlogModel(request);

            // Attempt to add blog with admin userID
            Boolean isAdded = addBlogService.addBlog(blogModel, ADMIN_USER_ID);

            if (isAdded == null) {
                handleError(request, response, "Server is under maintenance. Please try again later!");
            } else if (isAdded) {
                handleSuccess(request, response, "Blog successfully added!", "/WEB-INF/pages/admin/admin_dashboard.jsp");
            } else {
                handleError(request, response, "Could not add blog. Please try again later!");
            }
        } catch (Exception e) {
            handleError(request, response, "An unexpected error occurred. Please try again later!");
            e.printStackTrace();
        }
    }

    /**
     * Validates the blog form input.
     */
    private String validateBlogForm(HttpServletRequest request) {
        String blogTitle = request.getParameter("blogTitle");
        String blogPublishedDate = request.getParameter("blogPublishedDate");
        String blogDescription = request.getParameter("blogDescription");

        // Check for null or empty fields
        if (ValidationUtil.isNullOrEmpty(blogTitle))
            return "Blog title is required.";
        if (ValidationUtil.isNullOrEmpty(blogPublishedDate))
            return "Blog published date is required.";
        if (ValidationUtil.isNullOrEmpty(blogDescription))
            return "Blog description is required.";

        return null; // All validations passed
    }

    /**
     * Extracts blog details from the request and creates a BlogModel.
     */
    private BlogModel extractBlogModel(HttpServletRequest request) {
        String blogTitle = request.getParameter("blogTitle");
        String blogPublishedDate = request.getParameter("blogPublishedDate");
        String blogDescription = request.getParameter("blogDescription");

        // Create and return BlogModel
        return new BlogModel(blogTitle, LocalDate.parse(blogPublishedDate), blogDescription);
    }

    /**
     * Handles successful blog addition.
     */
    private void handleSuccess(HttpServletRequest request, HttpServletResponse response, String message, String redirectPage)
            throws ServletException, IOException {
        request.setAttribute("success", message);
        request.getRequestDispatcher(redirectPage).forward(request, response);
    }

    /**
     * Handles errors during blog addition.
     */
    private void handleError(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute("error", message);
        request.setAttribute("blogTitle", request.getParameter("blogTitle"));
        request.setAttribute("blogPublishedDate", request.getParameter("blogPublishedDate"));
        request.setAttribute("blogDescription", request.getParameter("blogDescription"));
        request.getRequestDispatcher("/WEB-INF/pages/admin/add_blog.jsp").forward(request, response);
    }
}