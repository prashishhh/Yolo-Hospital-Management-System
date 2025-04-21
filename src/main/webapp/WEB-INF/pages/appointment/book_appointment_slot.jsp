<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 
    book_appointment_slot.jsp - Displays a single doctor's profile and lets the user choose 
    a date and time to book an appointment.
--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Appointment Slot</title>

    <%-- Link to external CSS for styling the slot booking form --%>
    <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/book_appointment_slot.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />

    <%-- Page wrapper container --%>
    <div class="container">
    
        <%-- Booking section container --%>
        <div class="booking-container">
        
            <%-- Doctor profile card --%>
            <div class="doctor-card">
                <div class="doctor-image-container">
                    <img class="doctor-image" src="${pageContext.request.contextPath}/resources/images/doctors/prashsihsihss.jpg" alt="Dr. Prashish Sapkota">
                </div>
                <div class="doctor-info">
                    <h2>Dr. Prashish Sapkota</h2>
                    <p>MBBS, MS (General Surgery), MCh (Neurosurgery)</p>
                    <span class="specialty">NeuroSurgeon</span>
                </div>
            </div>

            <%-- Appointment booking form --%>
            <form class="appointment-form" action="/book-appointment" method="post">
                
                <%-- Date and Time selection row --%>
                <div class="form-row">
                
                    <%-- Appointment Date input --%>
                    <div class="form-group">
                        <label for="appointment-date">Appointment Date</label>
                        <input type="date" id="appointment-date" class="form-control" 
                               name="appointment-date" min="2025-04-16" value="2025-04-16" required>
                    </div>
                    
                    <%-- Time Slot dropdown --%>
                    <div class="form-group">
                        <label for="appointment-time">Time Slot</label>
                        <select id="appointment-time" class="form-control" 
                                name="appointment-time" required>
                            <option value="">Select a time</option>
                            <option value="09:30">9:30 AM</option>
                            <option value="09:45">10:00 AM</option>
                            <option value="10:00">10:30 AM</option>
                            <option value="10:15">11:00 AM</option>
                            <option value="10:30">12:30 PM</option>
                            <option value="13:30">1:30 PM</option>
                            <option value="14:15">2:00 PM</option>
                            <option value="15:00">3:00 PM</option>
                            <option value="15:45">3:30 PM</option>
                            <option value="16:00">4:00 PM</option>
                        </select>
                    </div>
                </div>

                <%-- Doctor’s Fee Section --%>
                <div class="fee-container">
                    <span class="fee-label">Fees:</span>
                    <span class="fee-amount">Rs. 3000</span>
                </div>

                <%-- Submit button to book appointment --%>
                <a href="${pageContext.request.contextPath}/payment" class="booking-button">Book appointment</a>
            </form>
        </div> <%-- End of booking-container --%>
    </div> <%-- End of container --%>

</body>
</html>
