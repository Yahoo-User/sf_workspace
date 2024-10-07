<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    

<jsp:useBean id="myBean" class="org.zerock.myapp.domain.LoginBean" scope="page"/>  	
<jsp:setProperty name="myBean" property="*" />


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>useBeanAll</title>
</head>
<body>
	<h1>/JSP/useBeanAll.jsp</h1>
    <hr>

	<h1>param 속성을 통한 전송파라미터 자동수집 실습#2</h1>
	
	1. userid: <jsp:getProperty name="myBean" property="userid" /><br>
	2. passwd: <jsp:getProperty name="myBean" property="passwd" />

</body>
</html>