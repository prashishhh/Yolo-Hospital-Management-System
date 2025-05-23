<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Appointment Overview</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin_appointment_list.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />
	
	<div class="content">
        <div class="header-container">
            <h2 class="list-header">Appointments List</h2>
            <a href="${pageContext.request.contextPath}/create-appointment" class="add-appointment-btn">+ Create Appointment</a>
        </div>
        
        <!-- Search form -->
            <div class="search-control">
                <form action="${pageContext.request.contextPath}/admin-appointment-list" method="get">
                    <input type="text" name="search" placeholder="Search appointments...." value="${param.search}">
                    <button type="submit"><img class="search-icon" src="${pageContext.request.contextPath}/resources/images/searchbar.png" alt="search bar"></button>
                </form>
            </div>
        <table>
            <thead>
                <tr>
                    <th>Appointment ID</th>
                    <th>Patient Name</th>
                    <th>Doctor Name</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Room</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach var="userAppointment" items="${appointmentDetailsList}">
                <tr>
                	<td>${userAppointment.appointment.appointmentID}</td>
                    <td>${userAppointment.user.firstName} ${userAppointment.user.lastName}</td>
                    <td>Dr. ${userAppointment.appointment.doctor.doctorFirstName} ${userAppointment.appointment.doctor.doctorLastName}</td>
                    <td>${userAppointment.appointment.appointmentDate}</td>
                    <td>${userAppointment.appointment.appointmentStartTime} - ${userAppointment.appointment.appointmentEndTime}</td>
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
</body>
</html>