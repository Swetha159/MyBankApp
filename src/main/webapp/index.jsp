<%@ page import="java.util.List, com.bank.registration.User"%>

<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Dashboard</title>
<link href="dashboard.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

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
	request.removeAttribute("errors");
	}
	%>
	<button onclick="window.location.href='insert.jsp'"
		data-tooltip="Add account" class="add-account-btn">
	  Add Account</i>
	</button>



	<%
	List<User> userList = (List<User>) request.getAttribute("userList");
	if (userList != null && !userList.isEmpty()) {
	%>

	<table border="1">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<!--  <th>Gender</th> -->
			<th>Email</th>
			<th>DOB</th>
			<th>Phone</th>
			<!--  <th>Occupation</th> -->
			<th>Account Type</th>
			<!--  <th>Debit Card</th>
            <th>Credit Card</th>
            <th>Net Banking</th> -->
			<!--   <th>Address</th>
            <th>City</th>
            <th>State</th>
            <th>ZIP</th>
            <th>Nationality</th> -->
			<th>Aadhaar</th>
			<th>PAN</th>
			<!--   <th>Income</th>
            <th>Marital Status</th>
            <th>Nominee</th>
            <th>Relationship</th> -->

		</tr>

		<%
		for (User user : userList) {
		%>
		<tr>
			<td><%=user.getId()%></td>
			<td><%=user.getName()%></td>
			<%--  <td><%= user.getGender() %></td> --%>
			<td><%=user.getEmail()%></td>
			<td><%=user.getDob()%></td>
			<td><%=user.getPhoneNumber()%></td>
			<%--   <td><%= user.getOccupation() %></td> --%>
			<td><%=user.getAccountType()%></td>
			<%--   <td><%= user.isDebitCard() ? "Yes" : "No" %></td>
            <td><%= user.isCreditCard() ? "Yes" : "No" %></td>
            <td><%= user.isNetBanking() ? "Yes" : "No" %></td>
            <td><%= user.getAddress() %></td>
            <td><%= user.getCity() %></td>
            <td><%= user.getState() %></td>
            <td><%= user.getZip() %></td>
            <td><%= user.getNationality() %></td> --%>
			<td><%=user.getAadhaar()%></td>
			<td class="action-column"><span class="pan-number"><%=user.getPan()%></span>
				<div class="action-container">

					<button class="action-button" data-tooltip="update"
						onclick="window.location.href='register?flag=update&id=<%=user.getId()%>&mode=user'">
						<i class="fa fa-edit"></i>
					</button>
					<button class="action-button" data-tooltip="delete"
						onclick="window.location.href='register?flag=delete&id=<%=user.getId()%>&mode=delete'">
						<i class="fa fa-trash"></i>
					</button>
				</div></td>
			<%--   <td><%= user.getIncome() %></td>
            <td><%= user.getMaritalStatus() %></td>
            <td><%= user.getNominee() %></td>
            <td><%= user.getRelationship() %></td> --%>



		</tr>

		<%
		}
		%>

	</table>

	<%
	} else {
	%>
	<h2 id = "welcome">Welcome to Admin Page</h2>
	<%
	}
	%>

</body>
</html>