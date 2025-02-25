<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>

<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h2>Validation Errors</h2>
    <ul>
        <%
           
            Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
            
            
            if (errors != null && !errors.isEmpty()) {
                for (Map.Entry<String, String> entry : errors.entrySet()) {
        %>
                    <li><strong><%= entry.getKey() %></strong>: <%= entry.getValue() %></li>
        <%
                }
            } else {
        %>
                <li>No validation errors found.</li>
        <%
            }
        %>
    </ul>
    <a href="insert.jsp">Go Back</a>
</body>
</html>
