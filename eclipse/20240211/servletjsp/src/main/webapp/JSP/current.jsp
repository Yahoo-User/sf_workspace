<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>current.jsp</title>
</head>

<body>
    <!-- 현 JSP에서는 아래 태그를 응답문서 Body(=Payload)에 출력함 -->
    <h1>/JSP/current.jsp</h1>
    <hr>

    <%  for(int i=0; i<500; i++) { %>
    
            <h4>/JSP/current.jsp</h4>
            
    <%  } // for %>

	<jsp:forward page="/JSP/forward.jsp">
		<jsp:param name="nickName" value="한글" />		
	</jsp:forward>
</body>
</html>