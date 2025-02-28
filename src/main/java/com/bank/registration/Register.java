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

import com.bank.utility.Util;

@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = config.getServletContext();
		String url = context.getInitParameter("DB_URL");
		String password = context.getInitParameter("DB_PASSWORD");
		String user = context.getInitParameter("DB_USER");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Database Connection Established!");
		} catch (ClassNotFoundException | SQLException e) {
			throw new ServletException("DB Connection Initialization Failed!", e);
		}

	}

	@Override
	public void destroy() {
		try {
			if (conn != null) {
				conn.close();
				System.out.println("Database Connection Closed!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String flag = request.getParameter("flag");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");
		String phoneNumber = request.getParameter("phonenumber");
		String occupation = request.getParameter("occupation");
		String accountType = request.getParameter("accounttype");
		String debitCard = request.getParameter("debitCard");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String nationality = request.getParameter("nationality");
		String aadhaar = request.getParameter("aadhaar");
		String pan = request.getParameter("pan");
		String income = request.getParameter("income");
		String maritalStatus = request.getParameter("marital");
		String nominee = request.getParameter("nominee");
		String relationship = request.getParameter("relationship");

		Map<String, String> errors = new HashMap<>();

		if (!Util.isValidName(name))
			errors.put("name", "Invalid name");
		if (!Util.isValidGender(gender))
			errors.put("gender", "Invalid gender");
		if (!Util.isValidEmail(email))
			errors.put("email", "Invalid email format");
		if (!Util.isValidDOB(dob))
			errors.put("dob", "Invalid date of birth format");
		if (!Util.isValidPhoneNumber(phoneNumber))
			errors.put("phoneNumber", "Invalid phone number");
		if (!Util.isValidOccupation(occupation))
			errors.put("occupation", "Invalid occupation");
		if (!Util.isValidAccountType(accountType))
			errors.put("accountType", "Invalid account type");
		if (!Util.isValidDebitCard(debitCard))
			errors.put("debitCard", "Invalid debitCard");
		if (!Util.isValidAddress(address))
			errors.put("address", "Invalid address");
		if (!Util.isValidCity(city))
			errors.put("city", "Invalid city");
		if (!Util.isValidState(state))
			errors.put("state", "Invalid state");
		if (!Util.isValidZip(zip))
			errors.put("zip", "Invalid zip code");
		if (!Util.isValidNationality(nationality))
			errors.put("nationality", "Invalid nationality");
		if (!Util.isValidAadhaar(aadhaar))
			errors.put("aadhaar", "Invalid Aadhaar number");
		if (!Util.isValidPan(pan))
			errors.put("pan", "Invalid PAN number");
		if (!Util.isValidIncome(income))
			errors.put("income", "Invalid income");
		if (!Util.isValidMaritalStatus(maritalStatus))
			errors.put("maritalStatus", "Invalid marital status");
		if (!Util.isValidNominee(nominee))
			errors.put("nominee", "Invalid nominee name");
		if (!Util.isValidRelationship(relationship))
			errors.put("relationship", "Invalid relationship");

		
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			if (flag.equals("add")) {
				request.getRequestDispatcher("insert.jsp").forward(request, response);
				return;
			} else {
				User user = new User();

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

				request.setAttribute("user",user);
				request.getRequestDispatcher("form.jsp").forward(request, response);
				return;
			}
		}

		if ("add".equals(flag)) {
			String sql = "INSERT INTO user_accounts (name, gender, email, dob, phone_number, occupation, account_type, debit_card,  address, city, state, zip, nationality, aadhaar, pan, income, marital_status, nominee, relationship) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
				stmt.setString(1, name);
				stmt.setString(2, gender);
				stmt.setString(3, email);
				stmt.setString(4, dob);
				stmt.setString(5, phoneNumber);
				stmt.setString(6, occupation);
				stmt.setString(7, accountType);
				stmt.setString(8, debitCard);
				stmt.setString(9, address);
				stmt.setString(10, city);
				stmt.setString(11, state);
				stmt.setString(12, zip);
				stmt.setString(13, nationality);
				stmt.setString(14, aadhaar);
				stmt.setString(15, pan);
				stmt.setString(16, income);
				stmt.setString(17, maritalStatus);
				stmt.setString(18, nominee);
				stmt.setString(19, relationship);

				int rowsInserted = stmt.executeUpdate();
				if (rowsInserted > 0) {
					HttpSession session = request.getSession();
					ResultSet rs = stmt.getGeneratedKeys();
					if (rs.next()) {
						int userId = rs.getInt(1);
						request.setAttribute("userId", userId);
					}
					request.setAttribute("name", name);
					request.setAttribute("mode", "showall");
					doGet(request, response);
				} else {
					out.println("<script type='text/javascript'>");
					out.println("alert('Registration Failed! Please try again.');");
					out.println("</script>");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			    request.setAttribute("errorMessage", "Database error: " + e.getMessage());
			    request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else if ("update".equals(flag)) {

			int userId = Integer.parseInt(request.getParameter("id"));

			String updateQuery = "UPDATE user_accounts SET name=?, gender=?,email=?, dob=?, phone_number=?, occupation=?, account_type=?, debit_card=?,  address=?, city=?, state=?, zip=?, nationality=?, aadhaar=?, pan=?, income=?, marital_status=?, nominee=?, relationship=? WHERE id=?";
		
			try (PreparedStatement stmt = conn.prepareStatement(updateQuery)){
				
				stmt.setString(1, name);
				stmt.setString(2, gender);
				stmt.setString(3, email);
				stmt.setString(4, dob);
				stmt.setString(5, phoneNumber);
				stmt.setString(6, occupation);
				stmt.setString(7, accountType);
				stmt.setString(8, debitCard);
				stmt.setString(9, address);
				stmt.setString(10, city);
				stmt.setString(11, state);
				stmt.setString(12, zip);
				stmt.setString(13, nationality);
				stmt.setString(14, aadhaar);
				stmt.setString(15, pan);
				stmt.setString(16, income);
				stmt.setString(17, maritalStatus);
				stmt.setString(18, nominee);
				stmt.setString(19, relationship);
				stmt.setInt(20, userId);

				int rowsUpdated = stmt.executeUpdate();
				if (rowsUpdated > 0) {

					request.setAttribute("mode", "showall");
					doGet(request, response);

				} else {
					out.println("<script>alert('Update failed! Record not found.');</script>");

				}

			} catch (SQLException e) {
			    e.printStackTrace();
			    request.setAttribute("errorMessage", "Database error: " + e.getMessage());
			    request.getRequestDispatcher("error.jsp").forward(request, response);
			}

		} else {
			out.println("<script>alert('Invalid operation!');</script>");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mode = (String) request.getAttribute("mode");

		if (mode == null) {
			mode = request.getParameter("mode");
		}

		if (mode == null) {
			mode = "delete";
		}
		if (mode.equals("user")) {
			int userId = Integer.parseInt(request.getParameter("id"));

			if (userId == 0) {
				request.setAttribute("error", "User ID is required");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}

			String sql = "SELECT * FROM user_accounts WHERE id=?";
		

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, userId);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setGender(rs.getString("gender"));
					user.setEmail(rs.getString("email"));
					user.setDob(rs.getString("dob"));
					user.setPhoneNumber(rs.getString("phone_number"));
					user.setOccupation(rs.getString("occupation"));
					user.setAccountType(rs.getString("account_type"));
					user.setDebitCard(rs.getString("debit_card"));
					user.setAddress(rs.getString("address"));
					user.setCity(rs.getString("city"));
					user.setState(rs.getString("state"));
					user.setZip(rs.getString("zip"));
					user.setNationality(rs.getString("nationality"));
					user.setAadhaar(rs.getString("aadhaar"));
					user.setPan(rs.getString("pan"));
					user.setIncome(rs.getString("income"));
					user.setMaritalStatus(rs.getString("marital_status"));
					user.setNominee(rs.getString("nominee"));
					user.setRelationship(rs.getString("relationship"));

					request.setAttribute("user", user);
				} else {
					request.setAttribute("error", "User not found");
					request.getRequestDispatcher("index.jsp").forward(request, response);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "Error retrieving data");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}

			request.getRequestDispatcher("form.jsp").forward(request, response);
		}

		else if (mode.equals("showall")) {
			List<User> userList = new ArrayList<>();

			try(Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM user_accounts")) {
				

				while (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setGender(rs.getString("gender"));
					user.setEmail(rs.getString("email"));
					user.setDob(rs.getString("dob"));
					user.setPhoneNumber(rs.getString("phone_number"));
					user.setOccupation(rs.getString("occupation"));
					user.setAccountType(rs.getString("account_type"));
					user.setDebitCard(rs.getString("debit_card"));
					user.setAddress(rs.getString("address"));
					user.setCity(rs.getString("city"));
					user.setState(rs.getString("state"));
					user.setZip(rs.getString("zip"));
					user.setNationality(rs.getString("nationality"));
					user.setAadhaar(rs.getString("aadhaar"));
					user.setPan(rs.getString("pan"));
					user.setIncome(rs.getString("income"));
					user.setMaritalStatus(rs.getString("marital_status"));
					user.setNominee(rs.getString("nominee"));
					user.setRelationship(rs.getString("relationship"));

					userList.add(user);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("userList", userList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);

		} else {
			String userId = request.getParameter("id");
			PrintWriter out = null;
			if (userId != null) {
				String deleteQuery = "DELETE FROM user_accounts WHERE id=?";

				try (PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
					stmt.setInt(1, Integer.parseInt(userId));
					int rowsDeleted = stmt.executeUpdate();
					if (rowsDeleted > 0) {
						request.setAttribute("mode", "showall");
						doGet(request, response);
					} else {
						out.println("<script>alert('Deletion failed! User not found.');</script>");
					}
				} catch (SQLException e) {
					e.printStackTrace();
					out.println("<html><body><h2>Error while processing request</h2></body></html>");
				}
			} else {
				out.println("<script>alert('Invalid User ID!');</script>");
			}
		}
	}
}
