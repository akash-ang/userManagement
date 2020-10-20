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
		<h1>Forgot Password</h1>
		<font color="green">${successMsg}</font> <font color="red">${failureMsg}</font>
		<form method="post" action="forgotPwd">
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email"></td>
				</tr>
			</table>
			<input type="button" value="Reset"> <input type="submit"
				value="Submit">
		</form>
	</div>
</body>
</html>