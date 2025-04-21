<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>

  <!-- 
    Loads custom fonts and icons 
    - Inter: Modern, readable font
    - FontAwesome: Provides icons
  -->
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

  <!-- 
    Loads external CSS for blog styling
    Path is relative to application context
  -->
  <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/blog.css"/>
</head>
<body>

	<jsp:include page="header.jsp" />

  <!-- 
    ===============================
    Hero Section
    Displays title and tagline with background image
    ===============================
  -->
  <section class="hero">
    <div class="hero-content">
      <h2>YOLO Hospital Health Blog</h2>
      <p>Trusted medical insights and wellness advice from our healthcare professionals</p>
    </div>

    <!-- 
      Image used as hero background (not as CSS background)
      Ensures visibility of <img> in DOM for accessibility and SEO
    -->
    <img src="${pageContext.request.contextPath}/resources/images/register.jpg" 
         alt="Medical Team" 
         class="hero-image">
  </section>

  <!-- 
    ===============================
    Blog Section
    Displays list of blog articles
    ===============================
  -->
  <div class="blog-container">
    <div class="blog-header">
      <h2>Latest Articles</h2>

      <!-- 
        JSP INTEGRATION POINT:
        'Add New Blog Post' button should be conditionally shown
        for admin users only 
      -->
      <!-- <button class="post-button">Add New Blog Post</button> -->
    </div>

    <!-- 
      JSP INTEGRATION POINT:
      Blog posts below are static placeholders.
      Replace with dynamic post iteration using JSTL or scriptlet
    -->

    <!-- 
      Sample Blog Post 1
      Topic: Stress management during recovery
    -->
    <div class="blog-post">
      <h3>How to Manage Stress During Recovery</h3>
      <p>
        Stress can significantly affect your healing process. 
        Our specialists share expert tips to manage mental and emotional well-being 
        while recovering from illness or surgery. 
        Learn practical techniques to reduce anxiety and promote faster healing.
      </p>
      <div class="blog-meta">April 6, 2025</div>
    </div>

    <!-- 
      Sample Blog Post 2
      Topic: Importance of regular health checkups
    -->
    <div class="blog-post">
      <h3>The Importance of Regular Health Checkups</h3>
      <p>
        Preventive healthcare is key to long-term wellness. 
        Discover why regular checkups are crucial, 
        what tests you should consider at different life stages, 
        and how early detection can make all the difference in treatment outcomes.
      </p>
      <div class="blog-meta">March 28, 2025</div>
    </div>

    <!-- 
      Sample Blog Post 3
      Topic: Nutrition and immunity
    -->
    <div class="blog-post">
      <h3>Nutrition for Immune System Support</h3>
      <p>
        Explore the connection between diet and immunity. 
        This article breaks down essential nutrients, 
        superfoods, and meal planning strategies to help 
        strengthen your body's natural defenses against illness.
      </p>
      <div class="blog-meta">March 15, 2025</div>
    </div>
    
    <!-- End of sample posts -->
  </div>
  
  <jsp:include page="footer.jsp" />
</body>
</html>
