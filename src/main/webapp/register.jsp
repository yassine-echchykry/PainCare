<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
    <!-- Ajoutez ici vos liens CSS ou d'autres déclarations de tête -->
</head>
<body>
    <div>
        <h2>User Registration</h2>
        
        <c:if test="${not empty requestScope.form_error}">
            <p style="color: red;">${requestScope.form_error}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/register" method="post">
            <label for="name">Username:</label>
            <input type="text" id="name" name="name" required><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>

            <label for="password2">Confirm Password:</label>
            <input type="password" id="password2" name="password2" required><br>

            <button type="submit">Register</button>
        </form>

        <p>Already have an account? <a href="${pageContext.request.contextPath}/login">Login here</a></p>
    </div>
    <!-- Ajoutez ici vos scripts ou autres contenus de page -->
</body>
</html>
