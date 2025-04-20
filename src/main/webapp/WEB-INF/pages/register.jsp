<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Registration</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/register.css" />
</head>
<body>
	<div class="container">
		<div class="card">
			<div class="left-section">

				<div class="form-header">
					<div class="form-small-text">PATIENT REGISTRATION</div>
					<h1 class="form-title">Create patient account<span>.</span></h1>
					<div class="tagline">Register Now, Heal Tomorrow</div>
					<div class="login-text">Already Registered? <a href="${pageContext.request.contextPath}/login">Log In</a></div>
				</div>

				<!-- Display error message if available -->
				<c:if test="${not empty error}">
					<p class="error-message">${error}</p>
				</c:if>
				<!-- Display success message if available -->
				<c:if test="${not empty success}">
					<p class="success-message">${success}</p>
				</c:if>
 
				<form action="${pageContext.request.contextPath}/register" method="post"
					enctype="multipart/form-data">

					<div class="form-section-title">Personal Information</div>

					<div class="form-row">
						<div class="form-group">
							<label for="firstName">First Name</label>
							<input type="text" id="firstName" name="firstName" value="${firstName}" 
								placeholder="Enter first name" required>
						</div>

						<div class="form-group">
							<label for="lastName">Last Name</label>
							<input type="text" id="lastName" name="lastName" value="${lastName}" 
								placeholder="Enter last name" required>
						</div>
					</div>

					<div class="form-row">
						<div class="form-group">
							<label for="birthday">Date of Birth</label>
							<input type="date" id="birthday" name="dateOfBirth" value="${dateOfBirth}" required>
						</div>

						<div class="form-group">
							<label for="gender">Gender</label>
							<select id="gender" name="gender" required>
								<option value="" disabled ${gender == null ? 'selected' : ''}>Select gender</option>
								<option value="male" ${gender == 'male' ? 'selected' : ''}>Male</option>
								<option value="female" ${gender == 'female' ? 'selected' : ''}>Female</option>
								<option value="other" ${gender == 'other' ? 'selected' : ''}>Other</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="phoneNumber">Contact Number</label>
						<input type="tel" id="phoneNumber" name="phoneNumber" value="${phoneNumber}"
							placeholder="Enter contact number" required>
					</div>

					<div class="form-section-title">Address Information</div>

					<div class="form-row">
						<div class="form-group">
							<label for="city">City</label>
							<input type="text" id="city" name="city" value="${city}"
								placeholder="Enter city" required>
						</div>

						<div class="form-group">
							<label for="district">District</label>
							<input type="text" id="district" name="district" value="${district}"
								placeholder="Enter district" required>
						</div>
					</div>

					<div class="form-row">
						<div class="form-group">
							<label for="municipality">Municipality</label>
							<input type="text" id="municipality" name="municipality" value="${municipality}"
								placeholder="Enter municipality" required>
						</div>

						<div class="form-group">
							<label for="ward">Ward</label>
							<input type="text" id="ward" name="wardNo" value="${ward}"
								placeholder="Enter ward number" required>
						</div>
					</div>

					<div class="form-section-title">Account Information</div>

					<div class="form-group">
						<label for="email">Email</label>
						<input type="email" id="email" name="email" value="${email}"
							placeholder="Enter email address" required>
					</div>

					<div class="form-group">
						<label for="username">Username</label>
						<input type="text" id="username" name="username" value="${username}"
							placeholder="Create username" required>
					</div>

					<div class="form-group">
						<label for="password">Password</label>
						<input type="password" id="password" name="password"
							placeholder="Create a password" required>
					</div>

					<div class="form-group">
						<label for="retypePassword">Confirm Password</label>
						<input type="password" id="retypePassword" name="retypePassword"
							placeholder="Confirm your password" required>
					</div>

					<div class="form-group">
						<label for="image">Profile Picture</label>
						<input type="file" id="image" name="image">
					</div>

					<div class="form-actions">
						<button type="submit" class="btn btn-primary">Register Patient</button>
					</div>
				</form>
			</div>

			<div class="right-section">
				<div class="image-container">
					<img src="${pageContext.request.contextPath}/resources/images/register.jpg" alt="Medical stethoscope" class="stethoscope-image">
				</div>
				<div class="overlay"></div>
				<div class="tagline-container">Register Now, Heal Tomorrow</div>
			</div>
		</div>
	</div>
</body>
</html>