<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test.jsp</title>
</head>
<body>

    <h1>1 ~ 10까지 출력하기</h1>

    <%  // Scriptlet tag: 자바코드를 넣을 수 있다!

    	// 왜? 필드를 선언하려면 선언태그가 필요
    	// scriptlet tag 안에 모든 자바 실행문장은
    	// servlet 의 service() 메소드 안에서 수행되는 문장!!!
        int age = 15;	// 필드가 아니라!, 지역변수이다!!
        boolean isMale = true;	// 남자
    
    	if(age < 18) {
    		// out: 내장객체(미리 생성되서 바로 사용가능한 객체)
    		out.println("미성년자");
    	} // if
	
    %>

</body>
</html>