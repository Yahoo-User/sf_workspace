<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>exam05</title>
</head>

<body>
    <h1>/JSTL/exam05.jsp</h1>
    <hr>

    <!-- request.setCharacterEncoding("utf8"); -->
    <!-- 위의 코드는 반드시 전송파라미터의 값을 얻기전에 해야 함 -->
    <fmt:requestEncoding value="utf8" />

    1. name: <%= request.getParameter("name") %> <br>
    2. age : <%= request.getParameter("age") %>

    <p></p>

    3. name: ${ param.name } <br>
    4. age : ${ param.age }

</body>
</html>