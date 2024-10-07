<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page
	import="java.util.HashMap"
	import="java.util.Map" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Map.jsp</title>
</head>
<body>

	<h1>공유영역의 Map 객체 속성의 출력</h1>
	
	<%
		Map<String, Integer> map = new HashMap<>();
		map.put("KEY_1", 1);
		map.put("KEY_2", 2);
		map.put("KEY_3", 3);
		
		session.setAttribute("__MODEL__", map);
	%>
	
	<hr>
	
	1. 첫번째 키의 값: ${ __MODEL__["KEY_1"] } <br>
	2. 두번째 키의 값: ${ __MODEL__["KEY_2"] } <br>
	3. 세번째 키의 값: ${ __MODEL__["KEY_3"] } <br>

	<hr>
	
	1. 첫번째 키의 값: ${ __MODEL__.KEY_1 } <br>
	2. 두번째 키의 값: ${ __MODEL__.KEY_2 } <br>
	3. 세번째 키의 값: ${ __MODEL__.KEY_3 } <br>
</body>
</html>