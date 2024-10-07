<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="myBean" class="org.zerock.myapp.domain.LoginBean" scope="page"/>
<jsp:setProperty name="myBean" property="userid" value="Yoseph"/>	
<jsp:setProperty name="myBean" property="passwd" value="1234"/>	 
   
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>useBeanSet.jsp </title>
</head>
<body>
    <h1>/JSP/useBeanSet.jsp</h1>
    <hr>

	<h1>useBean 사용실습</h1>
	
	1. userid: <%= myBean.getUserid() %><br>
	2. passwd: <%= myBean.getPasswd() %>

</body>
</html>