<%@page import="com.recruitment.model.Apply"%>
<%@page import="java.util.List"%>
<%@page import="com.recruitment.services.ApplyServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		int postId = Integer.parseInt(request.getParameter("postId"));
		int appId = Integer.parseInt(request.getParameter("appId"));
		String postName = request.getParameter("postName");
		String applicantName = request.getParameter("appName");
	%>
	<form action="InsertInterview" method="post">
		<table>
			<tr>
				<th>ApplicationID</th>
				<td><input type="text" name="appID" value="<%=appId%>" readonly></td>
			</tr>
			<tr>
				<th>Applicantion Name</th>
				<td><input type="text" value="<%=applicantName%>"
					name="appName" readonly></td>
			</tr>
			<tr>
				<th>PositionID</th>
				<td><input type="text" name="positionID" value="<%=postId%>"
					readonly></td>
			</tr>
			<tr>
				<th>PositionName</th>
				<td><input type="text" name="postName" value="<%=postName%>"
					readonly></td>
			</tr>
			<tr>
				<th>Date Of Interview</th>
				<td><input type="date" name="dateOfInterview"></td>
			</tr>
			<tr>
				<th>Location</th>
				<td><input type="text" name="location"></td>
			</tr>
			<tr>
				<th>Candidate Result</th>
				<td><select name="result">
						<option>Select</option>
						<option value="Selected">Selected</option>
						<option value="Not Selected">Not Selected</option>
				</select></td>
			</tr>
			<tr>
				<th>DateOfJoining</th>
				<td><input type="date" name="dateOfJoin"></td>
			</tr>
			<tr>
				<th>joined Status</th>
				<td><select name="joinStatus">
						<option>Select</option>
						<option value="Joined">Joined</option>
						<option value="Not Joined">Not Joined</option>
				</select></td>
			</tr>
			<tr>
				<td><input type="submit" value="result Stored"></td>
				<td><input type="reset" value="Reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>