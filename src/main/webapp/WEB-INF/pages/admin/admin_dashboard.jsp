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
          <div class="stat-value">509</div>
        </div>
        
        <div class="stat-card doctors">
          <div class="stat-header">
            <span class="stat-title">Doctors</span>
            <div class="stat-icon doctors-icon">👨‍⚕️</div>
          </div>
          <div class="stat-value">60</div>
        </div>
        
        <div class="stat-card patients">
          <div class="stat-header">
            <span class="stat-title">Patients</span>
            <div class="stat-icon patients-icon">👥</div>
          </div>
          <div class="stat-value">1k</div>
        </div>
        
        <div class="stat-card revenue">
          <div class="stat-header">
            <span class="stat-title">Total Income</span>
            <div class="stat-icon revenue-icon">💰</div>
          </div>
          <div class="stat-value">$80k</div>
        </div>
      </div>
      
      <div class="grid-container">
        <div class="card">
          <div class="card-header">
            <h3 class="card-title">Recent Patients</h3>
            <a href="#" class="card-link">View All</a>
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
                        <button class="btn btn-edit">✏️</button>
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
            <a href="#" class="card-link">View All</a>
          </div>
          <div class="card-body">
            <ul class="doctor-list">
              <li class="doctor-item">
                <div class="doctor-avatar">
                  <img src= "${pageContext.request.contextPath}/resources/images/doctors/prashsihsihss.jpg" alt="Doctor Profile">
                </div>
                <div class="doctor-info">
                  <div class="doctor-name">Dr. Brooklyn Simmons</div>
                  <div class="doctor-specialty">Radiation</div>
                </div>
                <div class="doctor-actions">
                  <button class="btn btn-icon">⋮</button>
                </div>
              </li>
              <li class="doctor-item">
                <div class="doctor-avatar">
                  <img src= "${pageContext.request.contextPath}/resources/images/doctors/prashsihsihss.jpg" alt="Doctor Profile">
                </div>
                <div class="doctor-info">
                  <div class="doctor-name">Dr. Savannah Nguyen</div>
                  <div class="doctor-specialty">Dialysis</div>
                </div>
                <div class="doctor-actions">
                  <button class="btn btn-icon">⋮</button>
                </div>
              </li>
              <li class="doctor-item">
                <div class="doctor-avatar">
                  <img src= "${pageContext.request.contextPath}/resources/images/doctors/prashsihsihss.jpg" alt="Doctor Profile">
                </div>
                <div class="doctor-info">
                  <div class="doctor-name">Dr. Courtney Henry</div>
                  <div class="doctor-specialty">Hormone</div>
                </div>
                <div class="doctor-actions">
                  <button class="btn btn-icon">⋮</button>
                </div>
              </li>
              <li class="doctor-item">
                <div class="doctor-avatar">
                  <img src= "${pageContext.request.contextPath}/resources/images/doctors/prashsihsihss.jpg" alt="Doctor Profile">
                </div>
                <div class="doctor-info">
                  <div class="doctor-name">Dr. Kathryn Murphy</div>
                  <div class="doctor-specialty">Radiation</div>
                </div>
                <div class="doctor-actions">
                  <button class="btn btn-icon">⋮</button>
                </div>
              </li>
              <li class="doctor-item">
                <div class="doctor-avatar">
                  <img src= "${pageContext.request.contextPath}/resources/images/doctors/prashsihsihss.jpg" alt="Doctor Profile">
                </div>
                <div class="doctor-info">
                  <div class="doctor-name">Dr. John Doe</div>
                  <div class="doctor-specialty">Cardiology</div>
                </div>
                <div class="doctor-actions">
                  <button class="btn btn-icon">⋮</button>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>
      
      <div class="card">
        <div class="card-header">
          <h3 class="card-title">Appointments</h3>
          <a href="#" class="card-link">View All</a>
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
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Jahid Ahmad</td>
                  <td>Dr. Brooklyn Simmons</td>
                  <td>10 Feb 2025<br/>9:30-10:30</td>
                  <td>Room 101<br/>(Radiation)</td>
                  <td><div class="badge badge-success">Confirmed</div></td>
                  <td><button class="btn btn-icon">⋮</button></td>
                </tr>
                <tr>
                  <td>Rohim Mia</td>
                  <td>Dr. Savannah Nguyen</td>
                  <td>10 Feb 2025<br/>10:00-11:00</td>
                  <td>Room 205<br/>(Dialysis)</td>
                  <td><div class="badge badge-warning">Pending</div></td>
                  <td><button class="btn btn-icon">⋮</button></td>
                </tr>
                <tr>
                  <td>Kabir Uddin</td>
                  <td>Dr. Courtney Henry</td>
                  <td>10 Feb 2025<br/>11:30-12:30</td>
                  <td>Room 303<br/>(Consultation)</td>
                  <td><div class="badge badge-success">Confirmed</div></td>
                  <td><button class="btn btn-icon">⋮</button></td>
                </tr>
                <tr>
                  <td>Mira Singh</td>
                  <td>Dr. Kathryn Murphy</td>
                  <td>10 Feb 2025<br/>2:00-3:00</td>
                  <td>Room 102<br/>(Physical)</td>
                  <td><div class="badge badge-warning">Pending</div></td>
                  <td><button class="btn btn-icon">⋮</button></td>
                </tr>
                <tr>
                  <td>Marten Don</td>
                  <td>Dr. Brooklyn Simmons</td>
                  <td>10 Feb 2025<br/>3:30-4:30</td>
                  <td>Room 101<br/>(Radiation)</td>
                  <td><div class="badge badge-success">Confirmed</div></td>
                  <td><button class="btn btn-icon">⋮</button></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </main>
</body>
</html>