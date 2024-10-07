<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>uploadAjax.jsp</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.3.2/jquery-migrate.min.js"></script>

    <script>

        $(function () {
            console.clear();
            console.log('Started.....');


            $('#uploadButton').on('click', function (e) {
                console.log('>>> uploadButton clicked.');

                //-----------------------------------------------//
                
                var inputElement = $('#uploadFiles');
                console.log('\t+ inputElement:', inputElement);

                //-----------------------------------------------//

                console.log('\t+ FormData:', FormData);

                //-----------------------------------------------//

                var files = inputElement[0].files;                //***//

                console.log('\t+ files:', files);
                console.log('\t\t>> typeof:', typeof files, ', instanceof FileList:', files instanceof FileList);

                var formData = new FormData();                          //***//

                var index = 0;
                for(var f of files) {
                    // console.log(f instanceof File)      // true

                    console.log('\t\t(', ++index, '). ', f.name, f.size, f.type, f.lastModified, f.lastModifiedDate);

                    formData.append('uploadFiles', f);                  //***//
                } // for-of

                console.log('\t+ formData:', formData);

                //-----------------------------------------------//

                $.ajax({
                    url: '/uploadFilesByAjax1',             // OK   : Servlet 3.0 and above standard file upload.
                    // url: '/uploadFilesByAjax2',             // OK   : Spring MVC file upload supporting.

                    processData: false,
                    contentType: false,

                    data: formData,
                    type: 'POST',

                    success: function (result) {
                        console.log('>>> success.');

                        alert(result);
                    },

                    error: function (err) {
                        console.log('>>> error.');

                        alert(err);
                    }
                }); // ajax

            }); // onclick
            
        }); // jq

    </script>
</head>
<body>
    <h1>/WEB-INF/views/uploadAjax.jsp</h1>

    <hr>

    <div>
        <input type="file" name="uploadFiles" id="uploadFiles" multiple>

        <p>&nbsp;</p>

        <button type="submit" id="uploadButton">Upload</button>
    </div>
</body>
</html>