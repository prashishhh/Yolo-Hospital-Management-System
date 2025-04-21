<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 
    This JSP page displays a team of medical specialists in a structured card-based layout.
    It uses dynamic path resolution via JSTL expressions to load CSS and image resources correctly.
    Each doctor card contains an image, name, specialization, short description, years of experience, and total patients treated.
--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Meet Our Doctors</title>

    <%-- Link to the external stylesheet for doctor section styling --%>
    <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/our_doctors.css" />
</head>
<body>

	<jsp:include page="header.jsp" />
	
    <div class="main-content">

        <%-- Page heading --%>
        <h1 class="page-title">Meet Our Doctors</h1>

        <%-- Subtitle providing brief overview of the doctors' dedication --%>
        <div class="page-subtitle">
            <p>Get the best medical care from our team of highly qualified specialists. Our doctors are committed to providing compassionate and exceptional healthcare.</p>
        </div>
        
        <%-- Container holding all doctor profile cards in grid format --%>
        <div class="doctor-grid">

            <%-- Doctor Card 1: Dr. Prashish Sapkota --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                    <img src="${pageContext.request.contextPath}/resources/images/doctors/prashsihsihss.jpg" alt="Dr. Prashish Sapkota" class="doctor-image">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. Prashish Sapkota</h3>
                    <div class="doctor-specialty">Neuro Surgeon</div>
                    <div class="doctor-description">Renowned neurosurgeon specializing in minimally invasive brain and spine surgeries with 15+ years experience.</div>
                    <div class="doctor-stats">
                        <div class="doctor-stat">
                            <div class="stat-value">7+</div>
                            <div class="stat-label">Years Exp</div>
                        </div>
                        <div class="doctor-stat">
                            <div class="stat-value">100+</div>
                            <div class="stat-label">Patients</div>
                        </div>
                    </div>
                </div>
            </div>
            
            <%-- Doctor Card 2: Dr. Girish Raj Thapa --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                    <img src="${pageContext.request.contextPath}/resources/images/doctors/girish.jpg" alt="Dr. Girish Raj Thapa" class="doctor-image">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. Girish Raj Thapa</h3>
                    <div class="doctor-specialty">Psychiatrist</div>
                    <div class="doctor-description">Compassionate psychiatrist combining modern therapeutic techniques with holistic mental health care.</div>
                    <div class="doctor-stats">
                        <div class="doctor-stat">
                            <div class="stat-value">7+</div>
                            <div class="stat-label">Years Exp</div>
                        </div>
                        <div class="doctor-stat">
                            <div class="stat-value">3000+</div>
                            <div class="stat-label">Patients</div>
                        </div>
                    </div>
                </div>
            </div>
            
            <%-- Doctor Card 3: Dr. Shiwans Shah --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                    <img src="${pageContext.request.contextPath}/resources/images/doctors/Shiwans.jpg" alt="Dr. Shiwans Shah" class="doctor-image">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. Shiwans Shah</h3>
                    <div class="doctor-specialty">Cardiologist</div>
                    <div class="doctor-description">Interventional cardiologist specializing in heart disease prevention and latest cardiac care technologies.</div>
                    <div class="doctor-stats">
                        <div class="doctor-stat">
                            <div class="stat-value">8+</div>
                            <div class="stat-label">Years Exp</div>
                        </div>
                        <div class="doctor-stat">
                            <div class="stat-value">5000+</div>
                            <div class="stat-label">Patients</div>
                        </div>
                    </div>
                </div>
            </div>
            
            <%-- Doctor Card 4: Dr. Jeshan Gurung --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                    <img src="${pageContext.request.contextPath}/resources/images/doctors/jeshan.jpg" alt="Dr. Jeshan Gurung" class="doctor-image">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. Jeshan Gurung</h3>
                    <div class="doctor-specialty">Lung Specialist</div>
                    <div class="doctor-description">Pulmonologist specializing in asthma, COPD, and sleep disorders with research contributions.</div>
                    <div class="doctor-stats">
                        <div class="doctor-stat">
                            <div class="stat-value">3+</div>
                            <div class="stat-label">Years Exp</div>
                        </div>
                        <div class="doctor-stat">
                            <div class="stat-value">2500+</div>
                            <div class="stat-label">Patients</div>
                        </div>
                    </div>
                </div>
            </div>
            
            <%-- Doctor Card 5: Dr. Pradeep Baral --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                    <img src="${pageContext.request.contextPath}/resources/images/doctors/pradeepa.jpg" alt="Dr. Pradeep Baral" class="doctor-img">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. Pradeep Baral</h3>
                    <div class="doctor-specialty">Dermatologist</div>
                    <div class="doctor-description">Expert in medical and cosmetic dermatology with advanced skin rejuvenation techniques.</div>
                    <div class="doctor-stats">
                        <div class="doctor-stat">
                            <div class="stat-value">4+</div>
                            <div class="stat-label">Years Exp</div>
                        </div>
                        <div class="doctor-stat">
                            <div class="stat-value">4000+</div>
                            <div class="stat-label">Patients</div>
                        </div>
                    </div>
                </div>
            </div>
            
            <%-- Doctor Card 6: Dr. J P Jaiswal --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                    <img src="${pageContext.request.contextPath}/resources/images/doctors/Jason.jpg" alt="Dr. J P Jaiswal" class="doctor-img">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. J P Jaiswal</h3>
                    <div class="doctor-specialty">Cardiologist</div>
                    <div class="doctor-description">Specialist in non-invasive cardiology and cardiac imaging, focused on preventive cardiology.</div>
                    <div class="doctor-stats">
                        <div class="doctor-stat">
                            <div class="stat-value">8+</div>
                            <div class="stat-label">Years Exp</div>
                        </div>
                        <div class="doctor-stat">
                            <div class="stat-value">6000+</div>
                            <div class="stat-label">Patients</div>
                        </div>
                    </div>
                </div>
            </div>
            
            <%-- Doctor Card 7: Dr. Rajib Pandey --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                    <img src="${pageContext.request.contextPath}/resources/images/doctors/pandey.jpg" alt="Dr. Rajib Pandey" class="doctor-img">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. Rajib Pandey</h3>
                    <div class="doctor-specialty">Cardiologist</div>
                    <div class="doctor-description">Interventional cardiologist expert in complex coronary interventions and preventive care.</div>
                    <div class="doctor-stats">
                        <div class="doctor-stat">
                            <div class="stat-value">6+</div>
                            <div class="stat-label">Years Exp</div>
                        </div>
                        <div class="doctor-stat">
                            <div class="stat-value">3500+</div>
                            <div class="stat-label">Patients</div>
                        </div>
                    </div>
                </div>
            </div>
            
            <%-- Doctor Card 8: Dr. Parag Karki --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                    <img src="${pageContext.request.contextPath}/resources/images/doctors/karki.jpg" alt="Dr. Parag Karki" class="doctor-img">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. Parag Karki</h3>
                    <div class="doctor-specialty">Cardiologist</div>
                    <div class="doctor-description">Electrophysiology specialist in pacemaker implantation and complex arrhythmias management.</div>
                    <div class="doctor-stats">
                        <div class="doctor-stat">
                            <div class="stat-value">15+</div>
                            <div class="stat-label">Years Exp</div>
                        </div>
                        <div class="doctor-stat">
                            <div class="stat-value">2000+</div>
                            <div class="stat-label">Patients</div>
                        </div>
                    </div>
                </div>
            </div>
            
            <%-- Doctor Card 9: Dr. Sofia Malla --%>
            <div class="doctor-card">
                <div class="doctor-img-container">
                    <img src="${pageContext.request.contextPath}/resources/images/doctors/sofia.jpg" alt="Dr. Sofia Malla" class="doctor-img">
                </div>
                <div class="doctor-info">
                    <h3 class="doctor-name">Dr. Sofia Malla</h3>
                    <div class="doctor-specialty">Orthodontist</div>
                    <div class="doctor-description">Specialist in traditional braces and clear aligners for functional, beautiful smiles.</div>
                    <div class="doctor-stats">
                        <div class="doctor-stat">
                            <div class="stat-value">4+</div>
                            <div class="stat-label">Years Exp</div>
                        </div>
                        <div class="doctor-stat">
                            <div class="stat-value">2800+</div>
                            <div class="stat-label">Patients</div>
                        </div>
                    </div>
                </div>
            </div>

        </div> <%-- End of doctor-grid --%>
    </div> <%-- End of main-content --%>
    
    <jsp:include page="footer.jsp" />
</body>
</html>
