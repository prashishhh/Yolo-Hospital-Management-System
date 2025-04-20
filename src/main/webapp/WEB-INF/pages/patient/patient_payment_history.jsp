<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <%-- JSP directive to set language and encoding --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title> <%-- Page title placeholder --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/patient_payment_history.css" /> <%-- Linking to external CSS file for styling --%>
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp" />
<div class="container"> <%-- Main container div --%>
        
        <main> <%-- Main content of the page --%>
            <!-- Payment History View -->
            <div class="section-header">
                <h2>Payment History</h2> <%-- Section title --%>
            </div>
            
            <table class="payment-table"> <%-- Table displaying payment records --%>
                <thead>
                    <tr> <%-- Table headers --%>
                        <th>Invoice ID</th>
                        <th>Date</th>
                        <th>Description</th>
                        <th>Amount</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr> <%-- First payment record --%>
                        <td>#INV-001</td>
                        <td>01/10/2023</td>
                        <td>Consultation with Dr. Girish Raj Thapa</td>
                        <td>$150</td>
                        <td><span class="status status-confirmed">Paid</span></td> <%-- Status with styling class --%>
                        <td><button class="btn btn-primary">View Invoice</button></td> <%-- Action button --%>
                    </tr>
                    <tr> <%-- Second payment record --%>
                        <td>#INV-002</td>
                        <td>15/03/2022</td>
                        <td>Appendectomy Surgery</td>
                        <td>$2000</td>
                        <td><span class="status status-confirmed">Paid</span></td>
                        <td><button class="btn btn-primary">View Invoice</button></td>
                    </tr>
                    <tr> <%-- Third payment record --%>
                        <td>#INV-003</td>
                        <td>25/03/2025</td>
                        <td>Consultation with Dr. Shiwans Shah</td>
                        <td>$100</td>
                        <td><span class="status status-cancelled">Cancelled</span></td> <%-- Cancelled payment --%>
                        <td><button class="btn btn-primary">View Details</button></td> <%-- Action may differ based on status --%>
                    </tr>
                </tbody>
            </table>
        </main>
    </div>
</body>
</html>
