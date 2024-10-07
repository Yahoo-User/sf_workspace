<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page 
    import="java.util.Date"
    import="java.util.Calendar"
    import="java.util.List" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>currentTime.jsp</title>
</head>
<body>

    <h1>현재 날짜 출력 실습</h1>

    <%
        Date now = new Date();
        System.out.println("- now: " + now);

        int age = 23;
    %>

    <h2>Now: <%= now %></h2>
    
    <h2>1. 이름: <%= "Yoseph" %></h2>

    <h2>2. 산술연산식의 결과: <%= 10+100 %></h2>

    <h2>3. 강사의 나이: <%= age %></h2>
</body>
</html>