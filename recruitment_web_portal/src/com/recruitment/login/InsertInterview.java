package com.recruitment.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recruitment.model.Applicant;
import com.recruitment.model.Interview;
import com.recruitment.model.Position;
import com.recruitment.services.InterviewServiceImpl;

/**
 * Servlet implementation class InsertInterview
 */
@WebServlet("/InsertInterview")
public class InsertInterview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertInterview() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		System.out.println(request.getParameter("appID"));
		int appId = Integer.parseInt(request.getParameter("appID"));
		System.out.println(request.getParameter("positionID"));
		int postId = Integer.parseInt(request.getParameter("positionID"));
		String applicantName = request.getParameter("appName");
		String postName = request.getParameter("postName");
		String date = request.getParameter("dateOfInterview");
		String location = request.getParameter("location");
		String result = request.getParameter("result");
		String dateOfJoin = request.getParameter("dateOfJoin");
		String joinStatus = request.getParameter("joinStatus");
		boolean candidateResult = false;
		boolean joiningStatus = false;

		if (result.equalsIgnoreCase("Selected")) {

			candidateResult = true;
			if (joinStatus.equalsIgnoreCase("Joined")) {
				joiningStatus = true;

			} // inner if
			else {

				joiningStatus = false;
			} // inner else
		} // if
		else {
			candidateResult = false;
			joiningStatus = false;
			dateOfJoin = null;
		} // else

		Position position = new Position();
		position.setPostId(postId);
		Applicant applicant = new Applicant();
		applicant.setApplicantId(appId);
		Date dateOfInterview = Date.valueOf(date);
		Date dateOfJoining = Date.valueOf(dateOfJoin);
		Interview interview = new Interview(applicant, position, dateOfInterview, dateOfJoining, location,
				candidateResult, joiningStatus);
		InterviewServiceImpl interviewService = new InterviewServiceImpl();
		interviewService.addInterview(interview);// add new Interview details into interview table

		out.print("Applicant ID " + appId + " Interview Result Successfully Stored");

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("InterviewEntry.jsp");
		requestDispatcher.include(request, response);
	}

}
