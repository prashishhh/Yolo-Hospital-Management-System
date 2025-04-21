<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <input type="text" placeholder="Search rooms...">
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
                <tr>
                    <td>1</td>
                    <td>Sunshine Room</td>
                    <td class="room-type">General Ward</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Ocean View</td>
                    <td class="room-type">Private</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Rainbow Room</td>
                    <td class="room-type">Pediatric</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>Comfort Suite</td>
                    <td class="room-type">ICU</td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>Harmony Room</td>
                    <td class="room-type">General Ward</td>
                </tr>
                <tr>
                    <td>6</td>
                    <td>Executive Suite</td>
                    <td class="room-type">VIP</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>