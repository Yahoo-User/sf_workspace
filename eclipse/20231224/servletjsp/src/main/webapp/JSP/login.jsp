<%
    // Step.1
    request.setCharacterEncoding("utf8");

    String userid = request.getParameter("userid");
    String passwd = request.getParameter("passwd");

    // Step.1 - 로그인 처리
    // 무조건 성공이라 가정 (단, 아이디와 암호가 정상 수신된 경우에만)
    if( ( userid != null && !"".equals(userid) ) && 
        ( passwd != null && !"".equals(passwd) ) ) {    // if Normal
        ;;
    } else {    // if Abnormal
        response.sendRedirect("/LoginForm.html");
        System.out.println(">>>>>>>>>>>>>>>>> RE-DIRECTED <<<<<<<<<<<<<<<");

        return; // _jspService() 메소드의 실행을 여기서 종료시킴!
    } // if-else

    // Step.3 - 과연 무엇을 해야 하는가!?
    // 현재 로그인 한 웹브라우저와 생명주기가 동일한, Session Scope에
    // "Login Succeed" 정보를 올려 놓아야 함.

    session.setAttribute("userid", userid);
    session.setAttribute("passwd", passwd);
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>login</title>
</head>

<body>
    <h1>/JSP/login.jsp</h1>
    <hr>

    <ul>
        <li>1. 안녕하세요 to <%= userid %></li>
        <li><a href='/JSP/loginInfo.jsp'>로그인 정보 보기</a></li>
    </ul>

</body>
</html>