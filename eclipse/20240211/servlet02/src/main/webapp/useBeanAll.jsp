<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<jsp:useBean
	id="myBean"
	class="org.zerock.myapp.bean.LoginBean"
	scope="page"/>

<jsp:setProperty name="myBean" property="*" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>useBeanAll.jsp</title>
</head>
<body>
	
	<h1>param 속성을 통한 전송파라미터 자동수집 실습</h1>
	
	1. userid: <%= myBean.getUserid() %><br>
	2. passwd: <%= myBean.getPasswd() %>


</body>
</html>