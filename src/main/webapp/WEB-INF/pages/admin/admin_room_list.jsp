<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Room Overview</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin_room_list.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />
		
	<div class="content">
        <div class="header-container">
            <h2 class="list-header">Rooms List</h2>
            <a href="${pageContext.request.contextPath}/add-room" class="add-room-btn">Add Room</a>
        </div>

        <div class="search-control">
                <form action="${pageContext.request.contextPath}/admin-room-list" method="get">
                    <input type="text" name="search" placeholder="Search appointments...." value="${param.search}">
                    <button type="submit"><img class="search-icon" src="${pageContext.request.contextPath}/resources/images/searchbar.png" alt="search bar"></button>
                </form>
            </div>
        <table>
            <thead>
                <tr>
                    <th>Room ID</th>
                    <th>Room Name</th>
                    <th>Room Type</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach var="room" items="${roomList }">
                <tr>
                    <td>${room.roomID }</td>
                    <td>${room.roomName }</td>
                    <td class="room-type">${room.roomType }</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>