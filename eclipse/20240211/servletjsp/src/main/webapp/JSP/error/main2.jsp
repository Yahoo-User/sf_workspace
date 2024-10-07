<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
</head>
<body>
	<h1>/JSP/main2.jsp</h1>
    <hr>

	<h1>include 지시어 태그 실습</h1>
	
	<p>현재시간을 구하는 예제입니다. 다음줄에 삽입이 됩니다.</p> <br>
	
    <!-- target 인 header.jsp를 include 할 때, header.jsp 에 필요한 파라미터를
    전달하는 용도로 param 액션태그를 사용해야 함!!! -->
	<jsp:include page="/JSP/header.jsp" flush="true">
		<jsp:param name="nickName" value="한글" />
	</jsp:include>
	
</body>
</html>