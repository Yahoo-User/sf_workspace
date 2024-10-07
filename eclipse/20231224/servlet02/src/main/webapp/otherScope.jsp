<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>otherScope.jsp</title>
</head>
<body>
	
	<h1>scope 데이터 보기</h1>
	
	1. pageScope 		속성값: ${ pageScope.NAME_1 }<br>
	2. requestScope 	속성값: ${ requestScope.NAME_2 }<br>
	3. sessionScope 	속성값: ${ sessionScope.NAME_3 }<br>
	4. applicationScope 속성값: ${ applicationScope.NAME_4 }<br>
	
	<hr>
	
	1. pageScope 		속성값: ${ NAME_1 }<br>
	2. requestScope 	속성값: ${ NAME_2 }<br>
	3. sessionScope 	속성값: ${ NAME_3 }<br>
	4. applicationScope 속성값: ${ NAME_4 }<br>
	
	
</body>
</html>