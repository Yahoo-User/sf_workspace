<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<jsp:useBean
	id="myBean"
	class="org.zerock.myapp.bean.LoginBean" 
	scope="page"/>
	
<jsp:setProperty 
	name="myBean" 
	property="userid" 
	value="Yoseph"/>
	
<jsp:setProperty 
	name="myBean" 
	property="passwd" 
	value="1234"/>	
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>useBeanGet.jsp</title>
</head>
<body>
	
	<h1>자바빈즈 객체의 프로퍼티 출력 실습</h1>
	
	1. userid: <jsp:getProperty name="myBean" property="userid" />
	   <br>
	2. passwd: <jsp:getProperty name="myBean" property="passwd" />


</body>
</html>