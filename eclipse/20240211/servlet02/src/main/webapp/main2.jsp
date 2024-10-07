<%@page 
    language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
</head>
<body>
    <h1>include 지시어(directive) 태그(tag) 실습</h1>

    <p>내용수정중입니다. 다음줄에 삽입이 됩니다.</p><br>

    <%-- 주석태그(comment tag): 정적삽입(static insert) --%>
    <%@include file="copyright.jsp" %>
</body>
</html>