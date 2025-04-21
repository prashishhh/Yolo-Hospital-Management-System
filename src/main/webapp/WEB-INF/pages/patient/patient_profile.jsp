<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <%-- JSP directive to define page settings --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title> <%-- Placeholder for page title --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/patient_profile.css" /> <%-- Linking external stylesheet --%>
</head>
<body>

<jsp:include page="/WEB-INF/pages/header.jsp" />
<main> <%-- Main content wrapper --%>
  
    <div class="profile-container"> <%-- Container for profile layout --%>
      <div class="profile-sidebar"> <%-- Sidebar for profile image and basic info --%>
        <div class="profile-picture">
          <i class="fas fa-user"><img src= "${pageContext.request.contextPath}/resources/images/patient/pp1.jpg" alt="Healthcare professionals"></i> <%-- Placeholder icon for user picture --%>
        </div>
        <div class="profile-info"> <%-- Basic profile information --%>
          <h3 class="profile-name">Katyani Bajgain</h3>
          <p class="profile-id">ID: #5233</p>
          <p><i class="fas fa-phone"></i> +977 9845603050</p>
          <p><i class="fas fa-envelope"></i> katyani123@gmail.com</p>
        </div>
        <a href="${pageContext.request.contextPath}/patient-edit-profile" class="btn edit-profile" id="edit-profile-btn">Edit Profile</a> <%-- Link to edit profile page --%>
      </div>
      
      <div class="profile-content"> <%-- Right section with detailed info --%>
        <!-- Personal Information Section -->
        <div class="profile-section">
          <h3 class="section-title">Personal Information</h3>
          <div class="info-grid"> <%-- Grid layout for displaying info --%>
            <div class="info-item">
              <p class="info-label">User ID</p>
              <p class="info-value">#5233</p>
            </div>
            <div class="info-item">
              <p class="info-label">First Name</p>
              <p class="info-value">Katyani</p>
            </div>
            <div class="info-item">
              <p class="info-label">Last Name</p>
              <p class="info-value">Bajgain</p>
            </div>
            <div class="info-item">
              <p class="info-label">Date of Birth</p>
              <p class="info-value">30/06/2000</p>
            </div>
            <div class="info-item">
              <p class="info-label">Gender</p>
              <p class="info-value">Female</p>
            </div>
            <div class="info-item">
              <p class="info-label">Phone Number</p>
              <p class="info-value">+977 9845603050</p>
            </div>
            <div class="info-item">
              <p class="info-label">Email</p>
              <p class="info-value">katyani123@gmail.com</p>
            </div>
            <div class="info-item">
              <p class="info-label">City</p>
              <p class="info-value">Kathmandu</p>
            </div>
            <div class="info-item">
              <p class="info-label">District</p>
              <p class="info-value">Kathmandu</p>
            </div>
            <div class="info-item">
              <p class="info-label">Municipality</p>
              <p class="info-value">Kathmandu Metropolitan</p>
            </div>
            <div class="info-item">
              <p class="info-label">Ward</p>
              <p class="info-value">5</p>
            </div>
            <div class="info-item">
              <p class="info-label">Username</p>
              <p class="info-value">katyani2000</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div> <%-- This extra closing div seems unmatched. Consider verifying --%>
</main>
</body>
</html>
