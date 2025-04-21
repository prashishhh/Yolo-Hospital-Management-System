<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
                <h1 class="form-title">Payment Details<span>.</span></h1>
                <div class="tagline">Complete your purchase securely</div>
            </div>
            
            <%-- Payment form starts here --%>
            <form>
                
                <%-- Card number input split into 4 boxes for UX clarity --%>
                <div class="form-group">
                    <label>Card Number</label>
                    <div class="card-input-row">
                        <input type="text" placeholder="0000" maxlength="4">
                        <input type="text" placeholder="0000" maxlength="4">
                        <input type="text" placeholder="0000" maxlength="4">
                        <input type="text" placeholder="0000" maxlength="4">
                    </div>
                </div>
                
                <%-- Cardholder name input field --%>
                <div class="form-group">
                    <label>Cardholder Name</label>
                    <input type="text" placeholder="Enter name as it appears on card">
                </div>
                
                <%-- Expiry date and CVV grouped in one row --%>
                <div class="form-group">
                    <div class="card-input-row">
                        
                        <%-- Expiry Date input fields (MM/YY) --%>
                        <div style="flex: 1;">
                            <label>Expiry Date</label>
                            <div class="card-date">
                                <input type="text" placeholder="MM" maxlength="2" style="width: 60px;">
                                <span class="date-slash">/</span>
                                <input type="text" placeholder="YY" maxlength="2" style="width: 60px;">
                            </div>
                        </div>
                        
                        <%-- CVV input field --%>
                        <div style="flex: 1;">
                            <label>CVV</label>
                            <input type="text" placeholder="CVV" maxlength="4">
                        </div>
                    </div>
                </div>
                
                <%-- Display total payment amount to be paid --%>
                <div class="payment-total">
                    <div>You have to Pay</div>
                    <div class="total-price">549<span class="total-cents">.99 USD</span></div>
                </div>
                
                <%-- Submit button for the payment form --%>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Pay Now</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
