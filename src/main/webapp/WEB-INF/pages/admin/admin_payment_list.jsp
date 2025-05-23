<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Overview</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin_payment_list.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />

	<div class="content">
        <div class="header-container">
            <h2 class="list-header">Payments List</h2>
        </div>

        <!-- Search form -->
        <div class="search-control">
            <form action="${pageContext.request.contextPath}/admin-payment-list" method="get">
                <input type="text" name="search" placeholder="Search payments..." value="${param.search}">
                <button type="submit"><img class="search-icon" src="${pageContext.request.contextPath}/resources/images/searchbar.png" alt="search bar"></button>
            </form>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Payment ID</th>
                    <th>Appointment ID</th>
                    <th>Patient Name</th>
                    <th>Amount</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="totalAmount" value="0" />
                <c:forEach var="userAppointment" items="${paymentDetailsList}">
                    <tr>
                        <td>${userAppointment.appointment.payment.paymentID}</td>
                        <td>${userAppointment.appointment.appointmentID}</td>
                        <td>${userAppointment.user.firstName} ${userAppointment.user.lastName}</td>
                        <td>Rs.${userAppointment.appointment.payment.paymentAmount}</td>
                        <c:set var="totalAmount" value="${totalAmount + userAppointment.appointment.payment.paymentAmount}" />
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="3" style="text-align: right; font-weight: bold;">Total Amount:</td>
                    <td class="amount" style="font-weight: bold;">Rs.${totalAmount}</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>