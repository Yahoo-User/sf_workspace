<%@page 
	errorPage="/error.jsp"
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>divide.jsp</title>
</head>
<body>

	<%
		// ZeroDivisionError 발생 유도!!! 
		int result = 4 / 0;	
	%>

</body>
</html>