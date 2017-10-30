<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Position</title>
</head>
<body>
	<h1>Add position</h1>
	<form action="AddPost" method="post">
		<table>
			<tr>
				<th>Post Name:</th>
				<td><input type="text" name="post_name"></td>
			</tr>
			<tr>
				<th>No. of Posts</th>
				<th><input type="text" name="noOfPosts"></th>
			</tr>

			<tr>
				<th>Experience</th>
				<th><input type="text" name="experience"></th>
			</tr>
			<tr>
				<td colspan="2"><input type="hidden"
					value="<%=session.getAttribute("hrID")%>" name="hrID"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"></td>
				<td><input type="reset" value="reset"></td>
			</tr>
		</table>
	</form>

</body>
</html>