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
		boolean debitCard = request.getParameter("debitcard") != null;
		boolean creditCard = request.getParameter("creditcard") != null;
		boolean netBanking = request.getParameter("netbanking") != null;
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
		  Map<String, String> errors = Util.validateForm(
	                name, gender, email, dob, phoneNumber, occupation, accountType,
	                address, city, state, zip, nationality, aadhaar, pan, income,
	                maritalStatus, nominee, relationship
	        );

	        // If errors exist, forward back to the form with error messages
	        if (!errors.isEmpty()) {
	            request.setAttribute("errors", errors);
	            request.getRequestDispatcher("error.jsp").forward(request, response);
	            return;
	        }

		
		if ("add".equals(flag)) {
			String sql = "INSERT INTO user_accounts (name, gender, email, dob, phone_number, occupation, account_type, debit_card, credit_card, net_banking, address, city, state, zip, nationality, aadhaar, pan, income, marital_status, nominee, relationship) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
				stmt.setString(1, name);
				stmt.setString(2, gender);
				stmt.setString(3, email);
				stmt.setString(4, dob);
				stmt.setString(5, phoneNumber);
				stmt.setString(6, occupation);
				stmt.setString(7, accountType);
				stmt.setBoolean(8, debitCard);
				stmt.setBoolean(9, creditCard);
				stmt.setBoolean(10, netBanking);
				stmt.setString(11, address);
				stmt.setString(12, city);
				stmt.setString(13, state);
				stmt.setString(14, zip);
				stmt.setString(15, nationality);
				stmt.setString(16, aadhaar);
				stmt.setString(17, pan);
				stmt.setString(18, income);
				stmt.setString(19, maritalStatus);
				stmt.setString(20, nominee);
				stmt.setString(21, relationship);

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
//                request.getRequestDispatcher("Register").forward(request, response);
					doGet(request, response);
				} else {
					out.println("<script type='text/javascript'>");
					out.println("alert('Registration Failed! Please try again.');");
					out.println("</script>");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				out.println("<html><body><h2>Error while storing data</h2></body></html>");
			}
		} else if ("update".equals(flag)) {;
			HttpSession session = request.getSession();
			int userId = (int) session.getAttribute("userId");
			String updateQuery = "UPDATE user_accounts SET name=?, gender=?,email=?, dob=?, phone_number=?, occupation=?, account_type=?, debit_card=?, credit_card=?, net_banking=?, address=?, city=?, state=?, zip=?, nationality=?, aadhaar=?, pan=?, income=?, marital_status=?, nominee=?, relationship=? WHERE id=?";
			PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement(updateQuery);

				stmt.setString(1, name);
				stmt.setString(2, gender);
				stmt.setString(3, email);
				stmt.setString(4, dob);
				stmt.setString(5, phoneNumber);
				stmt.setString(6, occupation);
				stmt.setString(7, accountType);
				stmt.setBoolean(8, debitCard);
				stmt.setBoolean(9, creditCard);
				stmt.setBoolean(10, netBanking);
				stmt.setString(11, address);
				stmt.setString(12, city);
				stmt.setString(13, state);
				stmt.setString(14, zip);
				stmt.setString(15, nationality);
				stmt.setString(16, aadhaar);
				stmt.setString(17, pan);
				stmt.setString(18, income);
				stmt.setString(19, maritalStatus);
				stmt.setString(20, nominee);
				stmt.setString(21, relationship);
				stmt.setInt(22, userId);

				int rowsUpdated = stmt.executeUpdate();
				if (rowsUpdated > 0) {
					// out.println("<script>alert('Update successful!');</script>");
					request.setAttribute("mode", "showall");
					doGet(request, response);
					// request.getRequestDispatcher("dashboard.jsp").forward(request, response);

				} else {
					out.println("<script>alert('Update failed! Record not found.');</script>");

				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		} else {
			out.println("<script>alert('Invalid operation!');</script>");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//HttpSession session = request.getSession();
		System.out.println("vanthuten");
		String mode = (String) request.getAttribute("mode");
		
		  if (mode == null) { mode = request.getParameter("mode"); }
		 
		if (mode == null) {
			mode = "delete"; 
		}
		if (mode.equals("user")) {

		 String userId =  request.getParameter("id");

			if (userId == null) {
				request.setAttribute("error", "User ID is required");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}

			String sql = "SELECT * FROM user_accounts WHERE id=?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, userId);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					request.setAttribute("id", rs.getInt("id"));
					request.setAttribute("name", rs.getString("name"));
					request.setAttribute("gender", rs.getString("gender"));
					request.setAttribute("email", rs.getString("email"));
					request.setAttribute("dob", rs.getString("dob"));
					request.setAttribute("phone", rs.getString("phone_number"));
					
					request.setAttribute("occupation", rs.getString("occupation"));
					  request.setAttribute("accountType", rs.getString("account_type"));
					 request.setAttribute("debitCard", rs.getBoolean("debit_card"));
					 request.setAttribute("creditCard", rs.getBoolean("credit_card"));
					  request.setAttribute("netBanking", rs.getBoolean("net_banking"));
					  request.setAttribute("address", rs.getString("address"));
					  request.setAttribute("city", rs.getString("city"));
					  request.setAttribute("state", rs.getString("state"));
					  request.setAttribute("zip", rs.getString("zip"));
					  request.setAttribute("nationality", rs.getString("nationality"));
					
					request.setAttribute("aadhaar", rs.getString("aadhaar"));
					request.setAttribute("pan", rs.getString("pan"));
					
					  request.setAttribute("income", rs.getString("income"));
					  request.setAttribute("maritalStatus", rs.getString("marital_status"));
					  request.setAttribute("nominee", rs.getString("nominee"));
					  request.setAttribute("relationship", rs.getString("relationship"));
					 
				}
				System.out.println("Vareennnnn");
				request.getRequestDispatcher("form.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "Error retrieving data");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}

		else if (mode.equals("showall")) {
			List<User> userList = new ArrayList<>();

			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM user_accounts");

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
					user.setDebitCard(rs.getBoolean("debit_card"));
					user.setCreditCard(rs.getBoolean("credit_card"));
					user.setNetBanking(rs.getBoolean("net_banking"));
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
