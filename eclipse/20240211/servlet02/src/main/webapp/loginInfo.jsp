<%@page
	session="true" 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginInfo.jsp</title>
</head>
<body>
    
    <h1>로그인 정보 보기</h1>
    
    <%
    	String __LOGIN__ = 
    		(String) session.getAttribute("__LOGIN__");
    
    	String passwd = 
   			(String) session.getAttribute("passwd");
    
    
    	if(__LOGIN__ == null) {	// 로그인하지 않았다면...
    		response.sendRedirect("/loginForm.html");
    	
    		return;
    	} else {
    %>
    		<h2>1. 아이디: <%= __LOGIN__ %></h2>
    		<h2>2. 암호: <%= passwd %></h2>
    		
    		<p></p>
    		
    		<a href='/logout.jsp'>로그아웃</a>
    
    <%  } // if-else %>
    
</body>
</html>



