<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>forward.jsp</title>
</head>

<body>
    <h1>/JSP/forward.jsp</h1>
    <hr>

	<h1>/JSP/current.jsp에서 포워드된 화면</h1>
	
	<%
		String httpMethod = request.getMethod();            // HTTP method
	
        // 직접 사용자가 입력하여 전송된 파라미터 획득
		String data = request.getParameter("data");

        // current.jsp에서 준 전송 파라미터 획득
		String nickName = request.getParameter("nickName");
	%>

	<h1>포워드 되었으며 넘어온 파라미터값은 <%= nickName %> 입니다.</h1> 
	<h1>직접 입력시킨 파라미터값은 <%= data %> 입니다.</h1>	
	<h1>HTTP method: <%= httpMethod %></h1>	
</body>
</html>