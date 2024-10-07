<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>page.jsp</title>
</head>
<body>
	<h1>/WEB-INF/views/fileupload/page.jsp</h1>
	
	<form action="/fileupload/doit" method="POST" enctype="multipart/form-data">
        <div><input type="file" name="files"></div>
        <div><input type="file" name="files"></div>
        <div><input type="file" name="files"></div>
        <div><input type="file" name="files"></div>
        <div><input type="file" name="files"></div>
        <div><input type="submit" value="Upload"></div>
    </form>
</body>
</html>