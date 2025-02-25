<%@ page import="java.util.List, com.bank.registration.User"%>

<!DOCTYPE html>
<html>
<head>
<title>Admin Dashboard</title>
<link href="dashboard.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<script>
	window.onload = function() {
		if (!window.location.href.includes("mode=showall")) {
			window.location.href = "register?mode=showall";
		}
	};
</script>

</head>
<body>
	<h2>ADMIN</h2>
	<button onclick="window.location.href='insert.jsp'"
		class="add-account-btn">
		<i class="fa fa-plus"></i>
	</button>



	<%
	List<User> userList = (List<User>) request.getAttribute("userList");
	if (userList != null && !userList.isEmpty()) {
	%>

	<table border="1">
		<tr>
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
			<td><%=user.getPan()%></td>
			<%--   <td><%= user.getIncome() %></td>
            <td><%= user.getMaritalStatus() %></td>
            <td><%= user.getNominee() %></td>
            <td><%= user.getRelationship() %></td> --%>
			<td>
				<button class="action-button" data-tooltip="update"
					onclick="window.location.href='register?flag=update&id=<%=user.getId()%>&mode=user'">
					<i class="fa fa-edit"></i>
				</button>
			</td>

			<td>
				<button class="action-button" data-tooltip="delete"
					onclick="window.location.href='register?flag=delete&id=<%=user.getId()%>'">
					<i class="fa fa-trash"></i>
				</button>
			</td>


		</tr>
		<%
		}
		%>
	</table>

	<%
	} else {
	%>
	<p>Welcome to Admin Page</p>
	<%
	}
	%>

</body>
</html>