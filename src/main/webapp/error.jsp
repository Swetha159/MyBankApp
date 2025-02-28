<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
<% if (errorMessage != null) { %>
    <script>alert("<%= errorMessage %>");</script>
<% } %>
