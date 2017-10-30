<%@page import="com.recruitment.model.Position"%>
<%@page import="com.recruitment.services.PositionServiceImpl"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Careers</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>Post ID</th>
			<th>Post Name</th>
			<th>No. of Posts</th>
			<th>Experience</th>
			<th>Date of Post</th>
			<th>Status</th>
			<th>Apply</th>
		</tr>
		<%!PositionServiceImpl positionServiceImpl = new PositionServiceImpl();%>
		<%
			List<Position> positions = positionServiceImpl.retrieveAll();
			for (Position position : positions) {
		%>
		<tr>
			<td><%=position.getPostId()%></td>
			<td><%=position.getPostName()%></td>
			<td><%=position.getNoOfPosts()%></td>
			<td><%=position.getExperience()%></td>
			<td><%=position.getDateOfPost()%></td>
			<td><%=position.isStatus()%></td>
			<td><a href="applyPosition.jsp?postId=<%=position.getPostId()%>">Apply</a></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>