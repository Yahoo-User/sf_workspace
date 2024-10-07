<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>initParam.jsp</title>
</head>
<body>
    <h1>/EL/initParam.jsp</h1>
    <hr>

	<h1>initParam 실습</h1>
	
	1. context 파라미터 jdbcDriver: ${ initParam.jdbcDriver }<br>
	2. context 파라미터 savePath  : ${ initParam.savePath }<br>

    <%
        String name = "Yoseph";
    %>

    3. name : <%= name %>
</body>
</html>