<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Treatment Overview</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin_treatment_list.css" />
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp" />

<div class="content">
  <div class="header-container">
    <h2 class="list-header">Treatments List</h2>
    <a href="${pageContext.request.contextPath}/add-treatment" class="add-treatment-btn">Add Treatment</a>
  </div>

  <div class="search-control">
            <form action="${pageContext.request.contextPath}/admin-treatment-list" method="get">
                <input type="text" name="search" placeholder="Search treatments..." value="${param.search}">
                <button type="submit"><img class="search-icon" src="${pageContext.request.contextPath}/resources/images/searchbar.png" alt="search bar"></button>
            </form>
        </div>
  <table>
    <thead>
      <tr>
        <th>Treatment ID</th>
        <th>Treatment Name</th>
        <th>Doctor</th>
        <th>Treatment Plan</th>
        <th>Remark</th>
      </tr>
    </thead>
    <tbody>
                <c:forEach var="appointment" items="${treatmentDetailsList}">
                    <tr>
                        <td>${appointment.treatment.treatmentID}</td>
                        <td class="treatment-name">${appointment.treatment.treatmentName}</td>
                        <td class="doctor-name">Dr. ${appointment.doctor.doctorFirstName} ${appointment.doctor.doctorLastName}</td>
                        <td>${appointment.treatment.treatmentPlan}</td>
                        <td>${appointment.treatment.treatmentRemark}</td>
                    </tr>
                </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>