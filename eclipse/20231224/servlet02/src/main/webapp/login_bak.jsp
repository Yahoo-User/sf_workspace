<%@page
	session="true"
	language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
</head>
<body>
	<h1>로그인 정보 세션 저장</h1>
	
	<%
		// 전송 파라미터 획득
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		if(userid == null || passwd == null) {
			// 아이디 또는 암호를 넣지 않았으면, 다시 로그인창으로 이동
			response.sendRedirect("/loginForm.html");			
		} else {
			// 아아디/암호의 유효성확인
			
			// Session Scope 공유영역에 로그인 정보를 저장
			session.setAttribute("__LOGIN__", userid);
			session.setAttribute("passwd", passwd);
		} // if-else
	%>
	
	<h2>안녕하세요: <%= userid %></h2>
	<h2><a href='/loginInfo.jsp'>정보보기</a></h2>
</body>
</html>





