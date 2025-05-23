<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patient Overview</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin_patient_list.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />

	<div class="content">
        <div class="header-container">
            <h2 class="list-header">Patients List</h2>
        </div>
        
		<!-- Search form -->
            <div class="search-control">
                <form action="${pageContext.request.contextPath}/admin-patient-list" method="get">
                    <input type="text" name="search" placeholder="Search patients..." value="${param.search}">
                    <button type="submit"><img class="search-icon" src="${pageContext.request.contextPath}/resources/images/searchbar.png" alt="search bar"></button>
                </form>
            </div>
        <table>
            <thead>
                <tr>
                    <th>Patient ID</th>
                    <th>Patient Name</th>
                    <th>Age</th>
                    <th>Gender</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach var="patient" items="${patientInfoList}">
                <tr>
                    <td>${patient.userId}</td>
                    <td>${patient.firstName} ${patient.lastName}</td>
                    <td>${patient.age }</td>
                    <td>${patient.gender}</td>
                    <td>${patient.phoneNumber}</td>
                    <td>${patient.municipality}, Ward ${patient.wardNo}</td>
                    <td class="actions">
                    	<form action="${pageContext.request.contextPath}/admin-patient-list" method="post" style="display:inline">
                    		<input type="hidden" name="action" value="deletePatient">
                    		<input type="hidden" name="userID" value="${patient.userId}">
                        	<button class="btn-delete">Delete</button>
                        </form>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>