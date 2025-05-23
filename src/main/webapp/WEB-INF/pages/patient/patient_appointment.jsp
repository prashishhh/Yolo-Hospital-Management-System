<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patient Appointment List</title>
    <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/patient_appointment.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/pages/header.jsp" />
    
    <div class="content">
        <div class="header-container">
            <h2 class="list-header">Appointments List</h2>
            <a href="${pageContext.request.contextPath}/book-appointment" class="add-appointment-btn">+ Book Appointment</a>
        </div>
        
        <!-- Search form -->
        <div class="search-control">
            <form action="${pageContext.request.contextPath}/patient-appointment" method="get">
                <input type="text" name="search" placeholder="Search appointments..." value="${param.search}">
                <button type="submit"><img class="search-icon" src="${pageContext.request.contextPath}/resources/images/searchbar.png" alt="search bar"></button>
            </form>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th>Appointment ID</th>
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
                        <td>Dr. ${userAppointment.appointment.doctor.doctorFirstName} ${userAppointment.appointment.doctor.doctorLastName}</td>
                        <td>${userAppointment.appointment.appointmentDate}</td>
                        <td>${userAppointment.appointment.appointmentStartTime} - ${userAppointment.appointment.appointmentEndTime}</td>
                        <td>${userAppointment.appointment.room.roomName} <br/> ${userAppointment.appointment.room.roomType}</td>
                        <td>
                            <span class="status 
                                <c:choose>
                                    <c:when test="${userAppointment.appointment.appointmentStatus == 'Booked'}">booked</c:when>
                                    <c:when test="${userAppointment.appointment.appointmentStatus == 'Completed'}">completed</c:when>
                                    <c:when test="${userAppointment.appointment.appointmentStatus == 'Not Booked'}">not-booked</c:when>
                                    <c:otherwise>default</c:otherwise>
                                </c:choose>">
                                ${userAppointment.appointment.appointmentStatus}
                            </span>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>