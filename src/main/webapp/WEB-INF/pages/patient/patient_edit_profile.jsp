<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <%-- Declares the page as a JSP page using Java language and sets encoding --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patient Edit Profile</title> <%-- Page title (can be customized) --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/patient_edit_profile.css" /> <%-- Linking external CSS for styling --%>
</head>
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />
	<div class="main-content">
    <div class="card">
      <div class="form-container">
        <div class="form-header">
          <h1 class="form-title">Edit Profile</h1>
          <div class="patient-id">ID: ${patient.userId}</div>
        </div>
        
        <!-- Display success message if available -->
        <c:if test="${not empty success}">
          <div class="success-message">${success}</div>
        </c:if>
        
        <!-- Display error message if available -->
        <c:if test="${not empty error}">
          <div class="error-message">${error}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/patient-edit-profile" method="post" enctype="multipart/form-data">
          
          <div class="form-section-title">Personal Information</div>
          
          <div class="form-row">
            <div class="form-group">
              <label>First Name</label>
              <input type="text" name="firstName" value="${patient.firstName}" required>
            </div>
            
            <div class="form-group">
              <label>Last Name</label>
              <input type="text" name="lastName" value="${patient.lastName}" required>
            </div>
          </div>
          
          <div class="form-row">
            <div class="form-group">
              <label>Date of Birth</label>
              <input type="date" name="dateOfBirth" value="${patient.dateOfBirth}" required>
            </div>
            
            <div class="form-group">
              <label>Gender</label>
              <select name="gender" required>
                <option value="" disabled>Select gender</option>
                <option value="Male" ${fn:toLowerCase(patient.gender) == 'male' ? 'selected' : ''}>Male</option>
                <option value="Female" ${fn:toLowerCase(patient.gender) == 'female' ? 'selected' : ''}>Female</option>
                 <option value="Other" ${fn:toLowerCase(patient.gender) == 'other' ? 'selected' : ''}>Other</option>
              </select>
            </div>
          </div>

          <div class="form-group">
            <label>Profile Picture</label>
              <img src="${pageContext.request.contextPath}/resources/images/patient/${patient.imagePath}" alt="patient profile picture">
            <c:if test="${empty patient.imagePath}">
              <div class="no-image">No profile picture available</div>
            </c:if>
            <input type="file" name="profilePicture" accept="image/*">
            <small>Leave blank if you don't want to change your profile picture</small>
          </div>
          
          <div class="form-group">
            <label>Phone Number</label>
            <input type="tel" name="phoneNumber" value="${patient.phoneNumber}" required>
          </div>

          <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" value="${patient.email}" required>
          </div>
          
          <div class="form-section-title">Address Information</div>
          
          <div class="form-row">
            <div class="form-group">
              <label>City</label>
              <input type="text" name="city" value="${patient.city}" required>
            </div>
            
            <div class="form-group">
              <label>District</label>
              <input type="text" name="district" value="${patient.district}" required>
            </div>
          </div>
          
          <div class="form-row">
            <div class="form-group">
              <label>Municipality</label>
              <input type="text" name="municipality" value="${patient.municipality}" required>
            </div>
            
            <div class="form-group">
              <label>Ward</label>
              <input type="text" name="ward" value="${patient.wardNo}" required>
            </div>
          </div>
          
          <div class="form-actions">
            <a href="${pageContext.request.contextPath}/patient-profile" class="form-btn btn-secondary" style="text-decoration: none">Cancel</a>
            <button type="submit" class="form-btn btn-primary">Update Profile</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</body>
</body>
</html>
