<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dateformat.jsp</title>
</head>
<body>

	<%
		java.util.Date now = new java.util.Date();
	%>
	
	<c:set var="now" value="<%= now %>" scope="session"/>
	
	1. 현재의 날짜와 시간: 
		<fmt:formatDate
			value="${now}" 
			pattern="yyyy-MM-dd HH:mm:ss.S"/>
	
	
</body>
</html>