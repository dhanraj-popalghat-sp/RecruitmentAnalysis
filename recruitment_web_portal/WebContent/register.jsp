<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<form method="post" action="register">
		<table>
			<tr>
				<th>Name</th>
				<td><input type="text" name="hrName"></td>
			</tr>
			<tr>
				<th>Department</th>
				<td><input type="text" name="dept"></td>
			</tr>
			<tr>
				<th>Contact</th>
				<td><input type="text" name="contact"></td>
			</tr>
			<tr>
				<th>Password</th>
				<td><input type="text" name="pass"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Sign Up"></td>
				<td><input type="reset" name="Reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>