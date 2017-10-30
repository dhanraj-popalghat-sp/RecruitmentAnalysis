<%@page import="com.recruitment.dao.PositionDAO"%>
<%@page import="com.recruitment.model.Applicant"%>
<%@page import="com.recruitment.model.Position"%>
<%@page import="com.recruitment.dao.ApplicantDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.recruitment.dao.ApplyDAO"%>
<%@page import="com.recruitment.model.Apply"%>
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
	<TABLE border="2" align="center">
		<TR>
			<TH>Applicant_id</TH>
			<TH>Applicant_name</TH>
			<TH>Position_id</TH>
			<TH>Position_name</TH>
		</TR>
		<%
			ApplyDAO applyDAO = new ApplyDAO();
			Position position = new Position();
			Applicant applicant = new Applicant();
			ApplicantDAO applicantDAO = new ApplicantDAO();
			PositionDAO positionDAO = new PositionDAO();
			List<Apply> listOfApply = applyDAO.retrieveAll();

			for (Apply apply : listOfApply) {
				applicant = applicantDAO.retrieve(apply.getApplicant().getApplicantId());
				position = positionDAO.retrieve(apply.getPost().getPostId());
				apply.setApplicant(applicant);
				apply.setPost(position);
		%>
		<TR>
			<TD><input type="text" name="appID"
				value="<%=applicant.getApplicantId()%>" readonly></TD>

			<TD><%=applicant.getName()%></TD>

			<TD><input type="text" name="positionID"
				value="<%=position.getPostId()%>" readonly></TD>

			<TD><%=position.getPostName()%></TD>
			<TD><a
				href="InterviewEntry.jsp?postId=<%=position.getPostId()%>&appId=<%=applicant.getApplicantId()%>&postName=<%=position.getPostName()%>&appName=<%=applicant.getName()%>">Interview
					Entry</a></TD>
		</TR>
		<%
			}
		%>
	</TABLE>
</body>
</html>