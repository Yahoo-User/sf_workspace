<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.zerock.myapp.domain.SampleDTO" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>sample.jsp</title>
</head>
<body>
	<h1><%= request.getRequestURI() %></h1>
	
	<p>name: ${sampleDTO.name}</p>
	<p>age: ${sampleDTO.age}</p>
</body>
</html>