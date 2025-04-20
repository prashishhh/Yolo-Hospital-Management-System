<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="userRole" value="${sessionScope.userRole}" />
<c:set var="currentUser" value="${sessionScope.username}" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css" />

<header class="site-header">
    <div class="site-logo">Yolo Hospital</div>

    <nav class="main-nav">
        <ul>
            <!-- Admin Menu -->
            <c:if test="${userRole == 'Admin'}">
                <li><a href="${pageContext.request.contextPath}/admin-dashboard">Overview</a></li>
                <li><a href="${pageContext.request.contextPath}/admin-doctor-list">Doctor</a></li>
                <li><a href="${pageContext.request.contextPath}/admin-patient-list">Patients</a></li>
                <li><a href="${pageContext.request.contextPath}/admin-appointment-list">Appointments</a></li>
                <li><a href="${pageContext.request.contextPath}/admin-treatment-list">Treatments</a></li>
                <li><a href="${pageContext.request.contextPath}/admin-room-list">Rooms</a></li>
                <li><a href="${pageContext.request.contextPath}/admin-payment-list">Payments</a></li>
                <li><a href="${pageContext.request.contextPath}/add-blog">Add Blog</a></li>
            </c:if>

            <!-- Patient Menu -->
            <c:if test="${userRole == 'Patient'}">
                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/profile">Profile</a></li>
                <li><a href="${pageContext.request.contextPath}/appointments">Appointments</a></li>
                <li><a href="${pageContext.request.contextPath}/treatments">Treatments</a></li>
                <li><a href="${pageContext.request.contextPath}/payments">Payment History</a></li>
                <li><a href="${pageContext.request.contextPath}/blog">Blog</a></li>
                <li><a href="${pageContext.request.contextPath}/about">About Us</a></li>
            </c:if>
		
            <!-- Default (Guest or other roles) -->
            <c:if test="${userRole != 'Admin' and userRole != 'Patient'}">
                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/about">About Us</a></li>
                <li><a href="${pageContext.request.contextPath}/blog">Blog</a></li>
            </c:if>
        </ul>
    </nav>
	<a href="${pageContext.request.contextPath}/book-appointment" class="btn btn--primary">Book Appointment</a>
    <div class="header-actions">
        <c:choose>
            <c:when test="${not empty currentUser}">
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <input type="submit" class="btn btn--outline" value="Logout" />
                </form>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/login" class="btn btn--outline">Login</a>
                <a href="${pageContext.request.contextPath}/register" class="btn btn--primary">Register</a>
            </c:otherwise>
        </c:choose>
    </div>
</header>
