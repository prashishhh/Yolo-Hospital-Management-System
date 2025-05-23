<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Meet Our Doctors</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/our_doctors.css" />
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="main-content">
        <h1 class="page-title">Meet Our Doctors</h1>
        <div class="page-subtitle">
            <p>Get the best medical care from our team of highly qualified specialists. Our doctors are committed to providing compassionate and exceptional healthcare.</p>
        </div>

        <!-- Search form -->
        <div class="search-control">
            <form action="${pageContext.request.contextPath}/our-doctors" method="get">
                <input type="text" name="search" placeholder="Search doctors..." value="${param.search}">
                <button type="submit"><img class="search-icon" src="${pageContext.request.contextPath}/resources/images/searchbar.png" alt="search bar"></button>
            </form>
        </div>

        <!-- Container holding all doctor profile cards in grid format -->
        <div class="doctor-grid">
            <c:forEach var="doctor" items="${doctorList}">
                <div class="doctor-card">
                    <div class="doctor-img-container">
                        <img src="${pageContext.request.contextPath}/resources/images/doctors/${doctor.imagePath}" alt="${doctor.doctorFirstName} ${doctor.doctorLastName}" class="doctor-image">
                    </div>
                    <div class="doctor-info">
                        <h3 class="doctor-name">${doctor.doctorFirstName} ${doctor.doctorLastName}</h3>
                        <div class="doctor-specialty">${doctor.doctorSpecialization}</div>
                        <div class="doctor-description" style="text-align:center;">${doctor.doctorQualification}</div>
                        <div class="doctor-stats">
                            <div class="doctor-stat">
                                <div class="stat-value">${doctor.doctorExperience}</div>
                                <div class="stat-label">Years Exp</div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <!-- Display message if no doctors are found -->
            <c:if test="${empty doctorList}">
                <p>No doctors found.</p>
            </c:if>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>