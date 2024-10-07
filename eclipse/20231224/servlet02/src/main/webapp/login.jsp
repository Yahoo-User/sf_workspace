<%@page
	session="true"
	language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<jsp:useBean 
	id="myBean" 
	class="org.zerock.myapp.bean.LoginBean"
	scope="page"/>
	
<jsp:setProperty name="myBean" property="*" />	
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
</head>
<body>
	
	<h1>EL 실습</h1>

	<h2>1. 아이디 : ${myBean.userid}</h2>
	<h2>2. 비밀번호: ${myBean.passwd}</h2>
	
	
</body>
</html>





