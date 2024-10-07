<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>exam07</title>
</head>

<body>
    <h1>/JSTL/examp07.jsp</h1>
    <hr>
    
    <% System.out.println( new Date() ); %>

    <c:set var="myDate" value="<%= new Date() %>" scope="request" />
    <c:out value="${myDate}" /><br>

    <!-- ===================== -->

    0. now: ${myDate}<br>
    1. <c:out value="${ myDate.getClass().getName() }" /><br>

    2. <fmt:formatDate value="${myDate}" type="date"/><br>
    3. <fmt:formatDate value="${myDate}" type="time"/><br>    
    4. <fmt:formatDate value="${myDate}" type="both"/><br>

    5. <fmt:formatDate value="${myDate}" type="both" dateStyle="short" timeStyle="long"/><br>
    6. <fmt:formatDate value="${myDate}" type="both" dateStyle="long" timeStyle="short"/><br>

    7. <fmt:formatDate value="${myDate}" pattern="yyyy/MM/dd HH:mm:ss.SSS" /><br>

    <!-- ===================== -->

    8. <fmt:formatNumber value="100000" type="currency" /><br>
    9. <fmt:formatNumber value="0.123" type="percent" /><br>
   10. <fmt:formatNumber value="987654321.1234" pattern="###,###,###.00" /><br>


</body>
</html>