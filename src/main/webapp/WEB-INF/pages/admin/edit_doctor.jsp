<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Doctor</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/edit_doctor.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/pages/header.jsp" />
    <div class="main-content">
        <div class="card">
            <div class="form-container">
                <div class="form-header">
                    <h1 class="form-title">Edit Doctor</h1>
                    <div class="doctor-id">ID: ${doctor.doctorID}</div>
                </div>
                
                <c:if test="${not empty success}">
                    <div class="success-message">${success}</div>
                </c:if>
                
                <c:if test="${not empty error}">
                    <div class="error-message">${error}</div>
                </c:if>
                
                <form action="${pageContext.request.contextPath}/edit-doctor" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="doctorID" value="${doctor.doctorID}">
                    
                    <div class="form-section-title">Personal Information</div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label>First Name</label>
                            <input type="text" name="doctorFirstName" value="${doctor.doctorFirstName}" required>
                        </div>
                        <div class="form-group">
                            <label>Last Name</label>
                            <input type="text" name="doctorLastName" value="${doctor.doctorLastName}" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label>Date of Birth</label>
                            <input type="date" name="dateOfBirth" value="${doctor.doctorDateOfBirth}" required>
                        </div>
                        <div class="form-group">
                            <label>Gender</label>
                            <select name="doctorGender" required>
                                <option value="" disabled>Select gender</option>
                                <option value="Male" ${fn:toLowerCase(doctor.doctorGender) == 'male' ? 'selected' : ''}>Male</option>
                                <option value="Female" ${fn:toLowerCase(doctor.doctorGender) == 'female' ? 'selected' : ''}>Female</option>
                                <option value="Other" ${fn:toLowerCase(doctor.doctorGender) == 'other' ? 'selected' : ''}>Other</option>
                                <option value="Prefer not to say" ${fn:toLowerCase(doctor.doctorGender) == 'prefer not to say' ? 'selected' : ''}>Prefer not to say</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label>Profile Picture</label>
                        <c:if test="${not empty doctor.imagePath}">
                            <img src="${pageContext.request.contextPath}/resources/images/doctors/${doctor.imagePath}" alt="Doctor profile" class="profile-preview">
                        </c:if>
                        <c:if test="${empty doctor.imagePath}">
                            <div class="no-image">No profile picture available</div>
                        </c:if>
                        <input type="file" name="doctorProfile" accept="image/*">
                        <small>Leave blank if you don't want to change the profile picture</small>
                    </div>
                    
                    <div class="form-group">
                        <label>Phone Number</label>
                        <input type="tel" name="doctorPhoneNumber" value="${doctor.doctorPhoneNumber}" required>
                    </div>

                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" name="doctorEmail" value="${doctor.doctorEmail}" required>
                    </div>
                    
                    <div class="form-section-title">Address Information</div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label>City</label>
                            <input type="text" name="doctorCity" value="${doctor.doctorCity}" required>
                        </div>
                        <div class="form-group">
                            <label>District</label>
                            <input type="text" name="doctorDistrict" value="${doctor.doctorDistrict}" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label>Municipality</label>
                            <input type="text" name="doctorMunicipality" value="${doctor.doctorMunicipality}" required>
                        </div>
                        <div class="form-group">
                            <label>Ward</label>
                            <input type="text" name="doctorWard" value="${doctor.doctorWardNo}" required>
                        </div>
                    </div>
                    
                    <div class="form-section-title">Professional Information</div>
                    
                    <div class="form-group">
                        <label>Qualification</label>
                        <input type="text" name="doctorQualification" value="${doctor.doctorQualification}" required>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label>Experience (Years)</label>
                            <input type="number" name="doctorExperience" value="${doctor.doctorExperience}" required>
                        </div>
                        <div class="form-group">
                            <label>Specialization</label>
                            <select name="doctorSpecialization" required>
                                <option value="" disabled>Select specialization</option>
                                <option value="Cardiology" ${doctor.doctorSpecialization == 'Cardiology' ? 'selected' : ''}>Cardiology</option>
                                <option value="Dermatology" ${doctor.doctorSpecialization == 'Dermatology' ? 'selected' : ''}>Dermatology</option>
                                <option value="Neurology" ${doctor.doctorSpecialization == 'Neurology' ? 'selected' : ''}>Neurology</option>
                                <option value="Orthopedics" ${doctor.doctorSpecialization == 'Orthopedics' ? 'selected' : ''}>Orthopedics</option>
                                <option value="Pediatrics" ${doctor.doctorSpecialization == 'Pediatrics' ? 'selected' : ''}>Pediatrics</option>
                                <option value="Psychiatry" ${doctor.doctorSpecialization == 'Psychiatry' ? 'selected' : ''}>Psychiatry</option>
                                <option value="General Medicine" ${doctor.doctorSpecialization == 'General Medicine' ? 'selected' : ''}>General Medicine</option>
                                <option value="General Surgery" ${doctor.doctorSpecialization == 'General Surgery' ? 'selected' : ''}>General Surgery</option>
                                <option value="Gynecology" ${doctor.doctorSpecialization == 'Gynecology' ? 'selected' : ''}>Gynecology</option>
                                <option value="Other" ${doctor.doctorSpecialization == 'Other' ? 'selected' : ''}>Other</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <a href="${pageContext.request.contextPath}/admin-doctor-list" class="form-btn btn-secondary" style="text-decoration: none">Cancel</a>
                        <button type="submit" class="form-btn btn-primary">Update Doctor</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>