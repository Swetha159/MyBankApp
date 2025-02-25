<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
        let pastedData = (event.clipboardData || window.clipboardData).getData('text');
        event.target.value = pastedData.replace(/\D/g, ''); 
    }
    
</script>
</head>
<body>
	<div class="container">
		<h2 class="form-title">Bank Account Form</h2>
		<form class="bank-form" action="/MyBank/register" method="POST">

			<!-- Section 1: Personal Information -->
			<div class="form-section active" id = "section1">
				<h2>Personal Information</h2>

				<div class="row">
					<div class="column">
						<label class="label_name" for="name" pattern ="^[A-Za-z][A-Za-z .']*$">Full Name</label> <input
							type="text" class="input-field" name="name"
							placeholder="Enter your name" required>
					</div>
					<div class="column">
						<label class="label_name" for="dob">Date of Birth</label> <input
							type="date" class="input-field" name="dob" required>
					</div>
				</div>

				<label class="label_name" for="gender">Gender</label>
				<div class="gender-group">
					<label><input type="radio" name="gender" value="male"
						required> Male</label> <label><input type="radio"
						name="gender" value="female"> Female</label> <label><input
						type="radio" name="gender" value="others"> Others</label>
				</div>

				<div class="row">
					<div class="column">
						<label class="label_name" for="marital">Marital Status</label> <select
							class="select-field" name="marital" required>
							<option value="single">Single</option>
							<option value="married">Married</option>
							<option value="divorced">Divorced</option>
							<option value="widowed">Widowed</option>
						</select>
					</div>
					<div class="column">
						<label class="label_name" for="nationality">Nationality</label> <input
							type="text" class="input-field" name="nationality"
							placeholder="Enter your nationality" required>
					</div>
					
				</div>
				
				<div class="row">
					<div class="column">
						<label class="label_name" for="occupation">Occupation</label> <input
							type="text" class="input-field" name="occupation"
							placeholder="Enter your occupation" required>
					</div>
					<div class="column">
						<label class="label_name" for="occupation">Annual Income</label> <select
							class="select-field" name="income" required>
							<option value="below-2L">Below 2,00,000</option>
							<option value="2L-5L">2,00,000 - 5,00,000</option>
							<option value="5L-10L">5,00,000 - 10,00,000</option>
							<option value="above-10L">Above 10,00,000</option>
						</select>
					</div>
				</div>

				

				<div class="form-navigation">
					<button type="button" class="nav-btn next" onclick = "nextSection(1)">Next</button>
				</div>
			</div>

			
			<!-- Section 2: Additional Information -->
			<div class="form-section" id = "section2" style="display: none;">
				<h2>Identity & Nominee Information</h2>

				<div class="row">
					<div class="column">
						<label class="label_name" for="pan">PAN Number</label> <input
							type="text" class="input-field" name="pan"
							placeholder="Enter PAN number" pattern="[A-Z]{5}[0-9]{4}[A-Z]{1}"
							required>
					</div>
					<div class="column">
						<label class="label_name" for="aadhaar">Aadhaar Number</label> <input
							type="text" class="input-field" name="aadhaar"
							placeholder="Enter Aadhar number" maxlength=12  oninput="validateDigits(this)" onpaste="handlePaste(event)" required>
					</div>
				</div>

				<div class="row">
					<div class="column">
						<label class="label_name" for="nominee">Nominee Name</label> <input
							type="text" class="input-field" name="nominee"
							placeholder="Enter nominee name" required>
					</div>


					<div class="column">
						<label class="label_name" for="relationship">Relationship
							with Nominee</label> <input type="text" class="input-field"
							name="relationship" placeholder="Enter relationship" required>
					</div>

				</div>
				
				<h2>Account Preferences</h2>

				<div class="row">
					<div class="column">
						<label class="label_name" for="accounttype">Account Type</label> <select
							class="select-field" name="accounttype" required>
							<option value="savings">Savings Account</option>
							<option value="current">Current Account</option>
							<option value="fixed">Fixed Deposit Account</option>
						</select>
					</div>
					<div class="column">
						<label class="label_name" for="servicesNeeded">Services
							Needed</label> <select class="select-field" name="servicesNeeded"
							required>
							<option value="netBanking">Net Banking</option>
							<option value="debitCard">Debit Card</option>
							<option value="creditCard">Credit Card</option>
							
						</select>
					</div>

				</div>
				

				<div class="form-navigation">
					<button type="button" class="nav-btn prev"onclick = "prevSection(2)">Previous</button>
					<button type="button" class="nav-btn next" onclick = "nextSection(2)">Next</button>
				</div>
			</div>
			
			<!-- Section 3: Contact Information -->
			<div class="form-section" id = "section3" style="display: none;">
				<h2>Contact </h2>

				<div class="row">
					<div class="column">
						<label class="label_name" for="email">Email</label> <input
							type="email" class="input-field" name="email"
							placeholder="Enter your email" required>
					</div>
					<div class="column">
						<label class="label_name" for="phonenumber">Phone Number</label> 
						<input type="tel" class="input-field" name="phonenumber"
							placeholder="Enter your phone number" maxlength=10  oninput="validateDigits(this)" onpaste="handlePaste(event)"required>
					</div>

				</div>
				
				<label class="label_name" for="address">Address</label>
				<textarea name="address" class="input-field"
					placeholder="Enter your address" required></textarea>

				<div class="row">
					<div class="column">
						<label class="label_name" for="city">City</label> <input
							type="text" name="city" class="input-field"
							placeholder="Enter City" required>
					</div>
					<div class="column">
						<label class="label_name" for="state">State</label> <input
							type="text" name="state" class="input-field"
							placeholder="Enter State" required>
					</div>
					<div class="column">
						<label class="label_name" for="zip">ZIP Code</label> <input
							type="text" name="zip" class="input-field"
							placeholder="Enter Zipcode" required>
					</div>
				</div>

				<label class="label_name" for="terms"> <input
					type="checkbox" name="terms" required> I agree to the Terms
					and Conditions
				</label> <input type="hidden" id="flag" name="flag" value="add">

				<div class="form-navigation">
					<button type="button" class="nav-btn prev" onclick ="prevSection(3)">Previous</button>
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
