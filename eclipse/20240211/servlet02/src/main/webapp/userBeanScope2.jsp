<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean
	id="myBean" 
	class="org.zerock.myapp.bean.LoginBean"
 	scope="session" />
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userBeanScope2.jsp</title>
</head>
<body>
	
	<h1>useBean 사용실습</h1>
	
	1. userid: <%= myBean.getUserid() %><br>
	2. passwd: <%= myBean.getPasswd() %>
	
</body>
</html>