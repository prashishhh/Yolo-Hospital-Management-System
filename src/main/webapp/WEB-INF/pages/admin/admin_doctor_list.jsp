<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Doctor List</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin_doctor_list.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />
	
	<main>
		<div class="content">
            <div class="header-container">
                <h2 class="list-header">Doctors List</h2>
                <a href="${pageContext.request.contextPath}/add-doctor" class="add-doctor-btn">+ Add Doctor</a>
            </div>
            
            <div class="search-control">
                <input type="text" placeholder="Search doctors...">
            </div>
            <table>
                <thead>
                    <tr>
                        <th>Doctor ID</th>
                        <th>Doctor Name </th>
                        <th>Experience (Years)</th>
                        <th>Phone</th>
                        <th>Specialization</th>
                        <th>Appointments</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Manoj Kumar</td>
                        <td>10</td>
                        <td>333-444-7777</td>
                        <td>Dental</td>
                        <td>24</td>
                        <td class="actions">
                            <button class="btn-edit">Edit</button>
                            <button class="btn-delete">Delete</button>
                        </td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Riya</td>
                        <td>6</td>
                        <td>3423-232-987</td>
                        <td>Ortho</td>
                        <td>18</td>
                        <td class="actions">
                            <button class="btn-edit">Edit</button>
                            <button class="btn-delete">Delete</button>
                        </td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Paul</td>
                        <td>15</td>
                        <td>3423-132-987</td>
                        <td>General Physician</td>
                        <td>32</td>
                        <td class="actions">
                            <button class="btn-edit">Edit</button>
                            <button class="btn-delete">Delete</button>
                        </td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>Manoj Kumar</td>
                        <td>20</td>
                        <td>333-444-7777</td>
                        <td>ENT</td>
                        <td>15</td>
                        <td class="actions">
                            <button class="btn-edit">Edit</button>
                            <button class="btn-delete">Delete</button>
                        </td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td>Riya</td>
                        <td>16</td>
                        <td>3423-232-987</td>
                        <td>General Physician</td>
                        <td>27</td>
                        <td class="actions">
                            <button class="btn-edit">Edit</button>
                            <button class="btn-delete">Delete</button>
                        </td>
                    </tr>
                    <tr>
                        <td>6</td>
                        <td>Paul</td>
                        <td>12</td>
                        <td>3423-132-987</td>
                        <td>Ortho</td>
                        <td>19</td>
                        <td class="actions">
                            <button class="btn-edit">Edit</button>
                            <button class="btn-delete">Delete</button>
                        </td>
                    </tr>
                    <tr>
                        <td>7</td>
                        <td>Manoj Kumar</td>
                        <td>19</td>
                        <td>333-444-7777</td>
                        <td>Neuro Surgeon</td>
                        <td>8</td>
                        <td class="actions">
                            <button class="btn-edit">Edit</button>
                            <button class="btn-delete">Delete</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
	</main>
</body>
</html>