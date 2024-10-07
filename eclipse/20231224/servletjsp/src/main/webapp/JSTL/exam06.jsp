<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ResourceBundle" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>exam06</title>
</head>
<body>
    <h1>/JSTL/exam06.jsp</h1>
    <hr>

    <%
        String value = ResourceBundle.getBundle("bundle").getString("sendMessage");        
    %>

    <%= value %><br>


    <fmt:setBundle basename="bundle" var="myBundle" />    
    <fmt:message key="sendMessage" bundle="${myBundle}" />

    <h2>sendMessage: ${myBundle}</h2>

</body>
</html>