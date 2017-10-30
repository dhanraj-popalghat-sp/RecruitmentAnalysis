package com.recruitment.login;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recruitment.dao.ApplicantDAO;
import com.recruitment.model.Applicant;
import com.recruitment.model.Apply;
import com.recruitment.model.Position;
import com.recruitment.services.ApplyServiceImpl;

/**
 * Servlet implementation class ApplicantApply
 */
@WebServlet("/ApplicantApply")
public class ApplicantApply extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplicantApply() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicantDAO applicantDAO = new ApplicantDAO();

		response.setContentType("text/html");
		String applicantName = request.getParameter("applicantName");
		double experience = Double.parseDouble(request.getParameter("experience"));
		String qualification = request.getParameter("highQual");
		String email = request.getParameter("email");
		String contact = request.getParameter("contact");
		int postId = Integer.parseInt(request.getParameter("postId"));
		java.util.Date currentDate = new java.util.Date();
		Date dateOfApply = new Date(currentDate.getTime());
		Applicant applicant = new Applicant(applicantName, experience, qualification, email, contact);
		Applicant dbApplicant = applicantDAO.add(applicant);

		ApplyServiceImpl applyServiceImpl = new ApplyServiceImpl();
		Position position = new Position();
		position.setPostId(postId);
		Apply apply = new Apply();
		apply.setApplicant(dbApplicant);
		apply.setPost(position);
		apply.setDateOfApply(dateOfApply);
		System.out.println("Apply Model : " + apply);
		applyServiceImpl.addApply(apply);
		response.sendRedirect("careers.jsp");
	}

}
