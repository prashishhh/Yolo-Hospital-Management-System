<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patient Overview</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin_patient_list.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />

	<div class="content">
        <div class="header-container">
            <h2 class="list-header">Patients List</h2>
        </div>
        
        <div class="search-control">
            <input type="text" placeholder="Search patients...">
        </div>
        <table>
            <thead>
                <tr>
                    <th>Patient ID</th>
                    <th>Patient Name</th>
                    <th>Age</th>
                    <th>Phone</th>
                    <th>Last Visit</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>Aarav Sharma</td>
                    <td>34</td>
                    <td>555-123-4567</td>
                    <td><span class="badge">April 10, 2025</span></td>
                    <td class="actions">
                        <button class="btn-delete">Delete</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Sarita Adhikari</td>
                    <td>45</td>
                    <td>555-234-5678</td>
                    <td><span class="badge">April 8, 2025</span></td>
                    <td class="actions">
                        <button class="btn-delete">Delete</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Bijay Thapa</td>
                    <td>28</td>
                    <td>555-345-6789</td>
                    <td><span class="badge">March 25, 2025</span></td>
                    <td class="actions">
                        <button class="btn-delete">Delete</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Priya Shrestha</td>
                    <td>62</td>
                    <td>555-456-7890</td>
                    <td><span class="badge">April 12, 2025</span></td>
                    <td class="actions">
                        <button class="btn-delete">Delete</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Dipesh Gurung</td>
                    <td>19</td>
                    <td>555-567-8901</td>
                    <td><span class="badge">March 18, 2025</span></td>
                    <td class="actions">
                        <button class="btn-delete">Delete</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Sunita Tamang</td>
                    <td>53</td>
                    <td>555-678-9012</td>
                    <td><span class="badge">February 5, 2025</span></td>
                    <td class="actions">
                        <button class="btn-delete">Delete</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Rajesh Poudel</td>
                    <td>41</td>
                    <td>555-789-0123</td>
                    <td><span class="badge">January 22, 2025</span></td>
                    <td class="actions">
                        <button class="btn-delete">Delete</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>