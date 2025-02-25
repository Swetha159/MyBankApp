<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.Locale"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
Locale locale = request.getLocale();
String country = locale.getDisplayCountry();
String nationality = country;
String name = "", gender = "", email = "", phoneNumber = "", dob = "", occupation = "", accountType = "", address = "",
		city = "", state = "", zip = "", maritalStatus = "", aadhaar = "", pan = "", nominee = "", relationship = "",
		income = "", services = "";

boolean isUpdating = true;

HttpSession userSession = request.getSession();

Integer userId = (Integer) request.getAttribute("id");
userSession.setAttribute("userId", userId);
String userName = (String) request.getAttribute("name");

if (userId != null) {

	name = (String) request.getAttribute("name");
	gender = (String) request.getAttribute("gender");
	System.out.println(gender);
	email = (String) request.getAttribute("email");
	dob = (String) request.getAttribute("dob");
	phoneNumber = (String) request.getAttribute("phone");
	occupation = (String) request.getAttribute("occupation");
	accountType = (String) request.getAttribute("accountType");
	boolean debitCard = (Boolean) request.getAttribute("debitCard");
	boolean creditCard = (Boolean) request.getAttribute("creditCard");
	boolean netBanking = (Boolean) request.getAttribute("netBanking");
	address = (String) request.getAttribute("address");
	city = (String) request.getAttribute("city");
	state = (String) request.getAttribute("state");
	zip = (String) request.getAttribute("zip");
	nationality = (String) request.getAttribute("nationality");
	aadhaar = (String) request.getAttribute("aadhaar");
	pan = (String) request.getAttribute("pan");
	income = (String) request.getAttribute("income");
	maritalStatus = (String) request.getAttribute("maritalStatus");
	nominee = (String) request.getAttribute("nominee");
	relationship = (String) request.getAttribute("relationship");

	isUpdating = true;
	request.setAttribute("userId", userId);
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Bank - Account Form</title>
<link rel="stylesheet" href="form.css">
</head>
<body>

	<div class="container">
		<h2 class="form-title">
			<%=isUpdating ? "Your Profile" : "Bank Account Registration Form"%>
		</h2>


		<form class="bank-form" method="POST">

			<%
			if (isUpdating) {
			%>
			<input type="hidden" id="flag" name="flag" value="update">
			<%
			} else {
			%>
			<input type="hidden" id="flag" name="flag" value="add">
			<%
			}
			%>


			<label class="label_name" for="name">Full Name</label> <input
				type="text" id="name" name="name" class="input-field"
				value="<%=name%>" required> <label class="label_name">Gender</label>
			<div class="gender-group">
				<label><input type="radio" name="gender" value="male"
					<%=gender.equals("male") ? "checked" : ""%>> Male</label> <label><input
					type="radio" name="gender" value="female"
					<%=gender.equals("female") ? "checked" : ""%>> Female</label> <label><input
					type="radio" name="gender" value="others"
					<%=gender.equals("others") ? "checked" : ""%>> Others</label>
			</div>

			<div class="row">
				<div class="column">
					<label class="label_name" for="email">Email</label> <input
						type="email" id="email" name="email" class="input-field"
						pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
						value="<%=email%>" required>
				</div>
				<div class="column">
					<label class="label_name" for="phonenumber">Phone Number</label> <input
						type="text" id="phonenumber" name="phonenumber"
						class="input-field" inputmode="numeric" value="<%=phoneNumber%>"
						required>
				</div>
			</div>

			<label class="label_name" for="dob">Date of Birth</label> <input
				type="date" id="dob" name="dob" class="input-field"
				value="<%=dob%>" required> <label class="label_name"
				for="occupation">Occupation</label> <input type="text"
				id="occupation" name="occupation" class="input-field"
				value="<%=occupation%>" required> <label
				class="label_name" for="accounttype">Account Type</label> <select
				id="accounttype" name="accounttype" class="select-field" required>
				<option value="Saving"
					<%=accountType.equals("Saving") ? "selected" : ""%>>Savings</option>
				<option value="Current"
					<%=accountType.equals("Current") ? "selected" : ""%>>Current</option>
			</select> <label class="label_name" for="address">Address</label>
			<textarea id="address" name="address" class="input-field" rows="3"
				required><%=address%></textarea>

			<div class="row">
				<div class="column">
					<label class="label_name" for="city">City</label> <input
						type="text" id="city" name="city" class="input-field"
						value="<%=city%>" required>
				</div>
				<div class="column">
					<label class="label_name" for="state">State</label> <input
						type="text" id="state" name="state" class="input-field"
						value="<%=state%>" required>
				</div>
				<div class="column">
					<label class="label_name" for="zip">ZIP Code</label> <input
						type="text" id="zip" name="zip" class="input-field"
						value="<%=zip%>" required>
				</div>
			</div>

			<div class="row">
				<div class="column">
					<label class="label_name" for="marital">Marital Status</label> <select
						id="marital" name="marital" class="select-field">
						<option value="single"
							<%=maritalStatus.equals("single") ? "selected" : ""%>>Single</option>
						<option value="married"
							<%=maritalStatus.equals("married") ? "selected" : ""%>>Married</option>
						<option value="divorced"
							<%=maritalStatus.equals("divorced") ? "selected" : ""%>>Divorced</option>
					</select>
				</div>
				<div class="column">
					<label class="label_name" for="nationality">Nationality</label> <input
						type="text" id="nationality" name="nationality"
						class="input-field" value="<%=nationality%>" required>
				</div>
			</div>

			<label class="label_name" for="aadhaar">Aadhaar Number</label> <input
				type="text" id="aadhaar" name="aadhaar" class="input-field"
				maxlength=12 value="<%=aadhaar%>" required>


			<div class="row">
				<div class="column">
					<label class="label_name" for="nominee">Nominee</label> <input
						type="text" id="nominee" name="nominee" class="input-field"
						value="<%=nominee%>" required>
				</div>
				<div class="column">
					<label class="label_name" for="relationship">Relationship</label> <input
						type="text" id="relationship" name="relationship"
						class="input-field" value="<%=relationship%>" required>
				</div>
			</div>
			<label class="label_name" for="pan">PAN Number</label> <input
				type="text" id="pan" name="pan" class="input-field"
				pattern="^[A-Z]{5}[0-9]{4}[A-Z]{1}$" value="<%=pan%>" required>
			<label class="label_name" for="income">Income Range</label> <select
				id="income" name="income" class="select-field" required>
				<option value="below-2L"
					<%=income.equals("below-2L") ? "selected" : ""%>>Below
					2,00,000</option>
				<option value="2L-5L"
					<%=income.equals("2L-5L") ? "selected" : ""%>>2,00,000 -
					5,00,000</option>
				<option value="5L-10L"
					<%=income.equals("5L-10L") ? "selected" : ""%>>5,00,000
					- 10,00,000</option>
				<option value="above-10L"
					<%=income.equals("above-10L") ? "selected" : ""%>>Above
					10,00,000</option>
			</select>

			<button type="submit" id="submit" class="submit-btn"><%=isUpdating ? "Update" : "Submit"%></button>

		</form>
	</div>

</body>
</html>