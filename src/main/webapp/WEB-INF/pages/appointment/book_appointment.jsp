<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 
    book_appointment.jsp - Displays a list of doctors with the option to book appointments.
    Each doctor is shown in a card layout with their image, name, specialty, and a booking button.
--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book an Appointment</title>

    <%-- Link to external CSS file for page styling --%>
    <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/book_appointment.css" />
</head>
<body>

	<jsp:include page="/WEB-INF/pages/header.jsp" />

    <%-- Main container for appointment page content --%>
    <div class="main-content">
        
        <%-- Page title --%>
        <h1 class="page-title">Book an Appointment</h1>
        
        <%-- Grid layout containing doctor cards --%>
        <div class="doctor-grid">
            
            <%-- Doctor Card: Dr. Prashish Sapkota --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                     <img src="${pageContext.request.contextPath}/resources/images/doctors/prashsihsihss.jpg" alt="Dr. Prashish Sapkota">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. Prashish Sapkota</h3>
                    <div class="doctor-specialty">Neuro Surgeon</div>
                </div>
                <div class="appointment-btn-container">
                    <a href="${pageContext.request.contextPath}/book-appointment-slot" class="appointment-btn">Book appointment</a>
                </div>
            </div>

            <%-- Doctor Card: Dr. Girish Raj Thapa --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                    <img src="${pageContext.request.contextPath}/resources/images/doctors/girish.jpg" alt="Dr. Girish Raj Thapa">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. Girish Raj Thapa</h3>
                    <div class="doctor-specialty">Psychiatrist</div>
                </div>
                <div class="appointment-btn-container">
                    <a href="${pageContext.request.contextPath}/book-appointment-slot" class="appointment-btn">Book appointment</a>
                </div>
            </div>

            <%-- Doctor Card: Dr. Shiwans Shah --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                    <img src="${pageContext.request.contextPath}/resources/images/doctors/Shiwans.jpg" alt="Dr. Shiwans Shah">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. Shiwans Shah</h3>
                    <div class="doctor-specialty">Cardiologist</div>
                </div>
                <div class="appointment-btn-container">
                    <a href="${pageContext.request.contextPath}/book-appointment-slot" class="appointment-btn">Book appointment</a>
                </div>
            </div>

            <%-- Doctor Card: Dr. Jeshan Gurung --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                    <img src="${pageContext.request.contextPath}/resources/images/doctors/jeshan.jpg" alt="Dr. Jeshan Gurung">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. Jeshan Gurung</h3>
                    <div class="doctor-specialty">Lung Specialist</div>
                </div>
                <div class="appointment-btn-container">
                    <a href="${pageContext.request.contextPath}/book-appointment-slot" class="appointment-btn">Book appointment</a>
                </div>
            </div>

            <%-- Doctor Card: Dr. Pradeep Baral --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                    <img src="${pageContext.request.contextPath}/resources/images/doctors/pradeepa.jpg" alt="Dr. Pradeep Baral">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. Pradeep Baral</h3>
                    <div class="doctor-specialty">Dermatologist</div>
                </div>
                <div class="appointment-btn-container">
                    <a href="${pageContext.request.contextPath}/book-appointment-slot" class="appointment-btn">Book appointment</a>
                </div>
            </div>

            <%-- Doctor Card: Dr. J P Jaiswal --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                     <img src="${pageContext.request.contextPath}/resources/images/doctors/Jason.jpg" alt="Dr. J P Jaiswal">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. J P Jaiswal</h3>
                    <div class="doctor-specialty">Cardiologist</div>
                </div>
                <div class="appointment-btn-container">
                    <a href="${pageContext.request.contextPath}/book-appointment-slot" class="appointment-btn">Book appointment</a>
                </div>
            </div>

            <%-- Doctor Card: Dr. Rajib Pande --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                    <img src="${pageContext.request.contextPath}/resources/images/doctors/pandey.jpg" alt="Dr. Rajib Pande">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. Rajib Pande</h3>
                    <div class="doctor-specialty">Cardiologist</div>
                </div>
                <div class="appointment-btn-container">
                    <a href="${pageContext.request.contextPath}/book-appointment-slot" class="appointment-btn">Book appointment</a>
                </div>
            </div>

            <%-- Doctor Card: Dr. Parag Karki --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                      <img src="${pageContext.request.contextPath}/resources/images/doctors/karki.jpg" alt="Dr. Parag Karki">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. Parag Karki</h3>
                    <div class="doctor-specialty">Cardiologist</div>
                </div>
                <div class="appointment-btn-container">
                    <a href="${pageContext.request.contextPath}/book-appointment-slot" class="appointment-btn">Book appointment</a>
                </div>
            </div>

            <%-- Doctor Card: Dr. Sofia Malla --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                    <img src="${pageContext.request.contextPath}/resources/images/doctors/sofia.jpg" alt="Dr. Sofia Malla">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. Sofia Malla</h3>
                    <div class="doctor-specialty">Orthodontist</div>
                </div>
                <div class="appointment-btn-container">
                    <a href="${pageContext.request.contextPath}/book-appointment-slot" class="appointment-btn">Book appointment</a>
                </div>
            </div>
            
        </div> <%-- End of doctor-grid --%>
    </div> <%-- End of main-content --%>

</body>
</html>
