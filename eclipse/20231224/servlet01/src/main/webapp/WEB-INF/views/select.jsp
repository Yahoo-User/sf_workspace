<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>select.jsp</title>
</head>
<body>
	<!-- <h1>${MODEL}</h1> -->

    <h1>사원정보(사번:${MODEL.empno})</h1>

    <hr>

    <h3>1. 사원명: ${MODEL.ename}</h3>
    <h3>2. 직무: ${MODEL.job}</h3>
    <h3>3. 관리자: ${MODEL.mgr}</h3>
    <h3>4. 입사일자: ${MODEL.hireDate}</h3>
    <h3>5. 급여: ${MODEL.sal}</h3>
    <h3>6. 커미션: ${MODEL.comm}</h3>
    <h3>7. 소속부서: ${MODEL.deptno}</h3>
</body>
</html>