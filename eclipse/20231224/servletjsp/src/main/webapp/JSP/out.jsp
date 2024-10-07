<%@page
    language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>out</title>
</head>
<body>
    <h1>out.jsp</h1>
    <hr>

    <%
        String name = "홍길동";

        out.println( String.format("<p>이것은out 내장객체로출력: %s</p>", name) );
    %>

    <p>이것은expression tag로 출력: <%= name %></p>
    
</body>
</html>