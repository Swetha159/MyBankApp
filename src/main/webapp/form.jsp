<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="java.util.Map"%>
<%@ page import="com.bank.registration.User"%>

<%
User user = (User) request.getAttribute("user");

String name = "", gender = "", email = "", phoneNumber = "", dob = "", occupation = "", accountType = "", address = "",
		city = "", state = "", zip = "", maritalStatus = "", aadhaar = "", pan = "", nominee = "", relationship = "",
		income = "", nationality = "", debitCard = "";
int id = 0;
boolean isUpdating = false;

if (user != null) {
	isUpdating = true;
	id = user.getId();
	name = user.getName();
	gender = user.getGender();
	email = user.getEmail();
	dob = user.getDob();
	phoneNumber = user.getPhoneNumber();
	occupation = user.getOccupation();
	accountType = user.getAccountType();
	debitCard = user.getDebitCard();
	address = user.getAddress();
	city = user.getCity();
	state = user.getState();
	zip = user.getZip();
	nationality = user.getNationality();
	aadhaar = user.getAadhaar();
	pan = user.getPan();
	income = user.getIncome();
	maritalStatus = user.getMaritalStatus();
	nominee = user.getNominee();
	relationship = user.getRelationship();
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Bank - Account Form</title>
<link rel="stylesheet" href="form.css">
<script>
	function validateDigits(input) {
		input.value = input.value.replace(/\D/g, '');
	}

	function handlePaste(event) {
		event.preventDefault();
		n
		let pastedData = (event.clipboardData || window.clipboardData)
				.getData('text');
		event.target.value = pastedData.replace(/\D/g, '');
	}
</script>

</head>
<body>
	<header class="banner">
		<img src="images/ftlogowhite.png" alt="Bank Logo" class="bank-logo">

		<h1 id="bank-name">FINTRUST BANK</h1>

	</header>
	<%
	Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");

	if (errors != null && !errors.isEmpty()) {
		StringBuilder errorMessages = new StringBuilder();
		for (Map.Entry<String, String> entry : errors.entrySet()) {
			errorMessages.append(entry.getKey()).append(": ").append(entry.getValue()).append("<br>"); // Line break for better readability
		}
	%>
	<div id="errorAlert" class="error-box">
		<p><%=errorMessages.toString()%></p>
		<button onclick="closeAlert()">OK</button>
	</div>

	<script>
		function closeAlert() {
			document.getElementById("errorAlert").style.display = "none";
		}
	</script>


	<%
	}
	%>

	<div class="container">
		<h2 class="form-title">
			<%=isUpdating ? "User Details" : "Bank Account Registration Form"%>
		</h2>


		<form class="bank-form" action="/MyBank/register" method="POST">




			<label class="label_name" for="name">Full Name</label> <input
				type="text" id="name" name="name" class="input-field"
				value="<%=name%>" placeholder="Enter your name" required
				> <label class="label_name">Gender</label>
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
						placeholder="Enter your email"
						pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
						value="<%=email%>" required>
				</div>
				<div class="column">
					<label class="label_name" for="phonenumber">Phone Number</label> <input
						type="text" id="phonenumber" name="phonenumber" pattern="\d{10}"
						class="input-field" inputmode="numeric"
						placeholder="Enter your phone number" maxlength=10
						oninput="validateDigits(this)" onpaste="handlePaste(event)"
						value="<%=phoneNumber%>" required>
				</div>
			</div>

			<label class="label_name" for="dob">Date of Birth</label> <input
				type="date" id="dob" name="dob" class="input-field" value="<%=dob%>"
				required> <label class="label_name" for="occupation">Occupation</label>
			<input type="text" id="occupation" name="occupation"
				class="input-field" placeholder="Enter your occupation"
				pattern="^[A-Za-z][A-Za-z\s\-\.']*$" value="<%=occupation%>"
				required> <label class="label_name" for="accounttype">Account
				Type</label> <select id="accounttype" name="accounttype"
				class="select-field" required>
				<option value="savings"
					<%=accountType.equals("savings") ? "selected" : ""%>>Savings
					Account</option>
				<option value="current"
					<%=accountType.equals("current") ? "selected" : ""%>>Current
					Account</option>
				<option value="current"
					<%=accountType.equals("fixed") ? "selected" : ""%>>Fixed
					Deposit</option>
			</select> <label class="label_name" for="address">Address</label> <input
				type="text" name="address" class="input-field"
				placeholder="Enter your address" id="address"
				pattern="^[A-Za-z][A-Za-z\s\-\(\:\)\,\.']*$" value="<%=address%>"
				required> <label class="label_name" for="debitCard">Debit
				Card Type</label> <select class="select-field" name="debitCard"
				id="debitCard" required>
				<option value="visa" <%if ("visa".equals(debitCard)) {%> selected
					<%}%>>Visa Debit Card</option>
				<option value="master" <%if ("master".equals(debitCard)) {%>
					selected <%}%>>Mastercard Debit Card</option>
				<option value="rupay" <%if ("rupay".equals(debitCard)) {%>
					selected <%}%>>RuPay Debit Card</option>
			</select>
			<div class="row">
				<div class="column">
					<label class="label_name" for="city">City</label> <input
						type="text" id="city" name="city" class="input-field"
						value="<%=city%>" placeholder="Enter City"
						pattern="^[A-Za-z][A-Za-z\s\-\.']*$" required>
				</div>
				<div class="column">
					<label class="label_name" for="state">State</label> <input
						type="text" id="state" name="state" class="input-field"
						value="<%=state%>" placeholder="Enter State"
						pattern="^[A-Za-z][A-Za-z\s\-\.']*$" required>
				</div>
				<div class="column">
					<label class="label_name" for="zip">ZIP Code</label> <input
						type="text" id="zip" name="zip" class="input-field"
						placeholder="Enter Zipcode" maxlength=6
						oninput="validateDigits(this)" onpaste="handlePaste(event)"
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
						<option value="widowed"
							<%=maritalStatus.equals("widowed") ? "selected" : ""%>>Widowed</option>
					</select>
				</div>
				<div class="column">
					<label class="label_name" for="nationality">Nationality</label> <input
						type="text" id="nationality" name="nationality"
						class="input-field" placeholder="Enter your nationality"
						pattern="^[A-Za-z][A-Za-z\s\-\.']*$" value="<%=nationality%>"
						required>
				</div>
			</div>

			<label class="label_name" for="aadhaar">Aadhaar Number</label> <input
				type="text" id="aadhaar" name="aadhaar" class="input-field"
				placeholder="Enter Aadhar number" maxlength=12
				oninput="validateDigits(this)" onpaste="handlePaste(event)"
				value="<%=aadhaar%>" required>


			<div class="row">
				<div class="column">
					<label class="label_name" for="nominee">Nominee</label> <input
						type="text" id="nominee" name="nominee" class="input-field"
						placeholder="Enter nominee name" pattern="^[A-Za-z][A-Za-z .']*$"
						value="<%=nominee%>" required>
				</div>
				<div class="column">
					<label class="label_name" for="relationship">Relationship</label> <input
						type="text" id="relationship" name="relationship"
						class="input-field" placeholder="Enter relationship"
						pattern="^[A-Za-z][A-Za-z\s\-\.']*$" value="<%=relationship%>"
						required>
				</div>
			</div>
			<label class="label_name" for="pan">PAN Number</label> <input
				type="text" id="pan" name="pan" class="input-field"
				placeholder="Enter PAN number" pattern="[A-Z]{5}[0-9]{4}[A-Z]{1}"
				value="<%=pan%>" required> <label class="label_name"
				for="income">Income Range</label> <select id="income" name="income"
				class="select-field" required>
				<option value="below-2L"
					<%=income.equals("below-2L") ? "selected" : ""%>>Below
					2,00,000</option>
				<option value="2L-5L" <%=income.equals("2L-5L") ? "selected" : ""%>>2,00,000
					- 5,00,000</option>
				<option value="5L-10L"
					<%=income.equals("5L-10L") ? "selected" : ""%>>5,00,000 -
					10,00,000</option>
				<option value="above-10L"
					<%=income.equals("above-10L") ? "selected" : ""%>>Above
					10,00,000</option>
			</select> <input type="hidden" id="flag" name="flag" value="update"> <input
				type="hidden" id="id" name="id" value="<%=id%>">

			<button type="submit" id="submit" class="submit-btn"><%=isUpdating ? "Update" : "Submit"%></button>

		</form>
	</div>

</body>
</html>