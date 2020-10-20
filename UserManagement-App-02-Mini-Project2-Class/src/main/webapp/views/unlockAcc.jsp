<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>




</head>
<body>
	<div align="center">
		<h1>Unlock Account</h1>
		<font color="green">${successMsg}</font> <font color="red">${failureMsg}</font>
		<form:form method="post" action="unlockAccount?email=${unlockAcc.email}"
			modelAttribute="unlockAcc">
			<table>
				<tr>
					<td>Email:</td>
					<td><p>${unlockAcc.email}</p></td>
				</tr>
				<tr>
					<td>Temporary Password:</td>
					<td><form:input path="tempPwd" /></td>
				</tr>
				<tr>
					<td>New Password:</td>
					<td><form:input path="newPwd"/></td>
				</tr>
				<tr>
					<td>Confirm Password:</td>
					<td><form:input path="confirmPwd" /></td>
				</tr>
			</table>
			<input type="button" value="Reset">
			<input type="submit" value="Unlock Account">
		</form:form>
	</div>
</body>
</html>