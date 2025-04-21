<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patient Treatment List</title>
    <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/patient_treatment.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/pages/header.jsp" />
    
    <div class="container">
        <div class="main-content">
            <div class="header-container">
                <h2 class="list-header">My Treatments</h2>
            </div>
            
            <table class="treatment-table">
                <thead>
                    <tr>
                        <th>Treatment Name</th>
                        <th>Doctor</th>
                        <th>Treatment Plan</th>
                        <th>Remark</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Hypertension Management</td>
                        <td>Dr. Girish Raj Thapa</td>
                        <td>Monitor blood pressure weekly, maintain a low-sodium diet, and review in 1 month for medication adjustment.</td>
                        <td>Patient advised on lifestyle changes and medication adherence. Follow-up scheduled in 4 weeks.</td>
                    </tr>
                    <tr>
                        <td>Post-Appendectomy Care</td>
                        <td>Dr. Prashish Sapkota</td>
                        <td>Monitor wound, take meds as prescribed, avoid heavy lifting, follow up in 1 week.</td>
                        <td>Patient stable. Wound clean. Advised on care and follow-up.</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>