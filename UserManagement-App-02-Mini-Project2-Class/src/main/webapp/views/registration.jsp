<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<link rel="stylesheet" type="text/css"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="js/application.js"></script>

</head>
<body>
	<div align="center">
		<h1>Registration Form</h1>
		<font color="greens">${successMsg}</font> <font color="red">${failureMsg}</font>
		<form:form method="post" action="userRegistration"
			modelAttribute="userAcc">
			<table>
				<tr>
					<td>First Name:</td>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><form:input path="lastName" /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><form:input path="userEmail" id="userEmail" /><span
						id="emailErrorMsg"></span></td>
				</tr>
				<tr>
					<td>PhNo:</td>
					<td><form:input path="userMobile" /></td>
				</tr>
				<tr>
					<td>DOB:</td>
					<td><form:input path="dob" id="datepicker" autocomplete="off" /></td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td><form:radiobutton path="gender" value="M" />Male <form:radiobutton
							path="gender" value="F" />Female</td>
				</tr>
				<tr>
					<td>Country:</td>
					<td><form:select path="countryId" id="countryId">
							<form:option value="0">select</form:option>
							<form:options items="${countries}" />
						</form:select></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><form:select path="stateId" id="stateId">
							<form:option value="0">select</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>City:</td>
					<td><form:select path="cityId" id="cityId">
							<form:option value="0">select</form:option>
						</form:select></td>
				</tr>
			</table>
			<input type="button" value="Reset">
			<input type="submit" value="Register" id="submitButton">
		</form:form>
	</div>
</body>
</html>