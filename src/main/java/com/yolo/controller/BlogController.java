package com.yolo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.yolo.service.BlogService;

/**
 * Servlet implementation class BlogController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/blog" })
public class BlogController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BlogService blogService;

    /**
     * Constructor initializing the service
     */
    public BlogController() {
        this.blogService = new BlogService();
    }

    /**
     * Handles GET requests to display the blog list
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the search query from the request
        String searchQuery = request.getParameter("search");

        // Get blog posts and set as request attribute
        request.setAttribute("blogPosts", blogService.getBlogPosts(searchQuery));

        // Forward to the JSP
        request.getRequestDispatcher("/WEB-INF/pages/blog.jsp").forward(request, response);
    }

    /**
     * Handles POST requests by delegating to doGet
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}