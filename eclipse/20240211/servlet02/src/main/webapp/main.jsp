<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
</head>
<body>
	
	<h1>include 지시어 태그 실습</h1>
	
	<p>현재시간을 구하는 예제입니다. 다음줄에 삽입이 됩니다.</p> <br>
	
	<jsp:include page="/header.jsp" flush="true">
	
		<jsp:param name="nickName" value="한글" />
		
	</jsp:include>
	
</body>
</html>