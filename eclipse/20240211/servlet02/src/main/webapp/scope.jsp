<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>scope.jsp</title>
</head>
<body>
    <h1>4개의 공유영역(scope) 실습</h1>

    <%
        // 4개의 각각의 공유영역에 속성 바인딩 예제
        pageContext.setAttribute("NAME_1", "VALUE_1");  // page
        request.setAttribute("NAME_2", "VALUE_2");   // request
        session.setAttribute("NAME_3", "VALUE_3");   // session
        application.setAttribute("NAME_4", "VALUE_4");   // application
    %>

    <h1>1. pageScope의 속성값은: ${ pageScope.NAME_1 }</h1>
    <h1>2. requestScope 속성값은: ${ requestScope.NAME_2 }</h1>
    <h1>3. sessionScope 속성값은: ${ sessionScope.NAME_3 }</h1>
    <h1>4. applicationScope 속성값은: ${ applicationScope.NAME_4 }</h1>
</body>
</html>