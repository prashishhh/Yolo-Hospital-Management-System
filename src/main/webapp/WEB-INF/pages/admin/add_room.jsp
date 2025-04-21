<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/add_room.css" />
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />
	
	<div class="main-content">
    <div class="card">
      <div class="form-container">
        <div class="form-header">
          <h1 class="form-title">Add Room</h1>
        </div>
        
        <form>
          
          <div class="form-section-title">Room Information</div>
          
          <div class="form-group">
            <label>Room Name</label>
            <input type="text" name="roomName" placeholder="Enter room name (e.g. Room 101)" required>
          </div>
          
          <div class="form-group">
            <label>Room Type</label>
            <select name="roomType" required>
              <option value="" disabled selected>Select room type</option>
              <option value="examination">Examination</option>
              <option value="consultation">Consultation</option>
              <option value="procedure">Procedure</option>
              <option value="operation">Operation</option>
              <option value="recovery">Recovery</option>
              <option value="ward">Ward</option>
            </select>
          </div>
          
          <div class="form-actions">
            <button type="button" class="form-btn btn-secondary">Cancel</button>
            <button type="submit" class="form-btn btn-primary">Add Room</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</body>
</html>