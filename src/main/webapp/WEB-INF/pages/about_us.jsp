<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <!-- 
    Head section for including metadata, fonts, stylesheets.
    Includes external fonts and custom CSS for styling.
  -->
  <meta charset="UTF-8">
  <title>YOLO Hospital</title>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap">
  <link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/css/about_us.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>

	<jsp:include page="header.jsp" />

  <!-- 
    Hero Section:
    Displays a large background image with a welcome message and key hospital stats.
  -->
  <section class="hero">
    <img src="${pageContext.request.contextPath}/resources/images/register.jpg" alt="Stethescope" class="hero-bg-image">
    <div class="hero-content">
      <h1>Exceptional Care <br><span>For Every Generation</span></h1>
      <p>At YOLO Hospital, we combine clinical excellence with compassionate care to serve your entire family's health needs.</p>
      <div class="hero-stats">
        <div class="stat-item">
          <div class="stat-number">250+</div>
          <div class="stat-label">Expert Doctors</div>
        </div>
        <div class="stat-item">
          <div class="stat-number">500+</div>
          <div class="stat-label">Beds Available</div>
        </div>
        <div class="stat-item">
          <div class="stat-number">24/7</div>
          <div class="stat-label">Emergency Care</div>
        </div>
      </div>
      <div class="hero-cta">
  		<a href="${pageContext.request.contextPath}/book-appointment" class="primary-btn">Make An Appointment Now ➜</a>
	  </div>
	      
    </div>
  </section>

  <!-- 
    Services Section:
    Highlights three core service areas with icon, image and brief text.
  -->
  <section class="services">
    <h2 class="section-title">Our Specialized Services</h2>
    <p class="section-subtitle">Comprehensive healthcare solutions for all your needs</p>

    <div class="services-grid">

      <!-- Hospitality Service Card -->
      <div class="service-card">
        <img src="${pageContext.request.contextPath}/resources/images/hospitality.png" alt="Hospitality" class="card-bg-image">
        <div class="service-content">
          <div class="service-icon"><i class="fas fa-hotel"></i></div>
          <h3>Hospitality</h3>
          <p>Clinical excellence must be the priority for any healthcare service provider.</p>
        </div>
      </div>

      <!-- Emergency Care Service Card (includes contact number) -->
      <div class="service-card">
        <img src="${pageContext.request.contextPath}/resources/images/emergency.png" alt="Emergency" class="card-bg-image">
        <div class="service-content">
          <div class="service-icon"><i class="fas fa-ambulance"></i></div>
          <h3>Emergency Care</h3>
          <p>24/7 emergency support by the best medical team in the region.</p>
          <p><i class="fas fa-phone"></i> +10 272 356 3567</p>
        </div>
      </div>

      <!-- Chamber Service Card -->
      <div class="service-card">
        <img src="${pageContext.request.contextPath}/resources/images/chamber.png" alt="Chamber" class="card-bg-image">
        <div class="service-content">
          <div class="service-icon"><i class="fas fa-stethoscope"></i></div>
          <h3>Chamber Service</h3>
          <p>Private consultation chambers with advanced facilities and caring staff.</p>
        </div>
      </div>

    </div>
  </section>

  <!-- 
    Mission Section:
    Displays the hospital's mission, vision, and a visual image.
  -->
  <section class="mission-section">
    <div class="mission-content">
      <div class="mission-text">
        <h2>Our Mission & Vision</h2>
        <p>To provide exceptional, patient-centered healthcare through innovation, compassion, and clinical excellence. We envision a healthier community where everyone has access to quality medical care.</p>
        <ul class="mission-list">
          <li><i class="fas fa-check-circle"></i> Patient-first approach in all services</li>
          <li><i class="fas fa-check-circle"></i> Cutting-edge medical technology</li>
          <li><i class="fas fa-check-circle"></i> Continuous staff training and development</li>
          <li><i class="fas fa-check-circle"></i> Community health initiatives</li>
        </ul>
      </div>
      <div class="mission-image">
        <img src="${pageContext.request.contextPath}/resources/images/discussion.png" alt="Discussion" class="mission-img">
      </div>
    </div>
  </section>
  
  <jsp:include page="footer.jsp" />

</body>
</html>
