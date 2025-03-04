package com.bank.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.utility.DatabaseConnection;
import com.bank.utility.UserDAO;
import com.bank.utility.Util;

public class BankCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAO dao;

	@Override
	public void init() throws ServletException {

		dao = new UserDAO(getServletContext());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		User user = new User();
		String flag = request.getParameter("flag");
		user.setName(request.getParameter("name"));
		user.setGender(request.getParameter("gender"));
		user.setEmail(request.getParameter("email"));
		user.setDob(request.getParameter("dob"));
		user.setPhoneNumber(request.getParameter("phonenumber"));
		user.setOccupation(request.getParameter("occupation"));
		user.setAccountType(request.getParameter("accounttype"));
		user.setDebitCard(request.getParameter("debitCard"));
		user.setAddress(request.getParameter("address"));
		user.setCity(request.getParameter("city"));
		user.setState(request.getParameter("state"));
		user.setZip(request.getParameter("zip"));
		user.setNationality(request.getParameter("nationality"));
		user.setAadhaar(request.getParameter("aadhaar"));
		user.setPan(request.getParameter("pan"));
		user.setIncome(request.getParameter("income"));
		user.setMaritalStatus(request.getParameter("marital"));
		user.setNominee(request.getParameter("nominee"));
		user.setRelationship(request.getParameter("relationship"));

		Map<String, String> errors = new HashMap<>();
		Util.validateRegisterForm(user, errors);

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			if (flag.equals("add")) {
				request.getRequestDispatcher("insert.jsp").forward(request, response);
				return;
			} else {
				request.setAttribute("user", user);
				request.getRequestDispatcher("form.jsp").forward(request, response);
				return;
			}
		}

		if ("add".equals(flag)) {

			int rowsInserted = dao.Add(user);
			if (rowsInserted > 0) {
				doGet(request, response);
			} else {
				errors.put("registration", "Registration Failed! Please try again.");

			}

		} else if ("update".equals(flag)) {

			user.setId(Integer.parseInt(request.getParameter("id")));

			int rowsUpdated = dao.Update(user);
			if (rowsUpdated > 0) {
				doGet(request, response);
			} else {
				errors.put("update", "Update failed! ");

			}

		} else {

			errors.put("operation", "Invalid operation!");
		}
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
		
			doGet(request, response);
			
		}
		

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> errors = new HashMap<>();
		String mode = request.getParameter("mode");

		if (mode == null) {
			mode = "showall";
		}

		if (mode.equals("user")) {
			int userId = Integer.parseInt(request.getParameter("id"));

			if (userId == 0) {
				errors.put("Error", "User Id not Found");
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("index.jsp").forward(request, response);

			}

			User user = dao.displayUser(userId);
			if (user != null) {
				request.setAttribute("user", user);
				request.getRequestDispatcher("form.jsp").forward(request, response);
			} else {
				errors.put("Error", "Try Again");
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}

		}

		else if (mode.equals("showall"))

		{

			List<User> userList = dao.displayAll();

			request.setAttribute("userList", userList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);

		} else if (mode.equals("delete")) {
			int userId = Integer.parseInt(request.getParameter("id"));
			if (userId == 0) {
				errors.put("deleteError", "User Id not Found");
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			int rowsDeleted = dao.delete(userId);
			if (rowsDeleted > 0) {

				request.getRequestDispatcher("register?mode=showall").forward(request, response);
			} else {
				errors.put("deleteError", "Error Occured Try Again");
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}

		} else {
			errors.put("invalidOperation", "Invalid operation!");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
}