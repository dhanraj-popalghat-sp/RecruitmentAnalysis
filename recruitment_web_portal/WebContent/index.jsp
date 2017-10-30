<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<a href="careers.jsp">Careers</a>
	<a href="#">HR login</a>
	<form action=LoginCheck method="post">
		<fieldset>
			<legend>HR Login</legend>
			<table>
				<tr>
					<th>HR ID :</th>
					<td><input type="text" name="userId"></td>
				</tr>
				<tr>
					<th>Password :</th>
					<td><input type="password" name="pass"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Login"></td>
					<td><a href="register.jsp">Sign Up</a>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>