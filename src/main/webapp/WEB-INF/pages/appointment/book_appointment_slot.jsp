<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%
    // Generate the next 7 days (today + 6 days)
    LocalDate today = LocalDate.now();
    List<LocalDate> nextSevenDays = new ArrayList<>();
    
    for (int i = 0; i < 7; i++) {
        nextSevenDays.add(today.plusDays(i));
    }
    
    // Set as attribute to use in the JSP
    request.setAttribute("nextSevenDays", nextSevenDays);
    
    // Get the current month and year
    String currentMonth = today.format(DateTimeFormatter.ofPattern("MMMM, yyyy"));
    request.setAttribute("currentMonth", currentMonth);
    
    // If no date is selected, default to today
    if (request.getAttribute("selectedDate") == null) {
        request.setAttribute("selectedDate", today);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Appointment Slot</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/book_appointment_slot.css" />
</head>
<body>
    <div class="container">
        <div class="booking-container">
            <!-- Doctor profile card -->
            <c:if test="${not empty doctor}">
                <div class="doctor-card">
                    <div class="doctor-image-container">
                        <img class="doctor-image" src="${pageContext.request.contextPath}/resources/images/doctors/${doctor.imagePath}"
                            alt="${doctor.doctorFirstName} ${doctor.doctorLastName}">
                    </div>
                    <div class="doctor-info">
                        <h2>Dr. ${doctor.doctorFirstName} ${doctor.doctorLastName}</h2>
                        <p>${doctor.doctorQualification}</p>
                        <span class="specialty">${doctor.doctorSpecialization}</span>
                    </div>
                </div>
            </c:if>

            <!-- Error message -->
            <c:if test="${not empty error}">
                <p class="error-message">${error}</p>
            </c:if>

            <!-- Month display -->
            <div class="month-container">
                <h3>${currentMonth}</h3>
            </div>

            <!-- Days of the week with dates -->
            <div class="dates-container">
                <div class="day-labels">
                    <c:forEach var="date" items="${nextSevenDays}">
                        <span>${date.getDayOfWeek().toString().substring(0, 3)}</span>
                    </c:forEach>
                </div>
                <div class="date-buttons">
                    <c:forEach var="date" items="${nextSevenDays}">
                        <form action="${pageContext.request.contextPath}/book-appointment-slot" method="get">
                            <input type="hidden" name="selectedDate" value="${date}">
                            <button type="submit" class="date-button ${date == selectedDate ? 'selected' : ''}">${date.getDayOfMonth()}</button>
                        </form>
                    </c:forEach>
                </div>
            </div>

            <!-- Available slots for selected date -->
            <c:if test="${not empty selectedAppointments}">
                <form action="${pageContext.request.contextPath}/book-appointment-slot" method="post">
                    <div class="slots-container">
                        <c:forEach var="appointment" items="${selectedAppointments}">
                            <label class="slot-item">
                                <input type="radio" name="appointmentID" value="${appointment.appointmentID}" required>
                                <span>${appointment.appointmentStartTime} - ${appointment.appointmentEndTime}</span>
                            </label>
                        </c:forEach>
                    </div>
                    
                    <!-- Fee display -->
                    <c:if test="${not empty fee}">
                        <div class="fee-container">
                            <span class="fee-label">Consultation Fee:</span>
                            <span class="fee-amount">Rs.${fee}</span>
                        </div>
                    </c:if>
                    
                    <button type="submit" class="booking-button">Book Appointment</button>
                </form>
                <a href="${pageContext.request.contextPath}/book-appointment" class="form-btn btn-secondary" style="text-decoration: none">Cancel</a>
            </c:if>
            
            <!-- No slots message -->
            <c:if test="${empty selectedAppointments && empty error}">
                <p class="error-message">No available slots for this date.</p>
            </c:if>

            <!-- Message -->
            <c:if test="${not empty message}">
                <p>${message}</p>
            </c:if>
        </div>
    </div>
</body>
</html>