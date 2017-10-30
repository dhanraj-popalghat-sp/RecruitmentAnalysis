package com.recruitment.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recruitment.dao.HumanResourceDAO;
import com.recruitment.model.HumanResource;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int userId = Integer.parseInt(request.getParameter("userId"));
		String password = request.getParameter("pass");
		HumanResource humanResource = new HumanResourceDAO().retrieve(userId);

		if (humanResource.getHrId() == userId && humanResource.getPassword().equals(password)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userName", humanResource.getName());
			session.setAttribute("hrID", humanResource.getHrId());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("dashboard.jsp");
			requestDispatcher.forward(request, response);
		} else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			out.println("<font color='red'>invalid userId or Password</font>");
			requestDispatcher.include(request, response);
		}

	}

}
