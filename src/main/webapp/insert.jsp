<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Bank Account Form</title>
<link rel="stylesheet" href="style.css">

<script src="script.js"></script>
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
	<jsp:include page="header/header.jsp" />

	<%
	Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");

	if (errors != null && !errors.isEmpty()) {
		StringBuilder errorMessages = new StringBuilder();
		for (Map.Entry<String, String> entry : errors.entrySet()) {
			errorMessages.append(entry.getKey()).append(": ").append(entry.getValue()).append("<br>");
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
		<h2 class="form-title">Bank Account Form</h2>
		<form class="bank-form" action="/MyBank/register" method="POST">

			<!-- Section 1: Personal Information -->
			<div class="form-section active" id="section1">
				<h2>Personal Information</h2>

				<div class="row">
					<div class="column">
						<label class="label_name" for="name">Full Name</label> <input
							type="text" class="input-field" name="name"
							placeholder="Enter your name" pattern="^[A-Za-z][A-Za-z .']*$"
							value="<%=request.getParameter("name") != null ? request.getParameter("name") : ""%>"
							required>
					</div>
					<div class="column">
						<label class="label_name" for="dob">Date of Birth</label> <input
							type="date" class="input-field" name="dob"
							value="<%=request.getParameter("dob") != null ? request.getParameter("dob") : ""%>"
							required>
					</div>
				</div>

				<label class="label_name" for="gender">Gender</label>
				<div class="gender-group">
					<label><input type="radio" name="gender" value="male"
						<%="male".equals(request.getParameter("gender")) ? "checked" : ""%>
						required> Male</label> <label><input type="radio"
						name="gender" value="female"
						<%="female".equals(request.getParameter("gender")) ? "checked" : ""%>>
						Female</label> <label><input type="radio" name="gender"
						value="others"
						<%="others".equals(request.getParameter("gender")) ? "checked" : ""%>>
						Others</label>
				</div>

				<div class="row">
					<div class="column">
						<label class="label_name" for="marital">Marital Status</label> <select
							class="select-field" name="marital" required>
							<option value="single"
								<%="single".equals(request.getParameter("marital")) ? "selected" : ""%>>Single</option>
							<option value="married"
								<%="married".equals(request.getParameter("marital")) ? "selected" : ""%>>Married</option>
							<option value="divorced"
								<%="divorced".equals(request.getParameter("marital")) ? "selected" : ""%>>Divorced</option>
							<option value="widowed"
								<%="widowed".equals(request.getParameter("marital")) ? "selected" : ""%>>Widowed</option>
						</select>
					</div>
					<div class="column">
						<label class="label_name" for="nationality">Nationality</label> <input
							type="text" class="input-field" name="nationality"
							placeholder="Enter your nationality"
							pattern="^[A-Za-z][A-Za-z\s\-\.']*$"
							value="<%=request.getParameter("nationality") != null ? request.getParameter("nationality") : ""%>"
							required>
					</div>

				</div>

				<div class="row">
					<div class="column">
						<label class="label_name" for="occupation">Occupation</label> <input
							type="text" class="input-field" name="occupation"
							placeholder="Enter your occupation"
							pattern="^[A-Za-z][A-Za-z\s\-\.']*$"
							value="<%=request.getParameter("occupation") != null ? request.getParameter("occupation") : ""%>"
							required>
					</div>
					<div class="column">
						<label class="label_name" for="income">Annual Income</label> <select
							class="select-field" name="income" required>
							<option value="below-2L"
								<%="below-2L".equals(request.getParameter("income")) ? "selected" : ""%>>Below
								2,00,000</option>
							<option value="2L-5L"
								<%="2L-5L".equals(request.getParameter("income")) ? "selected" : ""%>>2,00,000
								- 5,00,000</option>
							<option value="5L-10L"
								<%="5L-10L".equals(request.getParameter("income")) ? "selected" : ""%>>5,00,000
								- 10,00,000</option>
							<option value="above-10L"
								<%="above-10L".equals(request.getParameter("income")) ? "selected" : ""%>>Above
								10,00,000</option>
						</select>
					</div>
				</div>



				<div class="form-navigation">
					<button type="button" class="nav-btn next" id="nav-btn-next"
						onclick="nextSection(1)">Next</button>
				</div>
			</div>


			<!-- Section 2: Additional Information -->
			<div class="form-section" id="section2" style="display: none;">
				<h2>Identity & Nominee Information</h2>

				<div class="row">
					<div class="column">
						<label class="label_name" for="pan">PAN Number</label> <input
							type="text" class="input-field" name="pan"
							placeholder="Enter PAN number" pattern="[A-Z]{5}[0-9]{4}[A-Z]{1}"
							value="<%=request.getParameter("pan") != null ? request.getParameter("pan") : ""%>"
							required>
					</div>
					<div class="column">
						<label class="label_name" for="aadhaar">Aadhaar Number</label> <input
							type="text" class="input-field" name="aadhaar"
							placeholder="Enter Aadhar number" maxlength=12
							oninput="validateDigits(this)" onpaste="handlePaste(event)"
							value="<%=request.getParameter("aadhaar") != null ? request.getParameter("aadhaar") : ""%>"
							required>
					</div>
				</div>

				<div class="row">
					<div class="column">
						<label class="label_name" for="nominee">Nominee Name</label> <input
							type="text" class="input-field" name="nominee"
							placeholder="Enter nominee name" pattern="^[A-Za-z][A-Za-z .']*$"
							value="<%=request.getParameter("nominee") != null ? request.getParameter("nominee") : ""%>"
							required>
					</div>


					<div class="column">
						<label class="label_name" for="relationship">Relationship
							with Nominee</label> <input type="text" class="input-field"
							name="relationship" placeholder="Enter relationship"
							pattern="^[A-Za-z][A-Za-z\s\-\.']*$"
							value="<%=request.getParameter("relationship") != null ? request.getParameter("relationship") : ""%>"
							required>
					</div>

				</div>

				<h2 id="subheading">Account Preferences</h2>

				<div class="row">
					<div class="column">
						<label class="label_name" for="accounttype">Account Type</label> <select
							class="select-field" name="accounttype" required>
							<option value="savings"
								<%="savings".equals(request.getParameter("accounttype")) ? "selected" : ""%>>Savings
								Account</option>
							<option value="current"
								<%="current".equals(request.getParameter("accounttype")) ? "selected" : ""%>>Current
								Account</option>
							<option value="fixed"
								<%="fixed".equals(request.getParameter("accounttype")) ? "selected" : ""%>>Fixed
								Deposit Account</option>
						</select>
					</div>
					<div class="column">
						<label class="label_name" for="debitCard">Debit Card Type</label>
						<select class="select-field" name="debitCard" required>
							<option value="visa"
								<%="visa".equals(request.getParameter("debitCard")) ? "selected" : ""%>>Visa
								Debit Card</option>
							<option value="master"
								<%="master".equals(request.getParameter("debitCard")) ? "selected" : ""%>>Mastercard
								Debit Card</option>
							<option value="rupay"
								<%="rupay".equals(request.getParameter("debitCard")) ? "selected" : ""%>>RuPay
								Debit Card</option>
						</select>

					</div>

				</div>


				<div class="form-navigation">
					<button type="button" class="nav-btn prev" onclick="prevSection(2)">Previous</button>
					<button type="button" class="nav-btn next" onclick="nextSection(2)">Next</button>
				</div>
			</div>

			<!-- Section 3: Contact Information -->
			<div class="form-section" id="section3" style="display: none;">
				<h2>Contact</h2>

				<div class="row">
					<div class="column">
						<label class="label_name" for="email">Email</label> <input
							type="email" class="input-field" name="email"
							placeholder="Enter your email"
							value="<%=request.getParameter("email") != null ? request.getParameter("email") : ""%>"
							required>
					</div>
					<div class="column">
						<label class="label_name" for="phonenumber">Phone Number</label> <input
							type="tel" class="input-field" name="phonenumber"
							placeholder="Enter your phone number" maxlength=10
							oninput="validateDigits(this)" pattern="\d{10}"
							onpaste="handlePaste(event)"
							value="<%=request.getParameter("phonenumber") != null ? request.getParameter("phonenumber") : ""%>"
							required>
					</div>

				</div>
				<div class="row">

					<label class="label_name" for="address">Address</label> <input
						type="text" name="address" class="input-field"
						placeholder="Enter your address" id="address"
						pattern="^[A-Za-z][A-Za-z\s\-\(\:\)\,\.']*$"
						value="<%=request.getParameter("address") != null ? request.getParameter("address") : ""%>"
						required>
				</div>

				<div class="row" id="location">
					<div class="column">
						<label class="label_name" for="city">City</label> <input
							type="text" name="city" class="input-field"
							placeholder="Enter City" pattern="^[A-Za-z][A-Za-z\s\-\.']*$"
							value="<%=request.getParameter("city") != null ? request.getParameter("city") : ""%>"
							required>
					</div>
					<div class="column">
						<label class="label_name" for="state">State</label> <input
							type="text" name="state" class="input-field"
							placeholder="Enter State" pattern="^[A-Za-z][A-Za-z\s\-\.']*$"
							value="<%=request.getParameter("state") != null ? request.getParameter("state") : ""%>"
							required>
					</div>
					<div class="column">
						<label class="label_name" for="zip">ZIP Code</label> <input
							type="text" name="zip" class="input-field"
							placeholder="Enter Zipcode" maxlength=6
							oninput="validateDigits(this)" onpaste="handlePaste(event)"
							value="<%=request.getParameter("zip") != null ? request.getParameter("zip") : ""%>"
							required>
					</div>
				</div>

				<label class="label_name" for="terms"> <input
					type="checkbox" name="terms" id="terms" required> I agree
					to the Terms and Conditions
				</label> <input type="hidden" id="flag" name="flag" value="add">

				<div class="form-navigation">
					<button type="button" class="nav-btn prev" onclick="prevSection(3)">Previous</button>
					<button type="submit" class="nav-btn">Submit</button>
				</div>

			</div>





		</form>
	</div>
	<script src="script.js"></script>
</body>
</html>

</form>
</div>

</body>
</html>
