<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Room</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/add_doctor.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/pages/header.jsp" />

    <div class="main-content">
        <div class="card">
            <div class="form-container">
                <div class="form-header">
                    <h1 class="form-title">Add Room</h1>
                </div>

                <!-- Display error message if available -->
                <c:if test="${not empty error}">
                    <p class="error-message">${error}</p>
                </c:if>
                <!-- Display success message if available -->
                <c:if test="${not empty success}">
                    <p class="success-message">${success}</p>
                </c:if>

                <form action="${pageContext.request.contextPath}/add-room" method="post">
                    <div class="form-section-title">Room Information</div>

                    <div class="form-group">
                        <label>Room Name</label>
                        <input type="text" name="roomName" value="${roomName}" placeholder="Enter room name" required>
                    </div>

                    <div class="form-group">
                        <label>Room Type</label>
                        <select name="roomType" required>
                            <option value="" disabled ${roomType == null ? 'selected' : ''}>Select room type</option>
                            <option value="General Ward" ${roomType == 'General Ward' ? 'selected' : ''}>General Ward</option>
                            <option value="Private Room" ${roomType == 'Private Room' ? 'selected' : ''}>Private Room</option>
                            <option value="ICU" ${roomType == 'ICU' ? 'selected' : ''}>ICU</option>
                            <option value="Operation Theatre" ${roomType == 'Operation Theatre' ? 'selected' : ''}>Operation Theatre</option>
                            <option value="Emergency Room" ${roomType == 'Emergency Room' ? 'selected' : ''}>Emergency Room</option>
                            <option value="Other" ${roomType == 'Other' ? 'selected' : ''}>Other</option>
                        </select>
                    </div>

                    <div class="form-actions">
                        <button type="button" class="form-btn btn-secondary" onclick="window.history.back()">Cancel</button>
                        <button type="submit" class="form-btn btn-primary">Add Room</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>