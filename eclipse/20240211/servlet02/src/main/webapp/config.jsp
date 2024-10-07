<%@page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>config.jsp</title>
</head>
<body>
	<h1>config 내장객체를이용한초기화파라미터값얻기</h1>
	
	<%
		String param1 = config.getInitParameter("PARAM1");
		String param2 = config.getInitParameter("PARAM2");
	%>
	
	<h2>1. param1: <%= param1 %></h2>
	<h2>2. param2: <%= param2 %></h2>
</body>
</html>