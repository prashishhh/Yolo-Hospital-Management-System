<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login.css" />
</head>
<body>
	<body>
  <div class="card">
    <div class="left-section">
      <div class="image-container">
        <img src="${pageContext.request.contextPath}/resources/images/register.jpg" alt="Medical stethoscope" class="stethoscope-image">
      </div>
      <div class="overlay"></div>
      <div class="tagline-container">Register Now, Heal Tomorrow</div>
    </div>
    
    <div class="right-section">
      
      <div class="form-header">
        <div class="form-small-text">PATIENT LOGIN</div>
        <h1 class="form-title">Welcome back<span>.</span></h1>
        <div class="tagline">Access Your Health Journey</div>
        <div class="register-text">Don't have an account? <a href="${pageContext.request.contextPath}/register">Register Now</a></div>
      </div>
      
      <form method="post" action="${pageContext.request.contextPath}/login">
        <div class="form-group">
  			<label>Username</label>
  			<input type="text" name="username" placeholder="Enter your username" required>
		</div>

		<div class="form-group">
 			<label>Password</label>
  			<input type="password" name="password" placeholder="Enter your password" required>
		</div>

        
        <div class="forgot-password">
          <a href="#">Forgot password?</a>
        </div>
        
        <div class="form-actions">
          <button type="submit" class="btn btn-primary">Login</button>
        </div>
        
         <!-- Display error message if available -->
		<c:if test="${not empty error}">
			<p class="error-message">${error}</p>
		</c:if>

		<!-- Display success message if available -->
		<c:if test="${not empty success}">
			<p class="success-message">${success}</p>
		</c:if>
      </form>
    </div>
  </div>
</body>
</html>