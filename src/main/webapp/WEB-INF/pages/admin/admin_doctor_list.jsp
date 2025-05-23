<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Doctor List</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin_doctor_list.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/pages/header.jsp" />
    <main>
        <div class="content">
            <div class="header-container">
                <h2 class="list-header">Doctors List</h2>
                <a href="${pageContext.request.contextPath}/add-doctor" class="add-doctor-btn">+ Add Doctor</a>
            </div>
            <c:if test="${not empty error}">
                <p class="error-message">${error}</p>
            </c:if>
            <c:if test="${not empty success}">
                <p class="success-message">${success}</p>
            </c:if>
            <div class="search-control">
                <form action="${pageContext.request.contextPath}/admin-doctor-list" method="get">
                    <input type="text" name="search" placeholder="Search doctors..." value="${param.search}">
                    <button type="submit"><img class="search-icon" src="${pageContext.request.contextPath}/resources/images/searchbar.png" alt="search bar"></button>
                </form>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>Doctor ID</th>
                        <th>Doctor Name</th>
                        <th>Experience (Years)</th>
                        <th>Phone</th>
                        <th>Specialization</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="doctor" items="${doctorInfoList}">
                        <tr>
                            <td>${doctor.doctorID}</td>
                            <td>${doctor.doctorFirstName} ${doctor.doctorLastName}</td>
                            <td>${doctor.doctorExperience}</td>
                            <td>${doctor.doctorPhoneNumber}</td>
                            <td>${doctor.doctorSpecialization}</td>
                            <td class="actions">
                                <form action="${pageContext.request.contextPath}/admin-doctor-list" method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="editDoctor">
                                    <input type="hidden" name="doctorID" value="${doctor.doctorID}">
                                    <button type="submit" class="btn-edit">Edit</button>
                                </form>
                                <form action="${pageContext.request.contextPath}/admin-doctor-list" method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="deleteDoctor">
                                    <input type="hidden" name="doctorID" value="${doctor.doctorID}">
                                    <button type="submit" class="btn-delete">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>