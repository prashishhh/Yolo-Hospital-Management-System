<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patient Treatment List</title>
    <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/patient_treatment.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/pages/header.jsp" />
    
    <div class="container">
        <div class="main-content">
            <div class="header-container">
                <h2 class="list-header">Treatment List</h2>
            </div>
            
            <!-- Search form -->
            <div class="search-control">
                <form action="${pageContext.request.contextPath}/patient-treatment" method="get">
                    <input type="text" name="search" placeholder="Search treatments..." value="${param.search}">
                    <button type="submit"><img class="search-icon" src="${pageContext.request.contextPath}/resources/images/searchbar.png" alt="search bar"></button>
                </form>
            </div>
            
            
            <table class="treatment-table">
                <thead>
                    <tr>
                        <th>Treatment Name</th>
                        <th>Doctor</th>
                        <th>Treatment Plan</th>
                        <th>Remark</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="userTreatment" items="${treatmentDetailsList}">
                        <tr>
                            <td>${userTreatment.appointment.treatment.treatmentName}</td>
                            <td>Dr. ${userTreatment.appointment.doctor.doctorFirstName} ${userTreatment.appointment.doctor.doctorLastName}</td>
                            <td>${userTreatment.appointment.treatment.treatmentPlan}</td>
                            <td>${userTreatment.appointment.treatment.treatmentRemark}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>