<%@page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>countInfo.jsp</title>
</head>
<body>
	<h1>방문자수 조회하기 화면</h1>
	
	<hr>
	
	<%
		int count = 
			(Integer) application.getAttribute("countValue");
	%>

	<h1>현재까지 총 방문자수: <%= count %></h1>
</body>
</html>







