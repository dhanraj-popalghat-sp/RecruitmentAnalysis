<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Apply Position</title>
</head>
<body>
	<h2>Apply position</h2>
	<form action="ApplicantApply" method="post">
		<table>
			<tr>
				<th>Applicant Name</th>
				<td><input type="text" name="applicantName"></td>
			</tr>
			<tr>
				<th>Experience</th>
				<td><input type="text" name="experience"></td>
			</tr>
			<tr>
				<th>Highest Qualification</th>
				<td><input type="text" name="highQual"></td>
			</tr>
			<tr>
				<th>Email</th>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<th>Contact</th>
				<td><input type="text" name="contact"></td>
			</tr>
			<tr><td colspan="2"><input type="hidden" value="<%=request.getParameter("postId")%>" name="postId"></td></tr>
			<tr>
				<td><input type="submit" value="Apply"></td>
				<td><input type="reset" value="Reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>