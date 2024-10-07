<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>scope</title>
</head>

<body>
    <h1>/EL/other.jsp</h1>
    <hr>

    <%-- ${ EL각SCOPE별내장객체명.공유속성명 }을 통한, 공유객체의 출력 --%>
    <h1>1. pageScope의 속성값은: ${ pageScope.NAME_1 }</h1>
    <h1>2. requestScope 속성값은: ${ requestScope.NAME_2 }</h1>
    <h1>3. sessionScope 속성값은: ${ sessionScope.NAME_3 }</h1>
    <h1>4. applicationScope 속성값은: ${ applicationScope.NAME_4 }</h1>
</body>

</html>