<%@page
	session="true" 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logout.jsp</title>
</head>
<body>
	
	<%
		session.invalidate();	// 세션영역 파괴
		
		response.sendRedirect("/loginForm.html");
	%>
	
</body>
</html>