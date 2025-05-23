<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Treatment</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/add_treatment.css" />
<script>
    function updateAppointmentId(selectElement) {
        var hiddenInput = document.getElementById('hiddenAppointmentId');
        hiddenInput.value = selectElement.value;
    }
</script>
</head>
<body>
    <jsp:include page="/WEB-INF/pages/header.jsp" />

    <div class="main-content">
        <div class="card">
            <div class="form-container">
                <div class="form-header">
                    <h1 class="form-title">Add Treatment</h1>
                </div>

                <c:if test="${not empty error}">
                    <p class="error-message">${error}</p>
                </c:if>
                <c:if test="${not empty success}">
                    <p class="success-message">${success}</p>
                </c:if>

                <c:choose>
                    <c:when test="${empty bookedAppointments}">
                        <p>No booked appointments available to add treatment.</p>
                    </c:when>
                    <c:otherwise>
                        <form action="${pageContext.request.contextPath}/add-treatment" method="post">
                            <div class="form-group">
                                <label>Select Appointment</label>
                                <select name="appointmentSelect" onchange="updateAppointmentId(this)" required>
                                    <option value="" disabled ${empty appointmentId ? 'selected' : ''}>Select an appointment</option>
                                    <c:forEach var="userAppointment" items="${bookedAppointments}">
                                        <option value="${userAppointment.appointment.appointmentID}"
                                            ${userAppointment.appointment.appointmentID == appointmentId ? 'selected' : ''}>
                                            Patient: ${userAppointment.user.firstName} ${userAppointment.user.lastName}, 
                                            Doctor: ${userAppointment.appointment.doctor.doctorFirstName} ${userAppointment.appointment.doctor.doctorLastName}, 
                                            Date: ${userAppointment.appointment.appointmentDate}
                                        </option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" id="hiddenAppointmentId" name="hiddenAppointmentId" value="${appointmentId}">
                            </div>

                            <div class="form-section-title">Treatment Information</div>

                            <div class="form-group">
                                <label>Treatment Name</label>
                                <input type="text" name="treatmentName" value="${treatmentName}" 
                                    placeholder="Enter treatment name" required>
                            </div>

                            <div class="form-group">
                                <label>Treatment Plan</label>
                                <textarea name="treatmentPlan" placeholder="Enter treatment plan details" 
                                    required>${treatmentPlan}</textarea>
                            </div>

                            <div class="form-group">
                                <label>Remarks</label>
                                <textarea name="remarks" placeholder="Enter any additional remarks">${remarks}</textarea>
                            </div>

                            <div class="form-actions">
                                <button type="button" class="form-btn btn-secondary" 
                                    onclick="window.history.back()">Cancel</button>
                                <button type="submit" class="form-btn btn-primary">Add Treatment</button>
                            </div>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</body>
</html>