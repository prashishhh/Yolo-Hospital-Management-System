<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Appointment</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/create_appointment.css" />
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

                <!-- Display error message if available -->
                <c:if test="${not empty error}">
                    <p class="error-message">${error}</p>
                </c:if>
                <!-- Display success message if available -->
                <c:if test="${not empty success}">
                    <p class="success-message">${success}</p>
                </c:if>

                <form action="${pageContext.request.contextPath}/create-appointment" method="post">
                    <div class="form-section-title">Appointment Details</div>

                    <div class="form-group">
                        <label>Appointment Date</label>
                        <input type="date" name="appointmentDate" value="${appointmentDate}" required>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label>Start Time</label>
                            <input type="time" name="appointmentStartTime" value="${appointmentStartTime}" required min="10:00" max="16:30" step="1800">
                        </div>

                        <div class="form-group">
                            <label>End Time</label>
                            <input type="time" name="appointmentEndTime" value="${appointmentEndTime}" required min="10:30" max="17:00" step="1800">
                        </div>
                    </div>

                    <div class="form-group">
                        <label>Appointment Fee</label>
                        <input type="number" name="appointmentFee" value="${appointmentFee}" placeholder="Enter appointment fee" required>
                    </div>

                    <div class="form-section-title">Assignment Information</div>

                    <div class="form-row">
                        <div class="form-group">
                            <label>Doctor</label>
                            <select name="doctorID" required>
                                <option value="" disabled ${doctorID == null ? 'selected' : ''}>Select doctor</option>
                                <c:forEach var="doctor" items="${doctors}">
                                    <option value="${doctor.doctorID}" ${doctorID == doctor.doctorID ? 'selected' : ''}>
                                        Dr. ${doctor.doctorFirstName} ${doctor.doctorLastName} - ${doctor.doctorSpecialization}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Room</label>
                            <select name="roomID" required>
                                <option value="" disabled ${roomID == null ? 'selected' : ''}>Select room</option>
                                <c:forEach var="room" items="${rooms}">
                                    <option value="${room.roomID}" ${roomID == room.roomID ? 'selected' : ''}>
                                        Room ${room.roomID} - ${room.roomName} (${room.roomType})
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button type="button" class="form-btn btn-secondary" onclick="window.history.back()">Cancel</button>
                        <button type="submit" class="form-btn btn-primary">Create Appointment</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>