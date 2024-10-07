<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="org.zerock.myapp.domain.Ticket" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ticket.jsp</title>
</head>
<body>

	<h1>일반객체의 EL출력</h1>
	
	<%
		Ticket ticket = new Ticket(1, "A", 2.45);
	
		application.setAttribute("__MODEL2__", ticket);
	%>
	
	<hr>
	
	0. Ticket: ${ __MODEL2__ }<br>
	
	1. Ticket 번호: ${ __MODEL2__.get1() }<br>
	2. Ticket 등급: ${ __MODEL2__.get2() }<br>
	3. Ticket 가격: ${ __MODEL2__.get3() }<br>

</body>
</html>