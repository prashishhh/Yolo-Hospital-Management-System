<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <!-- Link to external CSS for styling the patient dashboard -->
    <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/patient_dashboard.css" />
</head>
<body>

	<jsp:include page="/WEB-INF/pages/header.jsp" />

    <!-- Main content container -->
    <!-- Main content container -->
    <main>
        <div class="container">
            <!-- Welcome section with dashboard introduction -->
            <div class="welcome-section">
                <div class="welcome-text">
                    <h1>Welcome to Your Patient Dashboard</h1>
                    <p>Manage your appointments, treatments, and medical information in one place.</p>
                </div>
            </div>

            <!-- Section showing statistics like upcoming appointments and active treatments -->
            <div class="stats-section">
                <!-- Upcoming appointments card -->
                <div class="stat-card appointments">
                    <div class="stat-header">
                        <span class="stat-title">Total Appointments</span>
                        <div class="stat-icon appointments-icon">📅</div>
                    </div>
                    <div class="stat-value">${empty totalAppointments ? 0 : totalAppointments}</div>
                </div>

                <!-- Active treatments card -->
                <div class="stat-card treatments">
                    <div class="stat-header">
                        <span class="stat-title">Total Treatments</span>
                        <div class="stat-icon treatments-icon">📋</div>
                    </div>
                    <div class="stat-value">${empty totalTreatments ? 0 : totalTreatments}</div>
                </div>
            </div>

            <!-- Recent Appointments Table -->
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Recent Appointments</h3>
                    <a href="${pageContext.request.contextPath}/patient-appointment" class="card-link">View All</a>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Doctor</th>
                                    <th>Date & Time</th>
                                    <th>Department</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="userAppointment" items="${appointmentList}">
                                    <tr>
                                        <td>Dr. ${userAppointment.appointment.doctor.doctorFirstName} ${userAppointment.appointment.doctor.doctorLastName}</td>
                                        <td>${userAppointment.appointment.appointmentDate}<br>${userAppointment.appointment.appointmentStartTime}</td>
                                        <td>${userAppointment.appointment.doctor.doctorSpecialization}</td>
                                        <td>
                                            <div class="badge ${userAppointment.appointment.appointmentStatus == 'Confirmed' ? 'badge-confirmed' : userAppointment.appointment.appointmentStatus == 'Pending' ? 'badge-pending' : 'badge-cancelled'}">
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

            <!-- Treatment Plans Table -->
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Your Treatment Plans</h3>
                    <a href="${pageContext.request.contextPath}/patient-treatment" class="card-link">View All</a>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Treatment</th>
                                    <th>Plan</th>
                                    <th>Remarks</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="treatment" items="${treatmentList}">
                                    <tr>
                                        <td>${treatment.treatmentName}</td>
                                        <td>${treatment.treatmentPlan}</td>
                                        <td>${treatment.treatmentRemark}</td>
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
