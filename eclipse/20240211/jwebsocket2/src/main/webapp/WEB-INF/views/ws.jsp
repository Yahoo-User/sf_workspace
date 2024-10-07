<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>


<!DOCTYPE html>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>ws.jsp</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.3.2/jquery-migrate.min.js"></script>

    <script>

        $(function () {
            console.clear();
            console.group("jquery started.");

            var ws = null;
            var messageCount = 0;

            var unorderedList   = document.querySelector('#unorderedList');
            var message         = document.querySelector('#message')


            $('#connect').click(function (e) {
                console.debug('connect button clicked.');

                ws = new WebSocket("ws://desktop-hdb0crd:8090/echo");
                console.log('+ ws:', ws)
                
                //--------------------------------------------------//
                ws.onclose = e => {
                    console.log('ws.onclose(e) invoked.');
                    console.log('\t+ e:', e);

                    var newLI = document.createElement("li");
                    newLI.innerHTML = 'DISCONNECTED [ code: ' + e.code +', returnValue: '+ e.returnValue + ']';

                    unorderedList.append(newLI);
                }; // onclose

                ws.onerror = e => {
                    console.log('ws.onerror(e) invoked.');
                    console.log('\t+ e:', e);

                }; // onerror
                
                ws.onmessage = e => {
                    console.log('ws.onmessage(e) invoked.');
                    console.log('\t+ e:', e);

                    var newLI = document.createElement("li");
                    newLI.innerHTML = e.data + ' #'+ (messageCount++);

                    unorderedList.append(newLI);
                };  //onmessage

                ws.onopen = e => {
                    console.log('ws.onopen(e) invoked.');
                    console.log('\t+ e:', e);

                };  // onopen
                //--------------------------------------------------//

            }); // connect


            $('#disconnect').click(function (e) {
                console.debug('disconnect button clicked.');
                
                if(ws) {
                    ws.close();
                    ws = null;

                    console.log('\t+ WebSocket Closed.');
                } // if
            }); // disconnect

            
            $('#send').click(function (e) {
                console.debug('send button clicked.');
                
                if(ws && message.value) {
                    ws.send(message.value);

                    console.log(`${message.value} sent.`);
                } // if
            }); // send


            console.groupEnd()
        });  // jq

    </script>

    <style>

        button {
            width: 100px;
            height: 50px;
        }

    </style>
</head>
<body>
    
    <h1>/WEB-INF/views/ws.jsp</h1>
    
    <hr>

    <button type="button" id="connect">Connect</button>
    <button type="button" id="send">Send</button>
    <button type="button" id="disconnect">Disconnect</button>

    <p></p>

    Message: <input type="text"id="message">

    <hr>

    <ul id="unorderedList">

    </ul>

</body>
</html>