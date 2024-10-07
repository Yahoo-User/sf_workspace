<%@page
	import="org.zerock.myapp.domain.LoginBean"
	import="java.util.List"
	import="java.util.ArrayList" %>    
        
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>set.jsp</title>
</head>
<body>
    <h1>/EL/set.jsp</h1>
    <hr>

    <h1>List 실습</h1>

    <hr>

    <%
        LoginBean oneBean = new LoginBean();
        oneBean.setUserid("aaa");
        oneBean.setPasswd("1234");

        LoginBean twoBean = new LoginBean();
        twoBean.setUserid("bbb");
        twoBean.setPasswd("2222");

        // 리스트 객체를 만들고, 2개의 자바빈즈(LoginBean) 객체를 요소로 추가
        List<LoginBean> list = new ArrayList<>();
        list.add(oneBean);
        list.add(twoBean);

        // Request Scope 에 리스트 객체를 속성으로 바인딩
        request.setAttribute("__MODEL__", list );
    %>

    <!-- 추가적인 전송파라미터 함께 전송 -->
    <jsp:forward page="get.jsp">
        <jsp:param name="name" value="Yoseph" />
        <jsp:param name="age" value="23" />
    </jsp:forward>
</body>
</html>