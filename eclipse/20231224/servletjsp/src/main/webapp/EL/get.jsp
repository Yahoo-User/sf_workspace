<%@page isELIgnored="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>get.jsp</title>
</head>
<body>
    <h1>/EL/get.jsp</h1>
    <hr>
    
	<h1>List 실습</h1>
	
	1. 1st. LoginBean: ${ __MODEL__[0].userid }, ${ __MODEL__[0].passwd } <br>
	2. 2nd. LoginBean: ${ __MODEL__[1].userid }, ${ __MODEL__[1].passwd } <br>
    3. name : ${ param.name } <br>
    4. age  : ${ param.age }
</body>
</html>