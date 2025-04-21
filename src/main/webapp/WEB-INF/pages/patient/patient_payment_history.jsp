<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
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
    
    <div class="search-control">
        <input type="text" placeholder="Search payments...">
    </div>
    
    <table>
        <thead>
            <tr>
                <th>Invoice ID</th>
                <th>Date</th>
                <th>Description</th>
                <th>Amount</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>01/10/2023</td>
                <td>Consultation with Dr. Girish Raj Thapa</td>
                <td>$150</td>
            </tr>
            <tr>
                <td>2</td>
                <td>15/03/2022</td>
                <td>Appendectomy Surgery</td>
                <td>$2000</td>
            </tr>
            <tr>
                <td>3</td>
                <td>25/03/2025</td>
                <td>Consultation with Dr. Shiwans Shah</td>
                <td>$100</td>
            </tr>
        </tbody>
    </table>
</div>
</body>
</html>