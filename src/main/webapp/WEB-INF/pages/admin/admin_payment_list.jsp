<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Overview</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin_payment_list.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />

	<div class="content">
        <div class="header-container">
            <h2 class="list-header">Payments List</h2>
        </div>

        <div class="search-control">
            <input type="text" placeholder="Search payments...">
        </div>
        <table>
            <thead>
                <tr>
                    <th>Payment ID</th>
                    <th>Appointment ID</th>
                    <th>Patient Name</th>
                    <th>Amount</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>1</td>
                    <td>Aarati Sharma</td>
                    <td class="amount">₹ 2,500.00</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>2</td>
                    <td>Bijay Thapa</td>
                    <td class="amount">₹ 3,200.00</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>3</td>
                    <td>Deepak Poudel</td>
                    <td class="amount">₹ 4,500.00</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>5</td>
                    <td>Prakash Gurung</td>
                    <td class="amount">₹ 1,800.00</td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>6</td>
                    <td>Manisha Rai</td>
                    <td class="amount">₹ 3,700.00</td>
                </tr>
                <tr>
                    <td>6</td>
                    <td>7</td>
                    <td>Ram Bahadur KC</td>
                    <td class="amount">₹ 2,100.00</td>
                </tr>
                <tr>
                    <td colspan="3" style="text-align: right; font-weight: bold;">Total Amount:</td>
                    <td class="amount" style="font-weight: bold;">₹ 17,800.00</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>