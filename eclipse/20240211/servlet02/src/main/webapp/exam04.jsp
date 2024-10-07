<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>exam04.jsp</title>
</head>
<body>

    <h1>JSTL Core 라이브러리 실습</h1>

    <hr>

    <%  // scriptlet tag

        int [] num = { 1 ,2 , 3 ,4, 5 , 6 , 7, 8, 9, 10 };

        // request scope 에 배열객체 바인딩
        request.setAttribute("myArray", num);

    %>

    <c:forEach var="element" items="${myArray}">

        <!-- <c:out value="${element}" /> -->

        ${element} <br>

    </c:forEach>


	<h1>JSTL Core 라이브러리실습2</h1>
	
	<hr>
	
	<%
		java.util.List<String> list = 
			new java.util.ArrayList<>();
	
		list.add("홍길동");
		list.add("이순신");
		list.add("유관순");
		
		request.setAttribute("__NAME__", list);
	%>
	
	<c:forEach var="name" items="${__NAME__}">
	
		${name} <br>
	
	</c:forEach>
	
</body>
</html>