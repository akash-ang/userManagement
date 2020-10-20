<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h1>Sign In Here</h1>
		<span><font color="red">${failureMsg}</font></span>
		<form action="signIn" method="post">
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="userEmail"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="text" name="userPassword"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Sign-In"></td>
				</tr>
				<tr>
					<td><a href="/forgotPwd">Forgot Pwd?</a></td>
					<td><a href="/register">Sign-Up</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>