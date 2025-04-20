<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Doctor</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/add_doctor.css" />
</head>
<body>

	<jsp:include page="/WEB-INF/pages/header.jsp" />
	
	<div class="main-content">
    <div class="card">
      <div class="form-container">
        <div class="form-header">
          <h1 class="form-title">Add Doctor</h1>
        </div>
        
        <form>
          
          <div class="form-section-title">Personal Information</div>
          
          <div class="form-row">
            <div class="form-group">
              <label>First Name</label>
              <input type="text" name="doctorFirstName" placeholder="Enter first name" required>
            </div>
            
            <div class="form-group">
              <label>Last Name</label>
              <input type="text" name="doctorLastName" placeholder="Enter last name" required>
            </div>
          </div>
          
          <div class="form-row">
            <div class="form-group">
              <label>Date of Birth</label>
              <input type="date" name="dateOfBirth" required>
            </div>
            
            <div class="form-group">
              <label>Gender</label>
              <select name="doctorGender" required>
                <option value="" disabled selected>Select gender</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
                <option value="Prefer not to say">Prefer not to say</option>
              </select>
            </div>
          </div>

          <div class="form-group">
            <label>Upload Profile Picture</label>
            <input type="file" name="doctorProfile">
          </div>
          
          <div class="form-group">
            <label>Phone Number</label>
            <input type="tel" name="doctorPhoneNumber" placeholder="Enter contact number" required>
          </div>

          <div class="form-group">
            <label>Email</label>
            <input type="email" name="doctorEmail" placeholder="Enter email address" required>
          </div>
          
          <div class="form-section-title">Address Information</div>
          
          <div class="form-row">
            <div class="form-group">
              <label>City</label>
              <input type="text" name="doctorCity" placeholder="Enter city" required>
            </div>
            
            <div class="form-group">
              <label>District</label>
              <input type="text" name="doctorDistrict" placeholder="Enter district" required>
            </div>
          </div>
          
          <div class="form-row">
            <div class="form-group">
              <label>Municipality</label>
              <input type="text" name="doctorMunicipality" placeholder="Enter municipality" required>
            </div>
            
            <div class="form-group">
              <label>Ward</label>
              <input type="text" name="doctorWard" placeholder="Enter ward number" required>
            </div>
          </div>
          
          <div class="form-section-title">Professional Information</div>
          
          <div class="form-group">
            <label>Qualification</label>
            <input type="text" name="doctorQualification" placeholder="Enter qualification" required>
          </div>
          
          <div class="form-row">
            <div class="form-group">
              <label>Experience (Years)</label>
              <input type="number" name="doctorExperience" placeholder="Enter years of experience" required>
            </div>
            
            <div class="form-group">
              <label>Specialization</label>
              <select name="doctorSpecialization" required>
                <option value="" disabled selected>Select specialization</option>
                <option value="Cardiology">Cardiology</option>
                <option value="Dermatology">Dermatology</option>
                <option value="Neurology">Neurology</option>
                <option value="Orthopedics">Orthopedics</option>
                <option value="Pediatrics">Pediatrics</option>
                <option value="Psychiatry">Psychiatry</option>
                <option value="General Medicine">General Medicine</option>
                <option value="General Surgery">General Surgery</option>
                <option value="Gynecology">Gynecology</option>
                <option value="Other">Other</option>
              </select>
            </div>
          </div>
          
          <div class="form-actions">
            <button type="button" class="form-btn btn-secondary">Cancel</button>
            <button type="submit" class="form-btn btn-primary">Add Doctor</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</body>
</html>