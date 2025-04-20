<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Appointment Overview</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin_appointment_list.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />
	
	<div class="content">
        <div class="header-container">
            <h2 class="list-header">Appointments List</h2>
            <a href="${pageContext.request.contextPath}/create-appointment" class="add-appointment-btn">+ Create Appointment</a>
        </div>
        
        <div class="search-control">
            <input type="text" placeholder="Search appointments...">
        </div>
        <table>
            <thead>
                <tr>
                    <th>Appointment ID</th>
                    <th>Patient Name</th>
                    <th>Doctor Name</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Room</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>Aarati Sharma</td>
                    <td>Dr. Rajesh Adhikari</td>
                    <td>15-Apr-2025</td>
                    <td>10:00 AM - 10:30 AM</td>
                    <td>Room 101</td>
                    <td><span class="status booked">Booked</span></td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Bijay Thapa</td>
                    <td>Dr. Sabina Karki</td>
                    <td>15-Apr-2025</td>
                    <td>11:00 AM - 11:30 AM</td>
                    <td>Room 202</td>
                    <td><span class="status completed">Completed</span></td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Deepak Poudel</td>
                    <td>Dr. Nabin Shrestha</td>
                    <td>15-Apr-2025</td>
                    <td>2:00 PM - 2:45 PM</td>
                    <td>Room 105</td>
                    <td><span class="status booked">Booked</span></td>
                </tr>
                <tr>
                    <td>4</td>
                    <td></td>
                    <td>Dr. Rajesh Adhikari</td>
                    <td>16-Apr-2025</td>
                    <td>9:15 AM - 9:45 AM</td>
                    <td>Room 101</td>
                    <td><span class="status not-booked">Not Booked</span></td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>Prakash Gurung</td>
                    <td>Dr. Sabina Karki</td>
                    <td>16-Apr-2025</td>
                    <td>1:30 PM - 2:00 PM</td>
                    <td>Room 204</td>
                    <td><span class="status booked">Booked</span></td>
                </tr>
                <tr>
                    <td>6</td>
                    <td>Manisha Rai</td>
                    <td>Dr. Nabin Shrestha</td>
                    <td>17-Apr-2025</td>
                    <td>11:30 AM - 12:00 PM</td>
                    <td>Room 103</td>
                    <td><span class="status completed">Completed</span></td>
                </tr>
                <tr>
                    <td>7</td>
                    <td>Ram Bahadur KC</td>
                    <td>Dr. Rajesh Adhikari</td>
                    <td>17-Apr-2025</td>
                    <td>3:00 PM - 3:30 PM</td>
                    <td>Room 303</td>
                    <td><span class="status booked">Booked</span></td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>