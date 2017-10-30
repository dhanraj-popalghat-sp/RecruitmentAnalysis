package com.recruitment.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recruitment.dao.HumanResourceDAO;
import com.recruitment.model.HumanResource;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registration() {
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

		String hrName = request.getParameter("hrName");
		String department = request.getParameter("dept");
		String contact = request.getParameter("contact");
		String password = request.getParameter("pass");

		HumanResource humanResource = new HumanResource(hrName, department, contact, password);
		HumanResourceDAO humanResourceDAO = new HumanResourceDAO();
		humanResourceDAO.add(humanResource);
		response.sendRedirect("index.jsp");
	}

}
