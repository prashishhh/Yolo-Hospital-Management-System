<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Appointment</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/create_appointment.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />
	
	<div class="main-content">
    <div class="card">
      <div class="form-container">
        <div class="form-header">
          <h1 class="form-title">Create Appointment<span>.</span></h1>
          <p class="tagline">Create Appointment Slots for patients to book</p>
        </div>
        
        <form>
          
          <div class="form-section-title">Appointment Details</div>
          
          <div class="form-group">
            <label>Appointment Date</label>
            <input type="date" name="appointmentDate" required>
          </div>
          
          <div class="form-row">
            <div class="form-group">
              <label>Start Time</label>
              <input type="time" name="appointmentStartTime" required min="10:00" max="16:30" step="1800">
            </div>
            
            <div class="form-group">
              <label>End Time</label>
              <input type="time" name="appointmentEndTime" required min="10:30" max="17:00" step="1800">
            </div>
          </div>          

          <div class="form-group">
            <label>Appointment Fee</label>
            <input type="number" name="appointmentFee" placeholder="Enter appointment fee" required>
          </div>
          
          <div class="form-section-title">Assignment Information</div>
          
          <div class="form-row">
            <div class="form-group">
              <label>Doctor</label>
              <select name="doctorID" required>
                <option value="" disabled selected>Select doctor</option>
                <option value="1">Dr. Sarah Johnson - Cardiology</option>
                <option value="2">Dr. Michael Chen - Neurology</option>
                <option value="3">Dr. Lisa Patel - Pediatrics</option>
                <option value="4">Dr. David Williams - Orthopedics</option>
                <option value="5">Dr. Emily Brown - Dermatology</option>
                <option value="6">Dr. Robert Garcia - General Medicine</option>
              </select>
            </div>
            
            <div class="form-group">
              <label>Room</label>
              <select name="roomID" required>
                <option value="" disabled selected>Select room</option>
                <option value="101">Room 101 - Examination</option>
                <option value="102">Room 102 - Examination</option>
                <option value="103">Room 103 - Consultation</option>
                <option value="104">Room 104 - Consultation</option>
                <option value="105">Room 105 - Procedure</option>
                <option value="106">Room 106 - Procedure</option>
              </select>
            </div>
          </div>
          
          <div class="form-actions">
            <button type="button" class="form-btn btn-secondary">Cancel</button>
            <button type="submit" class="form-btn btn-primary">Create Appointment</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</body>
</html>