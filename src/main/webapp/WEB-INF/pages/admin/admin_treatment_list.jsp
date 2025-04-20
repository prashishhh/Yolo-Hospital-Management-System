<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Treatment Overview</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin_treatment_list.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />
	
	<!-- Main Section -->
  <main>
    <div class="container">
      <h2>Our Treatments</h2>

      <!-- Treatment Cards Section -->
      <div class="treatment">
        <input type="checkbox" id="treatment1">
        <label for="treatment1">
          <h3>Physiotherapy - Dr. Alice Smith</h3>
          <span class="arrow">&#9654;</span>
        </label>
        <div class="treatment-content">
          <p>
            Our physiotherapy program includes mobility exercises, joint manipulation, dry needling, and customized rehabilitation tailored to your recovery goals.
          </p>
          
          <!-- Doctor's Remarks Section -->
          <div class="doctor-remarks">
            <h4>Doctor's Remarks</h4>
            <p>"For optimal results, I recommend combining these physiotherapy sessions with light swimming twice a week. Most patients show significant improvement within 4-6 weeks with consistent therapy."</p>
          </div>
        </div>
      </div>

      <div class="treatment">
        <input type="checkbox" id="treatment2">
        <label for="treatment2">
          <h3>Cardiac Rehab - Dr. John Wells</h3>
          <span class="arrow">&#9654;</span>
        </label>
        <div class="treatment-content">
          <p>
            Includes monitored workouts, stress management, and heart evaluations. Personalized based on your medical history.
          </p>
          
          <!-- Doctor's Remarks Section -->
          <div class="doctor-remarks">
            <h4>Doctor's Remarks</h4>
            <p>"Cardiac rehabilitation is most effective when started within 2-4 weeks post-procedure. We'll monitor your vitals closely during sessions and adjust intensity based on your progress."</p>
          </div>
        </div>
      </div>

      <div class="treatment">
        <input type="checkbox" id="treatment3">
        <label for="treatment3">
          <h3>Post-Surgery Recovery - Dr. Emily Tran</h3>
          <span class="arrow">&#9654;</span>
        </label>
        <div class="treatment-content">
          <p>
            Focused on wound care, mobility support, and pain management to ensure a smooth and safe recovery after surgery.
          </p>
          
          <!-- Doctor's Remarks Section -->
          <div class="doctor-remarks">
            <h4>Doctor's Remarks</h4>
            <p>"Proper post-op care reduces recovery time by up to 30%. We'll provide detailed instructions for home care between sessions, including wound cleaning and movement restrictions."</p>
          </div>
        </div>
      </div>
    </div>
  </main>
</body>
</html>