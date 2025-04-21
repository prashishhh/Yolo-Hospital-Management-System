<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Treatment</title>
<link rel="stylesheet" type="text/css"
href="${pageContext.request.contextPath}/css/add_treatment.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />

<div class="main-content">
    <div class="card">
        <div class="form-container">
            <div class="form-header">
                <h1 class="form-title">Add Treatment</h1>
            </div>

            <form>
                <div class="form-section-title">Treatment Information</div>

                <div class="form-group">
                    <label>Treatment Name</label>
                    <input type="text" name="treatmentName" placeholder="Enter treatment name" required>
                </div>

                <div class="form-group">
                    <label>Treatment Plan</label>
                    <textarea name="treatmentPlan" placeholder="Enter treatment plan details" required></textarea>
                </div>

                <div class="form-group">
                    <label>Remarks</label>
                    <textarea name="remarks" placeholder="Enter any additional remarks"></textarea>
                </div>

                <div class="form-actions">
                    <button type="button" class="form-btn btn-secondary">Cancel</button>
                    <button type="submit" class="form-btn btn-primary">Add Treatment</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
