<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>out.jsp</title>
</head>
<body>

	<%
	
		String name = "홍길동";
		
		out.println("나의 이름은 " + name + " 입니다.");	// 1st.
	%>

	<hr>
	
	너의 이름은 <%= name %> 입니다.
</body>
</html>