<%@page 
	isErrorPage="true"
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error.jsp</title>
</head>
<body>

	<h1>divide.jsp 발생된예외를처리하는페이지</h1>
	
	<h2>잠시 시스템에 문제가 발생하였습니다.<br>조금 후에 다시 시도해 주세요</h2>

	<% exception.printStackTrace(); %>
</body>
</html>