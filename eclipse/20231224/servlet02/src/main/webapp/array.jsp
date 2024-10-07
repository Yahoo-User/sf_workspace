<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.Arrays" %>    


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>array.jsp</title>
</head>
<body>
	<h1>공유영역에 바인딩된 배열의 원소를 출력</h1>
	
	<%
		int[] intArr = { 1,2,3,4,5 };
	
		// 배열객체를 Request Scope에 속성으로 바인딩 시킴
		request.setAttribute("__MODEL__", intArr);
	%>
	
	<hr>
	
	1. 배열의 첫번째 원소: ${ __MODEL__[0] } <br>
	2. 배열의 첫번째 원소: ${ __MODEL__[1] } <br>
	3. 배열의 첫번째 원소: ${ __MODEL__[2] } <br>
	4. 배열의 첫번째 원소: ${ __MODEL__[3] } <br>
	5. 배열의 첫번째 원소: ${ __MODEL__[4] } <br>
	
	<hr>
	
	* 배열전체출력: ${ Arrays.toString(__MODEL__) }
	
</body>
</html>