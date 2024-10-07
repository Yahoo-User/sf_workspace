<%@page
   language="java"
   contentType="text/html; charset=utf8" 
   pageEncoding="utf8" 
   import="java.util.Date" %>

<!DOCTYPE html>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>expression.jsp</title>
</head>
<body>

    <%
        String name = "Yoseph";
        int age = 23;        
    %>

    <h1>1. name: <%= name %></h1>
    <h1>2. age:  <%= age %></h1>
    <h1>3. nationality:  <%= "대한민국" %></h1>
    
</body>
</html>