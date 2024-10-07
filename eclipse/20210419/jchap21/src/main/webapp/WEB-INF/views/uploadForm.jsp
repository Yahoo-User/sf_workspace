<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>uploadForm.jsp</title>
</head>
<body>

    <h1>/WEB-INF/views/uploadForm.jsp</h1>

    <hr>

    <!-- enctype="application/x-www-form-urlencoded" -->
        <!-- action="http://localhost:8007" -->
        <!-- action="uploadFilesByStandardServlet" -->
        <!-- action="uploadFilesBySpring" -->

    <form 
        action="uploadFilesByStandardServlet"
        method="POST"
        enctype="multipart/form-data" >

        Hobby : 
        <select name="hobby">
            <option value="movie">1. Movie</option>
            <option value="music">2. Music</option>
            <option value="book">3. Book</option>
        </select>

        <hr>

        <!-- For action="uploadFilesByStandardServlet" -->
        <input type="file" name="uploadFile1"><br>
        <input type="file" name="uploadFile2" multiple><br>
        
        
        <!-- For action="uploadFilesBySpring" -->
        <!-- <input type="file" name="uploadFiles" multiple><br>
        <input type="file" name="uploadFiles" multiple><br> -->
    
        <p></p>

        <button type="submit">Upload</button>
        <button type="reset">Cancel</button>
    </form>

</body>
</html>