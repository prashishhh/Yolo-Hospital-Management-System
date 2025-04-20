<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <!-- Link to external CSS for styling the patient dashboard -->
    <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/patient_dashboard.css" />
</head>
<body>

	<jsp:include page="/WEB-INF/pages/header.jsp" />

    <!-- Main content container -->
    <main>
        <div class="container">
        
            <!-- Welcome section with dashboard introduction -->
            <div class="welcome-section">
                <div class="welcome-text">
                    <h1>Welcome to Your Patient Dashboard</h1>
                    <p>Manage your appointments, treatments, and medical information in one place.</p>
                </div>
            </div>
            
            <!-- Section showing statistics like upcoming appointments and active treatments -->
            <div class="stats-section">
                <!-- Upcoming appointments card -->
                <div class="stat-card appointments">
                    <div class="stat-header">
                        <span class="stat-title">Upcoming Appointments</span>
                        <div class="stat-icon appointments-icon"><i class="fas fa-calendar-check"></i></div>
                    </div>
                    <div class="stat-value">2</div>
                    <div class="stat-text">You have 2 upcoming appointments this month</div>
                </div>
                
                <!-- Active treatments card -->
                <div class="stat-card treatments">
                    <div class="stat-header">
                        <span class="stat-title">Active Treatments</span>
                        <div class="stat-icon treatments-icon"><i class="fas fa-notes-medical"></i></div>
                    </div>
                    <div class="stat-value">1</div>
                    <div class="stat-text">You have 1 active treatment plan</div>
                </div>
            </div>
            
            <!-- Recent Appointments Table -->
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title"><i class="fas fa-calendar-check"></i> Recent Appointments</h3>
                    <a href="appointments.html" class="card-link">View All</a>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Doctor</th>
                                    <th>Date & Time</th>
                                    <th>Department</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Appointment entries -->
                                <tr>
                                    <td>Dr. Girish Raj Thapa</td>
                                    <td>April 7, 2025<br>10:30 AM</td>
                                    <td>Cardiology</td>
                                    <td><div class="badge badge-confirmed">Confirmed</div></td>
                                </tr>
                                <tr>
                                    <td>Dr. Prashish Sapkota</td>
                                    <td>April 15, 2025<br>2:15 PM</td>
                                    <td>General Medicine</td>
                                    <td><div class="badge badge-pending">Pending</div></td>
                                </tr>
                                <tr>
                                    <td>Dr. Shiwans Shah</td>
                                    <td>March 25, 2025<br>9:00 AM</td>
                                    <td>Dermatology</td>
                                    <td><div class="badge badge-cancelled">Cancelled</div></td>
                                </tr>
                                <tr>
                                    <td>Dr. Pradeep Baral</td>
                                    <td>March 25, 2025<br>9:00 AM</td>
                                    <td>Neurology</td>
                                    <td><div class="badge badge-cancelled">Cancelled</div></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            
            <!-- Treatment Plans Table -->
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title"><i class="fas fa-notes-medical"></i> Your Treatment Plans</h3>
                    <a href="treatments.html" class="card-link">View All</a>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Appointment</th>
                                    <th>Treatment</th>
                                    <th>Doctor</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Treatment entries -->
                                <tr>
                                    <td>January 15, 2025</td>
                                    <td>Hypertension Management</td>
                                    <td>Dr. Girish Raj Thapa</td>
                                </tr>
                                <tr>
                                    <td>November 10, 2024</td>
                                    <td>Physical Therapy</td>
                                    <td>Dr. Prashish Sapkota</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </main>
    
    <!-- Modal for Booking Appointment -->
    <div class="modal" id="book-appointment-modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>Book Appointment</h2>
            
            <form id="appointment-form">
                <!-- Select Doctor -->
                <div class="form-group">
                    <label for="doctor">Select Doctor</label>
                    <select id="doctor">
                        <option value="">-- Select Doctor --</option>
                        <option value="dr-girish">Dr. Girish Raj Thapa - Cardiology</option>
                        <option value="dr-prashish">Dr. Prashish Sapkota - General Medicine</option>
                        <option value="dr-shiwans">Dr. Shiwans Shah - Dermatology</option>
                        <option value="dr-pradeep">Dr. Pradeep Baral - Neurology</option>
                    </select>
                </div>
                
                <!-- Choose Date -->
                <div class="form-group">
                    <label for="appointment-date">Date</label>
                    <input type="date" id="appointment-date">
                </div>
                
                <!-- Choose Time Slot -->
                <div class="form-group">
                    <label for="appointment-time">Available Slots</label>
                    <select id="appointment-time">
                        <option value="">-- Select Time Slot --</option>
                        <option value="9:00">9:00 AM</option>
                        <option value="9:30">9:30 AM</option>
                        <option value="10:00">10:00 AM</option>
                        <option value="10:30">10:30 AM</option>
                        <option value="11:00">11:00 AM</option>
                        <option value="11:30">11:30 AM</option>
                        <option value="2:00">2:00 PM</option>
                        <option value="2:30">2:30 PM</option>
                        <option value="3:00">3:00 PM</option>
                        <option value="3:30">3:30 PM</option>
                        <option value="4:00">4:00 PM</option>
                    </select>
                </div>
                
                <!-- Reason for Visit -->
                <div class="form-group">
                    <label for="appointment-reason">Reason for Visit</label>
                    <textarea id="appointment-reason" rows="4"></textarea>
                </div>
                
                <!-- Submit Button -->
                <button type="submit" class="btn book btn-block">Book Appointment</button>
            </form>
        </div>
    </div>
    
    <!-- Modal for Viewing Appointment Details -->
    <div class="modal" id="view-appointment-modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>Appointment Details</h2>
            
            <!-- Appointment information display -->
            <div class="appointment-details">
                <p><strong>Doctor:</strong> <span id="appt-doctor">Dr. Girish Raj Thapa</span></p>
                <p><strong>Department:</strong> <span id="appt-department">Cardiology</span></p>
                <p><strong>Date & Time:</strong> <span id="appt-datetime">April 7, 2025 - 10:30 AM</span></p>
                <p><strong>Status:</strong> <span id="appt-status" class="badge badge-confirmed">Confirmed</span></p>
                <p><strong>Location:</strong> <span id="appt-location">Yolo Hospital, Room 305</span></p>
                <p><strong>Reason for Visit:</strong> <span id="appt-reason">Regular checkup for hypertension</span></p>
                
                <!-- Action buttons -->
                <div style="margin-top: 20px;">
                    <button class="btn book">Reschedule</button>
                    <button class="btn register">Cancel Appointment</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
