<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>exam03</title>
</head>
<body>
    <h1>/JSTL/exam03.jsp</h1>
    <hr>

    <!-- ================================ -->

    <h1>JSTL Core 라이브러리 실습#1</h1>

    <hr>

    <!-- pageContext.setAttribute("myColor", "빨강"); -->
    <c:set var="myColor" value="빨강" scope="page"/>
    
    <c:if test="${ myColor == '빨강' }">
        <p>색상은 빨강색이다.</p>        
    </c:if>

    <!-- ================================ -->

    <h1>JSTL Core 라이브러리실습#2</h1>

    <hr>

    <!-- pageContext.setAttribute("grade", "70") -->
    <!-- grade 란 EL변수(=공유속성의 이름) 생성됨 -->
    <c:set var="grade" value="70" scope="page"/>

    <!-- 자바의 switch문 또는 SQL의 CASE~WHEN문장을 JSTL을 이용해 구현 -->
    <%-- 3개의 JSTL 태그를 이용해서 구현: <c:choose/>, <c:when/>, <c:otherwise/> --%>
    <c:choose>
        <c:when test="${ grade >= 90 }">
            학점은A 이다.
        </c:when>

        <c:when test="${ grade >= 80}">
            학점은B 이다.
        </c:when>

        <c:when test="${ grade >= 70}">
            학점은C 이다.
        </c:when>

        <c:otherwise>
            학점은F 이다.
        </c:otherwise>
    </c:choose>
</body>
</html>