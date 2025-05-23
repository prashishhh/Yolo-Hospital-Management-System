<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Doctor</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/add_doctor.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/pages/header.jsp" />

    <div class="main-content">
        <div class="card">
            <div class="form-container">
                <div class="form-header">
                    <h1 class="form-title">Add Doctor</h1>
                </div>

                <!-- Display error message if available -->
                <c:if test="${not empty error}">
                    <p class="error-message">${error}</p>
                </c:if>
                <!-- Display success message if available -->
                <c:if test="${not empty success}">
                    <p class="success-message">${success}</p>
                </c:if>

                <form action="${pageContext.request.contextPath}/add-doctor" method="post" enctype="multipart/form-data">
                    <div class="form-section-title">Personal Information</div>

                    <div class="form-row">
                        <div class="form-group">
                            <label>First Name</label>
                            <input type="text" name="doctorFirstName" value="${doctorFirstName}" placeholder="Enter first name" required>
                        </div>
                        <div class="form-group">
                            <label>Last Name</label>
                            <input type="text" name="doctorLastName" value="${doctorLastName}" placeholder="Enter last name" required>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label>Date of Birth</label>
                            <input type="date" name="dateOfBirth" value="${dateOfBirth}" required>
                        </div>
                        <div class="form-group">
                            <label>Gender</label>
                            <select name="doctorGender" required>
                                <option value="" disabled ${doctorGender == null ? 'selected' : ''}>Select gender</option>
                                <option value="Male" ${doctorGender == 'Male' ? 'selected' : ''}>Male</option>
                                <option value="Female" ${doctorGender == 'Female' ? 'selected' : ''}>Female</option>
                                <option value="Other" ${doctorGender == 'Other' ? 'selected' : ''}>Other</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label>Upload Profile Picture</label>
                        <input type="file" name="doctorProfile">
                    </div>

                    <div class="form-group">
                        <label>Phone Number</label>
                        <input type="tel" name="doctorPhoneNumber" value="${doctorPhoneNumber}" placeholder="Enter contact number" required>
                    </div>

                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" name="doctorEmail" value="${doctorEmail}" placeholder="Enter email address" required>
                    </div>

                    <div class="form-section-title">Address Information</div>

                    <div class="form-row">
                        <div class="form-group">
                            <label>City</label>
                            <input type="text" name="doctorCity" value="${doctorCity}" placeholder="Enter city" required>
                        </div>
                        <div class="form-group">
                            <label>District</label>
                            <input type="text" name="doctorDistrict" value="${doctorDistrict}" placeholder="Enter district" required>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label>Municipality</label>
                            <input type="text" name="doctorMunicipality" value="${doctorMunicipality}" placeholder="Enter municipality" required>
                        </div>
                        <div class="form-group">
                            <label>Ward</label>
                            <input type="text" name="doctorWard" value="${doctorWard}" placeholder="Enter ward number" required>
                        </div>
                    </div>

                    <div class="form-section-title">Professional Information</div>

                    <div class="form-group">
                        <label>Qualification</label>
                        <input type="text" name="doctorQualification" value="${doctorQualification}" placeholder="Enter qualification" required>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label>Experience (Years)</label>
                            <input type="number" name="doctorExperience" value="${doctorExperience}" placeholder="Enter years of experience" required>
                        </div>
                        <div class="form-group">
                            <label>Specialization</label>
                            <select name="doctorSpecialization" required>
                                <option value="" disabled ${doctorSpecialization == null ? 'selected' : ''}>Select specialization</option>
                                <option value="Cardiology" ${doctorSpecialization == 'Cardiology' ? 'selected' : ''}>Cardiology</option>
                                <option value="Dermatology" ${doctorSpecialization == 'Dermatology' ? 'selected' : ''}>Dermatology</option>
                                <option value="Neurology" ${doctorSpecialization == 'Neurology' ? 'selected' : ''}>Neurology</option>
                                <option value="Orthopedics" ${doctorSpecialization == 'Orthopedics' ? 'selected' : ''}>Orthopedics</option>
                                <option value="Pediatrics" ${doctorSpecialization == 'Pediatrics' ? 'selected' : ''}>Pediatrics</option>
                                <option value="Psychiatry" ${doctorSpecialization == 'Psychiatry' ? 'selected' : ''}>Psychiatry</option>
                                <option value="General Medicine" ${doctorSpecialization == 'General Medicine' ? 'selected' : ''}>General Medicine</option>
                                <option value="General Surgery" ${doctorSpecialization == 'General Surgery' ? 'selected' : ''}>General Surgery</option>
                                <option value="Gynecology" ${doctorSpecialization == 'Gynecology' ? 'selected' : ''}>Gynecology</option>
                                <option value="Other" ${doctorSpecialization == 'Other' ? 'selected' : ''}>Other</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button type="button" class="form-btn btn-secondary" onclick="window.history.back()">Cancel</button>
                        <button type="submit" class="form-btn btn-primary">Add Doctor</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>