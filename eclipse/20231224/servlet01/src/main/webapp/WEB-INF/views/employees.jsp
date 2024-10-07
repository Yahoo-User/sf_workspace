<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>사원목록</title>

<style>

    #wrapper {
        width: 90%;
    }

    table {
        width: 95%;

        border-collapse: collapse;
        border: 1px ridge gray;
    }

</style>

</head>

<body>

    <!-- ${employees}	// EL : Expression Language -->

    <div id="wrapper">

        <table border="1">
            <caption>사원목록</caption>

            <thead>
                <tr>
                    <th>empno</th>
                    <th>ename</th>
                    <th>job</th>
                    <th>mgr</th>
                    <th>hireDate</th>
                    <th>sal</th>
                    <th>comm</th>
                    <th>deptno</th>
                </tr>
            </thead>

            <tbody>
                <!-- 공유데이터에 있는 리스트 객체의 요소개수만큼
                반복을 돌리면서, 각 EmpVO 객체를 이용해서
                테이블의 각 행을 만들어 내야 합니다. -->

                <c:forEach items="${EMPLOYEES}" var="empVO">
                    <tr>
                        <td>${empVO.getEmpno()}</td>
                        <td>${empVO.getEname()}</td>
                        <td>${empVO.getJob()}</td>
                        <td>${empVO.getMgr()}</td>
                        <td>${empVO.getHireDate()}</td>
                        <td>${empVO.getSal()}</td>
                        <td>${empVO.getComm()}</td>
                        <td>${empVO.getDeptno()}</td>
                    </tr>
                </c:forEach>

            </tbody>

            <tfoot/>

        </table>

    </div>

</body>
</html>