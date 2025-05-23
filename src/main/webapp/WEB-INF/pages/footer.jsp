<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="userRole" value="${sessionScope.userRole}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Footer</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
	<footer class="site-footer">
        <div class="container">
          <div class="footer__grid">
            <div class="footer__column">
              <h4 class="footer__title">Yolo Hospital</h4>
              <p>Your health is our priority. We provide the best healthcare services with modern facilities and experienced professionals.</p>
            </div>
            
            <div class="footer__column">
              <h4 class="footer__title">Quick Links</h4>
              <ul class="footer__links">
                <!-- Admin Menu -->
                <c:if test="${userRole == 'Admin'}">
                    <li><a href="${pageContext.request.contextPath}/admin-dashboard">Overview</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin-doctor-list">Doctor</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin-patient-list">Patients</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin-appointment-list">Appointments</a></li>
                    <li><a href="${pageContext.request.contextPath}/add-blog">Add Blog</a></li>
                </c:if>
                
                <!-- Patient Menu -->
                <c:if test="${userRole == 'Patient'}">
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/our-doctors">Our Doctors</a></li>
                    <li><a href="${pageContext.request.contextPath}/patient-dashboard">Overview</a></li>
                    <li><a href="${pageContext.request.contextPath}/patient-profile">Profile</a></li>
                    <li><a href="${pageContext.request.contextPath}/patient-appointment">Appointments</a></li>
                    <li><a href="${pageContext.request.contextPath}/blog">Blog</a></li>
                </c:if>
                
                <!-- Default (Guest or other roles) -->
                <c:if test="${userRole != 'Admin' and userRole != 'Patient'}">
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/about-us">About Us</a></li>
                    <li><a href="${pageContext.request.contextPath}/blog">Blog</a></li>
                </c:if>
              </ul>
            </div>
            
            <div class="footer__column">
              <h4 class="footer__title">Services</h4>
              <ul class="footer__links">
                <li>Emergency Care</li>
                <li>Diagnostics</li>
                <li>General Surgery</li>
                <li>Family Medicine</li>
                <li>Mental Health</li>
              </ul>
            </div>
            
            <div class="footer__column">
              <h4 class="footer__title">Contact Info</h4>
              <ul class="footer__links">
                <li>Yolo Hospital, Medical City</li>
                <li>Phone: +977 01-5003222</li>
                <li>Email: info@yolohospital.com</li>
                <li>Working Hours: 24/7</li>
              </ul>
            </div>
          </div>
          
          <div class="footer__copyright">
            <p>© 2025 Yolo Hospital. All Rights Reserved.</p>
          </div>
        </div>
    </footer>
</body>
</html>