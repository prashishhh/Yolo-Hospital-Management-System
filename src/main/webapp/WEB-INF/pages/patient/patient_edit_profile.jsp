<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <%-- Declares the page as a JSP page using Java language and sets encoding --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title> <%-- Page title (can be customized) --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/patient_edit_profile.css" /> <%-- Linking external CSS for styling --%>
</head>
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />
  <div class="main-content"> <%-- Main container for the edit profile section --%>
    <div class="card"> <%-- Card-style layout wrapper --%>
      <div class="form-container"> <%-- Container holding the edit form --%>
        <div class="form-header"> <%-- Header section for the form --%>
          <h1 class="form-title">Edit Profile</h1> <%-- Title of the form --%>
          <div class="patient-id">ID: 1</div> <%-- Static patient ID display (ideally should be dynamic) --%>
        </div>
        
        <form> <%-- Start of the form (action/method can be added later) --%>
          
          <div class="form-section-title">Personal Information</div> <%-- Section label --%>
          
          <div class="form-row"> <%-- Group of inputs in a row --%>
            <div class="form-group">
              <label>First Name</label>
              <input type="text" name="firstName" value="Sarah" required> <%-- Pre-filled input field --%>
            </div>
            
            <div class="form-group">
              <label>Last Name</label>
              <input type="text" name="lastName" value="Johnson" required>
            </div>
          </div>
          
          <div class="form-row">
            <div class="form-group">
              <label>Date of Birth</label>
              <input type="date" name="dateOfBirth" value="1990-03-15" required> <%-- DOB field with pre-set value --%>
            </div>
            
            <div class="form-group">
              <label>Gender</label>
              <select name="gender" required> <%-- Gender selection --%>
                <option value="" disabled>Select gender</option>
                <option value="Male">Male</option>
                <option value="Female" selected>Female</option> <%-- Selected option for Female --%>
                <option value="Other">Other</option>
                <option value="Prefer not to say">Prefer not to say</option>
              </select>
            </div>
          </div>

          <div class="form-group">
            <label>Profile Picture</label>
            <img src="WhatsApp Image 2025-04-15 at 12.05.48_b82d5424.jpg" alt="Patient profile" class="profile-preview"> <%-- Static preview of profile picture --%>
            <input type="file" name="profilePicture"> <%-- File input for uploading a new profile image --%>
          </div>
          
          <div class="form-group">
            <label>Phone Number</label>
            <input type="tel" name="phoneNumber" value="+1 555-987-6543" required>
          </div>

          <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" value="sarah.johnson@example.com" required>
          </div>
          
          <div class="form-section-title">Address Information</div> <%-- Section label for address --%>
          
          <div class="form-row">
            <div class="form-group">
              <label>City</label>
              <input type="text" name="city" value="San Francisco" required>
            </div>
            
            <div class="form-group">
              <label>District</label>
              <input type="text" name="district" value="Marina" required>
            </div>
          </div>
          
          <div class="form-row">
            <div class="form-group">
              <label>Municipality</label>
              <input type="text" name="municipality" value="North Beach" required>
            </div>
            
            <div class="form-group">
              <label>Ward</label>
              <input type="text" name="ward" value="3" required>
            </div>
          </div>
          
          <div class="form-actions"> <%-- Action buttons --%>
            <button type="button" class="form-btn btn-secondary">Cancel</button> <%-- Cancel button (no default behavior) --%>
            
            <button type="submit" class="form-btn btn-primary">Update Profile</button> <%-- Submit button to update profile --%>
          </div>
        </form> <%-- End of form --%>
      </div>
    </div>
  </div>
</body>
</html>
