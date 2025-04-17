<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page import="jakarta.servlet.http.HttpServletRequest"%>

<%
    String userRole = null;
    String currentUser = null;

    if (session != null) {
        userRole = (String) session.getAttribute("userRole");
        currentUser = (String) session.getAttribute("username");
    }

    pageContext.setAttribute("currentUser", currentUser);
    pageContext.setAttribute("userRole", userRole);
%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css" />

<header class="site-header">
    <div class="site-logo">Yolo Hospital</div>

    <nav class="main-nav">
        <ul>
            <% if ("Admin".equals(userRole)) { %>
                <li><a href="${pageContext.request.contextPath}/admin/admin-dashboard">Overview</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/admin-doctors">Doctor</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/admin-patients">Patients</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/admin-appointment">Appointments</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/admin-rooms">Rooms</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/admin-payments">Payments</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/admin-addblog">Add Blog</a></li>
            <% } else if ("Patient".equals(userRole)) { %>
                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/profile">Profile</a></li>
                <li><a href="${pageContext.request.contextPath}/appointments">Appointments</a></li>
                <li><a href="${pageContext.request.contextPath}/treatments">Treatments</a></li>
                <li><a href="${pageContext.request.contextPath}/payments">Payment History</a></li>
                <li><a href="${pageContext.request.contextPath}/blog">Blog</a></li>
                <li><a href="${pageContext.request.contextPath}/about">About Us</a></li>
            <% } else { %>
                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/about">About Us</a></li>
                <li><a href="${pageContext.request.contextPath}/blog">Blog</a></li>
            <% } %>
        </ul>
    </nav>

    <div class="header-actions">
        <% if (currentUser != null) { %>
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <input type="submit" class="btn btn--outline" value="Logout" />
            </form>
        <% } else { %>
            <a href="${pageContext.request.contextPath}/login" class="btn btn--outline">Login</a>
            <a href="${pageContext.request.contextPath}/register" class="btn btn--primary">Register</a>
        <% } %>
    </div>
</header>
