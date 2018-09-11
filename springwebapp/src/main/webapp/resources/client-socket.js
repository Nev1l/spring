            var registrationRequest = { "action": "login", "value": { "accessToken": "acc1", "tokenType": "tt1" } }

            var webSocket;

            var openSocket = function (){
                // Ensures only one connection is open at a time
                if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
                   writeResponse("WebSocket is already opened.");
                    return;
                }
                // Create a new instance of the websocket
                //ws://localhost:8080/action
                //ws://localhost:8080/spring-webapp/action
                webSocket = new WebSocket("ws://localhost:8080/spring-webapp/action");

                /**
                 * Binds functions to the listeners for the websocket.
                 */
                webSocket.onopen = function(event){
                    // For reasons I can't determine, onopen gets called twice
                    // and the first time event.data is undefined.
                    // Leave a comment if you know the answer.
                    if(event.data === undefined) {
                        return;
                    }
                    writeResponse(event.data);
                };

                webSocket.onmessage = function(event){
                    writeResponse(event.data);
                };

                webSocket.onclose = function(event){
                    writeResponse("Connection closed");
                };
            }

             var send = function(){
                var text = document.getElementById("messageinput").value;
                var testRequest = { "action": "test", "value": {"test" : ""} }
                testRequest["value"].test=text;
                webSocket.send(JSON.stringify(testRequest));
            }

            var sendJsonRequest = function(body){
                webSocket.send(JSON.stringify(body));
            }

            var closeSocket = function(){
                webSocket.close();
            }

            var writeResponse = function(text){
                var messages = document.getElementById("messages");
                messages.innerHTML += "<br/>" + text;
            }