<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>customLogin.jsp</title>
</head>
<body>

    <h1>/WEB-INF/views/customLogin.jsp</h1>

    <hr>

    <form action="/login" method="POST">
    
        <fieldset>
            <legend>Login</legend>

            <div>1. Username: <input type="text" name="username" size=50 placeholder="Please input your username."></div>
            <div>2. Password: <input type="password" name="password" size= 50 placeholder="Please input your password."></div>
            <div>&nbsp;</div>

            <div><button type="submit">Sign In</button></div>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

        </fieldset>

        <p>${__ERROR__}</p>
        <p>${__LOGOUT__}</p>
        
    </form>

</body>
</html>