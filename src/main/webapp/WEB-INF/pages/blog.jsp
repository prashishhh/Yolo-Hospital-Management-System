<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>YOLO Hospital Health Blog</title>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/blog.css"/>
</head>
<body>
    <jsp:include page="header.jsp" />

    <!-- Hero Section -->
    <section class="hero">
        <div class="hero-content">
            <h2>YOLO Hospital Health Blog</h2>
            <p>Trusted medical insights and wellness advice from our healthcare professionals</p>
        </div>
        <img src="${pageContext.request.contextPath}/resources/images/register.jpg" 
             alt="Medical Team" 
             class="hero-image">
    </section>

    <!-- Blog Section -->
    <div class="blog-container">
        <div class="blog-header">
            <h2>Latest Articles</h2>
            <!-- Search form -->
            <div class="search-control">
                <form action="${pageContext.request.contextPath}/blog" method="get">
                    <input type="text" name="search" placeholder="Search articles..." value="${param.search}">
                    <button type="submit"><img class="search-icon" src="${pageContext.request.contextPath}/resources/images/searchbar.png" alt="search bar"></button>
                </form>
            </div>
            <!-- Conditionally show Add New Blog Post button for admins -->
            <c:if test="${sessionScope.userRole == 'Admin'}">
                <button class="post-button" onclick="window.location.href='${pageContext.request.contextPath}/add-blog'">Add New Blog Post</button>
            </c:if>
        </div>

        <!-- Dynamic blog post iteration -->
        <c:forEach var="post" items="${blogPosts}">
            <div class="blog-post">
                <h3>${post.blogTitle}</h3>
                <p>${post.blogDescription}</p>
                <div class="blog-meta">${post.blogPublishedDate}</div>
            </div>
        </c:forEach>

        <!-- Display message if no posts are found -->
        <c:if test="${empty blogPosts}">
            <p>No blog posts found.</p>
        </c:if>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>