<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
  <title>Room: ${roomId}</title>
  <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
  <style>
    #log {
      border: 1px solid #ccc;
      height: 300px;
      overflow-y: auto;
      padding: 8px;
      background: #f9f9f9;
    }
    #msg {
      width: 70%;
    }
  </style>
</head>
<body>
  <h2>Room: ${roomId}</h2>
  <div id="log"></div>

  <div style="margin-top:8px">
    <input id="msg" type="text" placeholder="message" onkeypress="if(event.keyCode==13) send()"/>
    <button onclick="send()">Send</button>
  </div>

<script>
    const roomId = "${roomId}";
    const sender = "${name}";
    const log = document.getElementById('log');

    let stompClient;

    function connect() {
        const socket = new SockJS('<c:url value="/ws"/>');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe(`/topic/rooms/${roomId}`, function (message) {
                const msg = JSON.parse(message.body);
                logMessage(msg.sender + ": " + msg.content);
            });
        })
    }

    function send() {
        const content = document.getElementById('msg').value;
        if(!content.trim()) return;

        stompClient.send("/app/chat.send", {}, JSON.stringify({
            roomId: roomId,
            sender: sender,
            content: content
        }));
        document.getElementById('msg').value = "";
    }

    function logMessage(message) {
        const p = document.createElement('p');
        p.textContent = message;
        log.appendChild(p);
        log.scrollTop = log.scrollHeight;
    }

    connect();
</script>
</body>
</html>