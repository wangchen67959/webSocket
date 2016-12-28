<html>
<h2>Hello World!</h2>
<script src="BaseJs/jquery-1.8.3.min.js"></script>

<script type="text/javascript">
	//验证浏览器是否支持WebSocket协议  
	if (!window.WebSocket) {
		alert("WebSockeet not supported by this browser!");
	}
	
	var ws1;
	var tryTime =0;
	var userId = "one1";
	ws1 = new WebSocket("ws://localhost:8889/webSocket/flyfishWebSocket/"+ userId);
	function init() {
		//监听消息  
		ws1.onmessage = function(event) {
			console.log(event.data+"=== 1");
		}
		ws1.onerror = function(e){
			console.log("webSocket=====");
		};
/* 		ws1.send('I am the client and I\'m listening!');
 */		// 断线重连
		ws1.onclose = function () {
			// 重试10次，每次之间间隔10秒
			if (tryTime < 10) {
				setTimeout(function () {
					webSocket = null;
					tryTime++;
					init();
			 	}, 10000);
			} else {
				tryTime = 0;
			}
		};
		
	}
	
</script>
<body onload="init()">
	<div id="valueLabel"></div>
	<textarea rows="20" cols="30" id="contendId"></textarea>
	<br />
	<input name="message" id="messageId" />
	<button id="sendButton" onClick="javascript:sendMsg()">Send</button>
</body>
</html>
