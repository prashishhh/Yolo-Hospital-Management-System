<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book an Appointment</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/book_appointment.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/pages/header.jsp" />

    <div class="main-content">
        <h1 class="page-title">Book an Appointment</h1>

        <c:if test="${not empty error}">
            <p class="error-message">${error}</p>
        </c:if>

        <div class="doctor-grid">
            <c:forEach var="doctor" items="${doctors}">
                <div class="doctor-card">
                    <div class="doctor-img-container">
                        <img src="${pageContext.request.contextPath}/resources/images/doctors/${doctor.imagePath}" alt="${doctor.doctorFirstName} ${doctor.doctorLastName}">
                    </div>
                    <div class="doctor-info">
                        <h3 class="doctor-name">${doctor.doctorFirstName} ${doctor.doctorLastName}</h3>
                        <div class="doctor-specialty">${doctor.doctorSpecialization}</div>
                    </div>
                    <div class="appointment-btn-container">
                        <form action="${pageContext.request.contextPath}/book-appointment" method="post">
                            <input type="hidden" name="doctorID" value="${doctor.doctorID}">                            
                            <button type="submit" class="appointment-btn">Book Appointment</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>