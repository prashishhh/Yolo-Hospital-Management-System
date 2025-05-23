<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patient Payment History</title>
<link rel="stylesheet" type="text/css"
href="${pageContext.request.contextPath}/css/patient_payment_history.css" />
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp" />
<div class="content">
    <div class="header-container">
        <h2 class="list-header">Payment History</h2>
    </div>
    
    <table>
        <thead>
                <tr>
                    <th>Invoice ID</th>
                    <th>Appointment ID</th>
                    <th>Date</th>
                    <th>Amount</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="userPayment" items="${paymentDetailsList}">
                    <tr>
                        <td>${userPayment.appointment.payment.paymentID}</td>
                        <td>${userPayment.appointment.appointmentID}</td>
                        <td>${userPayment.appointment.appointmentDate}</td>
                        <td>Rs.${userPayment.appointment.payment.paymentAmount}</td>
                    </tr>
                </c:forEach>
            </tbody>
    </table>
</div>
</body>
</html>