<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patient Appointment List</title>
    
    <!-- Link to external CSS for styling the appointment list -->
    <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/patient_appointment.css" />
</head>
<body>

	<jsp:include page="/WEB-INF/pages/header.jsp" />
	
    <div class="content">
    
        <!-- Header section for the appointments list -->
        <div class="header-container">
            <h2 class="list-header">Appointments List</h2>
        </div>
        
        <!-- Search bar to filter appointments -->
        <div class="search-control">
            <input type="text" placeholder="Search appointments...">
        </div>
        
        <!-- Appointment list table -->
        <table>
            <thead>
                <tr>
                    <th>Appointment ID</th>
                    <th>Doctor Name</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Room</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <!-- Sample row: Booked appointment with Dr. Rajesh Adhikari -->
                <tr>
                    <td>1</td>
                    <td>Dr. Rajesh Adhikari</td>
                    <td>15-Apr-2025</td>
                    <td>10:00 AM - 10:30 AM</td>
                    <td>Room 101</td>
                    <td><span class="status booked">Booked</span></td>
                </tr>
                
                <!-- Completed appointment with Dr. Sabina Karki -->
                <tr>
                    <td>2</td>
                    <td>Dr. Sabina Karki</td>
                    <td>15-Apr-2025</td>
                    <td>11:00 AM - 11:30 AM</td>
                    <td>Room 202</td>
                    <td><span class="status completed">Completed</span></td>
                </tr>
                
                <!-- Another booked appointment with Dr. Nabin Shrestha -->
                <tr>
                    <td>3</td>
                    <td>Dr. Nabin Shrestha</td>
                    <td>15-Apr-2025</td>
                    <td>2:00 PM - 2:45 PM</td>
                    <td>Room 105</td>
                    <td><span class="status booked">Booked</span></td>
                </tr>
                
                <!-- Not booked appointment -->
                <tr>
                    <td>4</td>
                    <td>Dr. Rajesh Adhikari</td>
                    <td>16-Apr-2025</td>
                    <td>9:15 AM - 9:45 AM</td>
                    <td>Room 101</td>
                    <td><span class="status not-booked">Not Booked</span></td>
                </tr>
                
                <!-- Booked appointment for Dr. Sabina Karki -->
                <tr>
                    <td>5</td>
                    <td>Dr. Sabina Karki</td>
                    <td>16-Apr-2025</td>
                    <td>1:30 PM - 2:00 PM</td>
                    <td>Room 204</td>
                    <td><span class="status booked">Booked</span></td>
                </tr>
                
                <!-- Completed appointment with Dr. Nabin Shrestha -->
                <tr>
                    <td>6</td>
                    <td>Dr. Nabin Shrestha</td>
                    <td>17-Apr-2025</td>
                    <td>11:30 AM - 12:00 PM</td>
                    <td>Room 103</td>
                    <td><span class="status completed">Completed</span></td>
                </tr>
                
                <!-- Another booked appointment with Dr. Rajesh Adhikari -->
                <tr>
                    <td>7</td>
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
