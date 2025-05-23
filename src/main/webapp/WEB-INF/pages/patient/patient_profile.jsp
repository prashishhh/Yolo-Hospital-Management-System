<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patient Profile</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/patient_profile.css" />
<style>
</style>
</head>
<body>

<jsp:include page="/WEB-INF/pages/header.jsp" />
<main>

    <c:if test="${not empty error}">
        <p class="error-message">${error}</p>
    </c:if>
    <c:if test="${not empty success}">
        <p class="success-message">${success}</p>
    </c:if>
    <c:if test="${not empty param.success}">
        <p class="success-message">${param.success}</p>
    </c:if>

    <div class="profile-container">
        <div class="profile-sidebar">
            <div class="profile-picture">
                <i class="fas fa-user"><img src="${pageContext.request.contextPath}/resources/images/patient/${patientDetails.imagePath}" alt="patient profile picture"></i>
            </div>
            <div class="profile-info">
                <h3 class="profile-name">${patientDetails.firstName} ${patientDetails.lastName}</h3>
                <p class="profile-id">ID: ${patientDetails.userId}</p>
                <p><i class="fas fa-phone"></i> ${patientDetails.phoneNumber}</p>
                <p><i class="fas fa-envelope"></i> ${patientDetails.email}</p>
            </div>
            <form action="${pageContext.request.contextPath}/patient-profile" method="post">
                <input type="hidden" name="action" value="editProfile">
                <button type="submit" class="btn edit-profile" id="edit-profile-btn">Edit Profile</button>
            </form>
            <br>
            <a href="${pageContext.request.contextPath}/patient-change-password" class="btn edit-profile" id="edit-profile-btn">Change Password</a>
            <form action="${pageContext.request.contextPath}/patient-profile" method="post">
                <input type="hidden" name="action" value="deleteProfile">
                <button type="submit" class="btn delete-profile" id="delete-profile-btn">Delete Profile</button>
            </form>
        </div>

        <div class="profile-content">
            <!-- Personal Information Section -->
            <div class="profile-section">
                <h3 class="section-title">Personal Information</h3>
                <div class="info-grid">
                    <div class="info-item">
                        <p class="info-label">User ID</p>
                        <p class="info-value">${patientDetails.userId}</p>
                    </div>
                    <div class="info-item">
                        <p class="info-label">First Name</p>
                        <p class="info-value">${patientDetails.firstName}</p>
                    </div>
                    <div class="info-item">
                        <p class="info-label">Last Name</p>
                        <p class="info-value">${patientDetails.lastName}</p>
                    </div>
                    <div class="info-item">
                        <p class="info-label">Date of Birth</p>
                        <p class="info-value">${patientDetails.dateOfBirth}</p>
                    </div>
                    <div class="info-item">
                        <p class="info-label">Gender</p>
                        <p class="info-value">${patientDetails.gender}</p>
                    </div>
                    <div class="info-item">
                        <p class="info-label">Phone Number</p>
                        <p class="info-value">${patientDetails.phoneNumber}</p>
                    </div>
                    <div class="info-item">
                        <p class="info-label">Email</p>
                        <p class="info-value">${patientDetails.email}</p>
                    </div>
                    <div class="info-item">
                        <p class="info-label">City</p>
                        <p class="info-value">${patientDetails.city}</p>
                    </div>
                    <div class="info-item">
                        <p class="info-label">District</p>
                        <p class="info-value">${patientDetails.district}</p>
                    </div>
                    <div class="info-item">
                        <p class="info-label">Municipality</p>
                        <p class="info-value">${patientDetails.municipality}</p>
                    </div>
                    <div class="info-item">
                        <p class="info-label">Ward</p>
                        <p class="info-value">${patientDetails.wardNo}</p>
                    </div>
                    <div class="info-item">
                        <p class="info-label">Username</p>
                        <p class="info-value">${patientDetails.userName}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>