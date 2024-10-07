<%@page
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>response.jsp</title>
</head>
<body>

	<%
		response.sendRedirect("responseRedirect.jsp");
	%>
	
	<%
		System.out.println("---- 리다이렉션 응답전송 후 ----");
	%>

</body>
</html>