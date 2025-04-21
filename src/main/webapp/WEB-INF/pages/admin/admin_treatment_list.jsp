<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Treatment Overview</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin_treatment_list.css" />
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp" />

<div class="content">
  <div class="header-container">
    <h2 class="list-header">Treatments List</h2>
    <a href="${pageContext.request.contextPath}/add-treatment" class="add-treatment-btn">Add Treatment</a>
  </div>

  <div class="search-control">
    <input type="text" placeholder="Search treatments...">
  </div>
  <table>
    <thead>
      <tr>
        <th>Treatment ID</th>
        <th>Treatment Name</th>
        <th>Doctor</th>
        <th>Treatment Plan</th>
        <th>Remark</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>1001</td>
        <td class="treatment-name">Physical Therapy</td>
        <td class="doctor-name">Dr. Sarah Johnson</td>
        <td>3 sessions weekly for 4 weeks</td>
        <td>Patient showing good progress</td>
      </tr>
      <tr>
        <td>1002</td>
        <td class="treatment-name">Chemotherapy</td>
        <td class="doctor-name">Dr. Michael Chen</td>
        <td>Every 3 weeks for 6 months</td>
        <td>Monitor for side effects</td>
      </tr>
      <tr>
        <td>1003</td>
        <td class="treatment-name">Cognitive Behavioral Therapy</td>
        <td class="doctor-name">Dr. Emily Rodriguez</td>
        <td>Weekly sessions for 12 weeks</td>
        <td>Patient requires follow-up assessment</td>
      </tr>
      <tr>
        <td>1004</td>
        <td class="treatment-name">Insulin Management</td>
        <td class="doctor-name">Dr. James Wilson</td>
        <td>Daily insulin injections with monitoring</td>
        <td>Blood sugar levels stabilizing</td>
      </tr>
      <tr>
        <td>1005</td>
        <td class="treatment-name">Post-Surgery Rehabilitation</td>
        <td class="doctor-name">Dr. Lisa Thompson</td>
        <td>Daily exercises for 6 weeks</td>
        <td>Excellent recovery rate</td>
      </tr>
      <tr>
        <td>1006</td>
        <td class="treatment-name">Antibiotic Treatment</td>
        <td class="doctor-name">Dr. Robert Garcia</td>
        <td>Seven days of oral antibiotics</td>
        <td>Check for allergic reactions</td>
      </tr>
    </tbody>
  </table>
</div>
</body>
</html>