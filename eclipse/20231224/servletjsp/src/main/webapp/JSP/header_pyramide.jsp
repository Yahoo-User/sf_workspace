<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>   
<%@page import="java.util.Calendar" %>  

<%
	String httpMethod = request.getMethod();	    // HTTP method 획득
	String nickName = request.getParameter("nickName");

	Calendar cal = Calendar.getInstance();          // 달력객체 얻기
	int hour 	= cal.get(Calendar.HOUR_OF_DAY);    // 달력에서, 현재 시간 획득
	int minute 	= cal.get(Calendar.MINUTE);         // 달력에서, 현재 분 획득
	int second 	= cal.get(Calendar.SECOND);	        // 달력에서, 현재 초 획득
%>


<h1>현재시간은 <%= hour %>시 <%= minute %>분 <%= second %>초 입니다.</h1>
<h1>닉네임은 <%= nickName %>입니다</h1>
<h2>HTTP Method: <%= httpMethod %></h2>