<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>count.jsp</title>
</head>
<body>
	<h1>방문자수 설정하기 화면</h1>
	
	<hr>
	
	<!-- JSP 선언태그를 통해, 인스턴스 필드 선언 -->
	<%!
		int count;
	%>
	
	<%
		count++;

		// ServletContext 객체
		application.setAttribute("countValue", count);
	%>

	현재 방문자수: <%= count %>
</body>
</html>




