<%@page import="java.util.Date" %>
<%
	response.setContentType("text/event-stream;charset=utf-8");

	Date now = new Date();
%>
data:<%= now %>