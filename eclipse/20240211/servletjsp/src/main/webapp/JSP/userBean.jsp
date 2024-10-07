<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>userBean</title>
</head>
<body>
    <h1>/JSP/userBean.jsp</h1>
    <hr>

    <!-- 우선 자바빈즈 객체부터 생성해서, 기본 공유영역에 바인딩 수행 -->
    <jsp:useBean
        id="myBean"
        class="org.zerock.myapp.domain.LoginBean"
        scope="page" />

    <h1>useBean 액션태그의 사용실습</h1>

    <%  // 자바빈즈 객체의 프로퍼티(속성)에 값을 설정
        myBean.setUserid("Yoseph");
        myBean.setPasswd("1234");
    %>

    <!-- 자바빈즈 객체의 프로퍼티(속성)를 출력 -->
    <h3>1. userid: <%= myBean.getUserid() %></h3>
    <h3>2. passwd: <%= myBean.getPasswd() %></h3>

</body>
</html>