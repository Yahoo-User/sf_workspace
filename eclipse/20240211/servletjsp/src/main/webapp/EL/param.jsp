<%@page import="java.util.Arrays" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>param built-in object</title>
</head>

<body>
    <h1>/EL/param.jsp</h1>
    <hr>

    <h2>1. name: ${param.name}</h2>
    <h2>2. age: ${param.age}</h2>
    <h2>3. hobby: ${param.hobby}</h2>
    <h2>4. hobby: ${paramValues.hobby[0]}, ${paramValues.hobby[1]}, ${paramValues.hobby[2]}</h2>
    <h2>5. hobbies: ${ Arrays.toString( paramValues.hobby ) }</h2>
</body>

</html>