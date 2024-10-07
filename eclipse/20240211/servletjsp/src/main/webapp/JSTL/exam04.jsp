<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>exam04.jsp</title>
</head>
<body>
	<h1>/JSTL/exam04.jsp</h1>
	<hr>

	<!-- ====================================== -->
    <h1>JSTL Core 라이브러리 실습#1</h1>

    <hr>

    <%  // scriptlet tag

        int [] num = { 1 ,2 , 3 ,4, 5 , 6 , 7, 8, 9, 10 };

        // request scope 에 배열객체 바인딩
        request.setAttribute("myArray", num);

    %>

	<!-- 한글테스트2 -->
    <c:forEach var="element" items="${myArray}">
        <c:out value="${element}" />
    </c:forEach>

	<!-- ====================================== -->
	<h1>JSTL Core 라이브러리실습#2</h1>
	
	<hr>
	
	<%
		// page directive 에 import 속성으로 List의 타입명을 기재하지 않을거면,
		// 아래와 같이 개발자가 직접 FQCN으로 객체를 생성해도 됨
		java.util.List<String> list = new java.util.ArrayList<>();	// @since 8
	
		list.add("홍길동");
		list.add("이순신");
		list.add("유관순");
		
		// Request Scope 에 공유객체로 list 객체를 바인딩함
		// 이때, 공유속성의 이름은 EL변수명이 된다!
		request.setAttribute("__NAME__", list);
	%>
	
	<c:forEach var="name" items="${__NAME__}">	
		${name} <br>	
	</c:forEach>

	<!-- ====================================== -->
	<h1>JSTL Core 라이브러리실습#3</h1>
	
	<hr>

	<ul>
		<c:forEach var="counter" begin="0" end="9" step="1">
			<li>${counter}</li>
		</c:forEach>	
	</ul>

	<!-- ====================================== -->
	<h1>JSTL Core 라이브러리실습#4</h1>
	
	<hr>

	<%
		// CSV : Comma(,) Separated Value
		String str = "A,B,C,D";	// 쉼표(,)로 구분되어 있는 문자열(CSV형식의 문자열)

		// request scope에 바인딩: "data"란 이름이, EL변수명이 된다!
		request.setAttribute("data", str);
	%>

	<c:forTokens var="token" items="${ data }" delims="," >
		<c:out value="${token}" /><br>
	</c:forTokens>
</body>
</html>