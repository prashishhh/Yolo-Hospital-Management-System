<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css" />
	</head>
<body>

	<jsp:include page="header.jsp" />
	
	<!-- Main content -->
    <main>
        <!-- welcome banner section -->
        <section class="welcome-banner">
            <div class="container">
                <div class="welcome-banner__content">
                    <div class="welcome-banner__text">
                        <div class="welcome-banner__subtitle">WELCOME TO OUR HOSPITAL</div>
                        <h1 class="welcome-banner__title">TAKE CARE OF YOUR HEALTH</h1>
                        <p class="welcome-banner__description">
                        At Yolo Hospital, we are dedicated to providing compassionate, high-quality healthcare tailored to every patient's needs. With a team of experienced medical professionals and state-of-the-art facilities, we ensure that your health and well-being are our top priorities. From preventive care to advanced treatments, we are here to support you on every step of your health journey.
                        </p>
                            
                        <a href="about.html" class="btn btn--primary btn--large">More About Us</a>
                    </div>
                    <div class="welcome-banner__image">
                        <img src= "${pageContext.request.contextPath}/resources/images/hospital1.jpg" alt="Healthcare professionals">
                    </div>
                </div>
            </div>
        </section>

        <!-- Features Section -->
        <section class="features">
            <div class="container">
                <h2 class="section-title">Why Choose Us</h2>
                <p class="section-subtitle">Trusted care, expert professionals, and a commitment to your well-being—every step of the way.</p>

                <div class="features__grid">
                    <div class="feature-card">
                        <div class="feature-card__icon">🏆</div>
                        <h3 class="feature-card__title">Award Winning</h3>
                        <p class="feature-card__description">
                        Recognized nationally for excellence in patient care and medical innovation.
                        </p>
                    </div>

                    <div class="feature-card">
                        <div class="feature-card__icon">👨‍⚕️</div>
                        <h3 class="feature-card__title">Expert Doctors</h3>
                        <p class="feature-card__description">
                        Highly qualified specialists dedicated to providing the best medical outcomes.
                        </p>
                    </div>

                    <div class="feature-card">
                        <div class="feature-card__icon">💰</div>
                        <h3 class="feature-card__title">Fair Prices</h3>
                        <p class="feature-card__description">
                        Affordable healthcare with clear, transparent pricing—no hidden costs.
                        </p>
                    </div>

                    <div class="feature-card">
                        <div class="feature-card__icon">🎧</div>
                        <h3 class="feature-card__title">24/7 Support</h3>
                        <p class="feature-card__description">
                        Round-the-clock assistance to ensure you're never alone in your care journey.
                        </p>
                    </div>
                </div>
            </div>
        </section>
        
        <!-- CTA Banner -->
        <section class="cta-banner">
            <div class="container">
                <h2 class="cta-banner__heading">YOUR JOURNEY TO WELLNESS BEGINS HERE</h2>
                <p class="cta-banner__text">
                Schedule your appointment today and embark on a health journey where you are the hero. Your well-being starts with us.
                </p>
                <a href="appointment.html" class="btn btn--white btn--large">Book Appointment</a>
            </div>
        </section>

        <!-- Statistics Section -->
        <section class="statistics">
            <div class="container">
                <div class="statistics__grid">
                    <div class="stat-card">
                        <div class="stat-card__value">1,000+</div>
                        <div class="stat-card__title">Happy Patients</div>
                    </div>
                    
                    <div class="stat-card">
                        <div class="stat-card__value">60+</div>
                        <div class="stat-card__title">Expert Doctors</div>
                    </div>
                    
                    <div class="stat-card">
                        <div class="stat-card__value">500+</div>
                        <div class="stat-card__title">Successful Treatments</div>
                    </div>
                    
                    <div class="stat-card">
                        <div class="stat-card__value">24/7</div>
                        <div class="stat-card__title">Support Available</div>
                    </div>
                </div>
            </div>
        </section>
  </main>
  
  <jsp:include page="footer.jsp" />
</body>
</html>