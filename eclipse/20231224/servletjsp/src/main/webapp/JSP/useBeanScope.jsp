<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    

<jsp:useBean 
	id="myBean"
	class="org.zerock.myapp.domain.LoginBean"
 	scope="session" />
     
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>userBeanScope.jsp</title>
</head>

<body>

    <h1>/JSP/useBeanScope.jsp</h1>
    <hr>
	
	<h1>useBean 사용실습</h1>
	
	<%
		myBean.setUserid("Yoseph");
		myBean.setPasswd("1234");
	%>
	
	1. userid: <%= myBean.getUserid() %><br>
	2. passwd: <%= myBean.getPasswd() %>
	
    <p>&nbsp;</p>
    <h3>myBean: <%= myBean %></h3>
</body>
</html>