<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Diagnostic Result</title>
</head>
<body>

<%
    // Retrieve the diagnostic result from the request
    String diagnosticResult = (String) request.getAttribute("diagnosticResult");
%>

<h2>Diagnostic Result:</h2>
<p><strong><%= diagnosticResult %></strong></p>

</body>
</html>
