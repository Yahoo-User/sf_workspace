<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>declare.jsp</title>
</head>
<body>
    
    <h1>Declaration 태그를이용한 JSP LifeCycle 메소드</h1>

    <%!
        // 변환될 클래스 소스파일에 필드 선언
        String initMesg = "jspInit 메소드";
        String delMesg = "jspDestroy 메소드";
        
        // 변환될 클래스 소스파일에 메소드 선언
        //JSP LifeCycle(서블릿과 비슷하게) 메소드

        public void jspInit(){
            System.out.println(">>>>> "+initMesg+" <<<<<");
        } // jspInit
        
        public void jspDestroy(){
            System.out.println(">>>>> "+delMesg+" <<<<<");
        } // jspDestroy
    %>

</body>
</html>