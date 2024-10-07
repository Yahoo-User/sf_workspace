<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>customLogout.jsp</title>
</head>
<body>

    <h1>/WEB-INF/views/customLogout.jsp</h1>

    <hr>

    <form action="/customLogout" method="POST">
    
        <fieldset>
            <legend>Logout</legend>

            <div><button type="submit">Sign Out</button></div>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

        </fieldset>
        
    </form>

</body>
</html>