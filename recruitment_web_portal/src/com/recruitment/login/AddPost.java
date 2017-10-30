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

import com.recruitment.model.HumanResource;
import com.recruitment.model.Position;
import com.recruitment.services.HRServiceImpl;
import com.recruitment.services.PositionServiceImpl;

/**
 * Servlet implementation class AddPost
 */
@WebServlet("/AddPost")
public class AddPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPost() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PositionServiceImpl positionServiceImpl = new PositionServiceImpl();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String postName = request.getParameter("post_name");
		int noOfPosts = Integer.parseInt(request.getParameter("noOfPosts"));
		double experience = Double.parseDouble(request.getParameter("experience"));
		int hrID = Integer.parseInt(request.getParameter("hrID"));

		java.util.Date currentDate = new java.util.Date();
		Date dateOfPost = new Date(currentDate.getTime());
		boolean status = true;
		HumanResource humanResource = new HumanResource();
		humanResource.setHrId(hrID);
		Position position = new Position(postName, noOfPosts, experience, dateOfPost, status);
		position.setHr(humanResource);
		positionServiceImpl.add(position);
		response.sendRedirect("addPosition.jsp");
	}

}
