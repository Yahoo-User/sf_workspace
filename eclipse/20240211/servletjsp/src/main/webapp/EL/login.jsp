<%@page import="org.zerock.myapp.domain.LoginBean" %>

<!DOCTYPE html>
<html lang="ko">

<jsp:useBean id="myBean" class="org.zerock.myapp.domain.LoginBean" scope="page" />
<jsp:setProperty name="myBean" property="*" />

<% System.out.println(myBean); %>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>login</title>
</head>

<body>
    <h1>/EL/login.jsp</h1>
    <hr>

    <%
        LoginBean loginBean = (LoginBean) pageContext.getAttribute("myBean");
        session.setAttribute("__YOSEPH__", loginBean);
    %>

    <!-- JSP scripting element를 이용한 값의 출력 -->
    <h3>1. userid: <%= loginBean.getUserid() %></h3>
    <h3>1. passwd: <%= loginBean.getPasswd() %></h3>

    <!-- useBean의 id값인 "myBean"이란 이름의 참조변수를 이용한 값의 출력 -->
    <h3>2. userid: <%= myBean.getUserid() %></h3>
    <h3>3. userid: <%= myBean.getPasswd() %></h3>

    <!-- JSP action tag를 이용한 값의 출력 -->
    <h3>3. userid: <jsp:getProperty name="myBean" property="userid" /></h3>
    <h3>3. passwd: <jsp:getProperty name="myBean" property="passwd" /></h3>

    <!-- JSP EL를 이용한 값의 출력 -->
    <h3>4. __YOSEPH__: ${ __YOSEPH__ }</h3>
    <h3>4. myBean: ${ myBean }</h3>

    <!-- JSP EL 표현식을 이용한 자바빈즈객체의 프로퍼트 출력 -->
    <%-- 만일 ${ 자바빈즈객체.프로퍼티명 } 한다면,
         이는 ${ 자바빈즈객체.get프로퍼티명() } 과 같다. --%>
    <h3>5. userid: ${ __YOSEPH__.userid }</h3>
    <h3>5. passwd: ${ __YOSEPH__.getPasswd() }</h3>

    <!-- EL표현식에 지정한 공유속성이, 4개의 공유scope에서 발견되지 않으면.. -->
    <!-- 공유속성이 올바르지 않으면, 곧바로 예외가 발생(***) -->
    <!-- <h3>6. __YOSEPH2__: ${ __YOSEPH2__ }</h3> -->

    <!-- EL표현식의 내장객체를 이용한 객체의 출력 -->
    <h3>7. session scope __YOSEPH__: ${ sessionScope.__YOSEPH__ }</h3>
    <h3>7. session scope __YOSEPH__.userid : ${ sessionScope.__YOSEPH__.userid }</h3>
</body>

</html>