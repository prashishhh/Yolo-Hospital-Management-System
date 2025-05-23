<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin_dashboard.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />
	
	<main>
    <div class="container">
      <div class="welcome-section">
        <div class="welcome-text">
          <h1>Welcome Admin</h1>
          <p>Here's what's happening with your healthcare system today.</p>
        </div>
      </div>
      
      <div class="stats-section">
        <div class="stat-card appointments">
          <div class="stat-header">
            <span class="stat-title">Appointments</span>
            <div class="stat-icon appointments-icon">📅</div>
          </div>
          <div class="stat-value">${empty totalAppointment ? 0 : totalAppointment}</div>
        </div>
        
        <div class="stat-card doctors">
          <div class="stat-header">
            <span class="stat-title">Doctors</span>
            <div class="stat-icon doctors-icon">👨‍⚕️</div>
          </div>
          <div class="stat-value">${empty totalDoctor ? 0 : totalDoctor}</div>
        </div>
        
        <div class="stat-card patients">
          <div class="stat-header">
            <span class="stat-title">Patients</span>
            <div class="stat-icon patients-icon">👥</div>
          </div>
          <div class="stat-value">${empty totalPatient ? 0 : totalPatient}</div>
        </div>
        
        <div class="stat-card revenue">
          <div class="stat-header">
            <span class="stat-title">Total Income</span>
            <div class="stat-icon revenue-icon">💰</div>
          </div>
          <div class="stat-value">Rs. ${empty totalIncome ? 0 : totalIncome}</div>
        </div>
      </div>
      
      <div class="grid-container">
        <div class="card">
          <div class="card-header">
            <h3 class="card-title">Recent Patients</h3>
            <a href="${pageContext.request.contextPath}/admin-patient-list" class="card-link">View All</a>
          </div>
          <div class="card-body">
            <div class="table-responsive">
              <table class="table">
                <thead>
                  <tr>
                    <th>Patient Name</th>
                    <th>Gender</th>
                    <th>Age</th>
                    <th>Address</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach var="patient" items="${patientList}">
                  <tr>
                    <td>
                      <div class="name-cell">
                        <div class="avatar">
                          <img src="${pageContext.request.contextPath}/resources/images/patient/${patient.imagePath}" alt="patient profile picture"> 
                        </div>
                        ${patient.firstName} ${patient.lastName}
                      </div>
                    </td>
                    <td>${patient.gender}</td>
                    <td>${patient.age }</td>
                    <td>${patient.municipality}, Ward ${patient.wardNo}</td>
                    <td>
                      <div class="action-btns">
                        <button class="btn btn-delete">🗑️</button>
                      </div>
                    </td>
                  </tr>
                 </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        
         <div class="card">
          <div class="card-header">
            <h3 class="card-title">Doctors</h3>
            <a href="${pageContext.request.contextPath}/admin-doctor-list" class="card-link">View All</a>
          </div>
          <div class="card-body">
            <ul class="doctor-list">
            <c:forEach var="doctor" items="${doctorList}">
              <li class="doctor-item">
                <div class="doctor-avatar">
                  <img src= "${pageContext.request.contextPath}/resources/images/doctors/${doctor.imagePath}" alt="Doctor Profile">
                </div>
                <div class="doctor-info">
                  <div class="doctor-name">Dr. ${doctor.doctorFirstName} ${doctor.doctorLastName} </div>
                  <div class="doctor-specialty">${doctor.doctorSpecialization}</div>
                </div>
                <div class="doctor-actions">
                  <button class="btn btn-icon">⋮</button>
                </div>
              </li>
             </c:forEach>
            </ul>
          </div>
        </div>
      </div>
      
      <div class="card">
        <div class="card-header">
          <h3 class="card-title">Appointments</h3>
          <a href="${pageContext.request.contextPath}/admin-appointment-list" class="card-link">View All</a>
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table">
              <thead>
                <tr>
                  <th>Patient Name</th>
                  <th>Doctor</th>
                  <th>Date and Time</th>
                  <th>Room</th>
                  <th>Appointment Status</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var="userAppointment" items="${appointmentList}">
                <tr>
                  <td>${userAppointment.user.firstName} ${userAppointment.user.lastName}</td>
                  <td>Dr. ${userAppointment.appointment.doctor.doctorFirstName} ${userAppointment.appointment.doctor.doctorLastName}</td>
                  <td>${userAppointment.appointment.appointmentDate} <br/>
            			${userAppointment.appointment.appointmentStartTime} - ${userAppointment.appointment.appointmentEndTime}
        		  </td>
                  <td>${userAppointment.appointment.room.roomName} <br/> ${userAppointment.appointment.room.roomType}</td>
                  <td>
           			 <div class="badge ${userAppointment.appointment.appointmentStatus == 'Confirmed' ? 'badge-success' : 'badge-warning'}">
                		${userAppointment.appointment.appointmentStatus}
           			 </div>	
       			  </td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </main>
</body>
</html>