<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<jsp:useBean
	id="myBean" 
	class="org.zerock.myapp.domain.LoginBean"
 	scope="session" />
     
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>userBeanScope2</title>
</head>
<body>
    <h1>/JSP/useBeanScope2.jsp</h1>
    <hr>

	<h1>useBean 사용실습</h1>
	
	1. userid: <%= myBean.getUserid() %><br>
	2. passwd: <%= myBean.getPasswd() %>

    <p>&nbsp;</p>
    <h3>myBean: <%= myBean %></h3>	
</body>
</html>