<%@page
    language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>loginInfo.jsp</title>
</head>

<body>
    <h1>/JSP/loginInfo.jsp</h1>
    <hr>

    <%
        // 현재의 웹브라우저가 로그인에 성공한 브라우저인지 아닌지 판단하고
        // 로그인에 성공한 웹브라우저라면, "로그아웃"이라는 링크를 생성

        // Step.1 Session Scope 에서, 성공로그인정보(userid, passwd) 획득
        //        만일 없으면, 리다이렉션을 이용하여,
        //        로그인 창으로 밀어냄(로그인 안된 웹브라우저이기 때문)

        String userid = (String) session.getAttribute("userid");
        String passwd = (String) session.getAttribute("passwd");

        if( userid != null && passwd != null ) {    // 로그인 성공정보가 유효하다면
    %>
            <h2><a href="/JSP/logout.jsp">로그아웃</a></h2>

    <%
        } else {    // 성공 로그인 정보가 없다면(즉, 아직 로그인되지 못한 웹브라우저라면)
            response.sendRedirect("/LoginForm.html");
            System.out.println(">>>>>>>>>>>>>>>> Re-directed <<<<<<<<<<<<<");
            
            return;
        } // if-else
    %>
</body>
</html>