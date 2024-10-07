<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward.jsp</title>
</head>
<body>

	<h1>current.jsp에서forward 된화면</h1>
	
	<%
		String httpMethod = request.getMethod();
	
		String data = request.getParameter("data");
		String nickName = request.getParameter("nickName");
	%>

	<h1>포워드되었으며넘어온파라미터값은<%= nickName %></h1> 
	<h1>직접입력시킨 파라미터값은<%= data %>입니다.</h1>
	
	<h1>HTTP method: <%= httpMethod %></h1>
	
</body>
</html>