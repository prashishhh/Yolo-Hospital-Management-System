<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Payment.jsp - A secure checkout form for processing payment details.
    This page is divided into a left image section and a right form section.
--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment</title>

    <%-- Link to the external CSS file for styling the payment form --%>
    <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/payment.css" />
</head>
<body>

    
    <%-- Main card container for payment form layout --%>
    <div class="card">
    
    <c:if test="${not empty error}">
    <p class="error-message">${error}</p>
</c:if>

        <%-- Left section: displays payment-related image --%>
        <div class="left-section">
            <div class="image-container">
                <img src="${pageContext.request.contextPath}/resources/images/payment.png" 
                     alt="payment image" class="payment-image">
            </div>
        </div>
        
        <%-- Right section: contains the payment form --%>
        <div class="right-section">
            
            <%-- Form header with title and tagline --%>
            <div class="form-header">
                <div class="form-small-text">SECURE CHECKOUT</div>
                <h1 class="form-title">Payment Details<span></span></h1>
                <div class="tagline">Complete your purchase securely</div>
            </div>
            
            <%-- Payment form starts here --%>
            <form action="${pageContext.request.contextPath}/payment" method="post">
    <div class="form-group">
        <label>Card Number</label>
        <div class="card-input-row">
            <input type="text" name="cardNumber1" placeholder="0000" maxlength="4">
            <input type="text" name="cardNumber2" placeholder="0000" maxlength="4">
            <input type="text" name="cardNumber3" placeholder="0000" maxlength="4">
            <input type="text" name="cardNumber4" placeholder="0000" maxlength="4">
        </div>
    </div>
    <div class="form-group">
        <label>Cardholder Name</label>
        <input type="text" name="cardholderName" placeholder="Enter name as it appears on card">
    </div>
    <div class="form-group">
        <div class="card-input-row">
            <div style="flex: 1;">
                <label>Expiry Date</label>
                <div class="card-date">
                    <input type="text" name="expiryMonth" placeholder="MM" maxlength="2" style="width: 60px;">
                    <span class="date-slash">/</span>
                    <input type="text" name="expiryYear" placeholder="YY" maxlength="2" style="width: 60px;">
                </div>
            </div>
            <div style="flex: 1;">
                <label>CVV</label>
                <input type="text" name="cvv" placeholder="CVV" maxlength="4">
            </div>
        </div>
    </div>
    <div class="payment-total">
        <div>You have to Pay</div>
        <div class="total-price">Rs.${selectedAppointment.appointmentFee}<span class="total-cents"></span></div>
    </div>
    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="value" value="pay">Pay Now</button>
    </div>
    <div class="form-actions" style="padding-top: 5px">
        <button type="submit" class="btn btn-secondary" name="value" value="cancel">Cancel</button>
    </div>
</form>
        </div>
    </div>
</body>
</html>
