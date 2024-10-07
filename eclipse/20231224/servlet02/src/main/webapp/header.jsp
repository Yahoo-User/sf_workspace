<%@page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" %>
   
<%@page import="java.util.Calendar" %>  

<%
	String httpMethod = request.getMethod();	// GET / POST

	String nickName = request.getParameter("nickName");

	Calendar cal = Calendar.getInstance();

	int hour 	= cal.get(Calendar.HOUR_OF_DAY);
	int minute 	= cal.get(Calendar.MINUTE);
	int second 	= cal.get(Calendar.SECOND);
	
	

	
%>


<h1>현재시간은 <%= hour %>시 <%= minute %>분 <%= second %>초 입니다.</h1>

<h1>닉네임은 <%= nickName %>입니다</h1>

<h2>HTTP Method: <%= httpMethod %></h2>