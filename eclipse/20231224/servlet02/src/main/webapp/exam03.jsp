<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>exam03.jsp</title>
</head>
<body>
    <h1>JSTL Core 라이브러리 실습</h1>

    <hr>

    <c:set var="myColor" value="빨강" scope="page"/>
    
    <c:if test="${ myColor == '빨강' }">
        색상은 빨강색이다.
    </c:if>

    <h1>JSTL Core 라이브러리실습2</h1>

    <hr>

    <c:set var="grade" value="70" scope="page"/>

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