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
	ws1 = new WebSocket("ws://localhost:8899/flyfishWebSocket/"+ userId);
	function init() {
		ws1.onopen = function (event) {  
	    }  
		//监听消息  
		ws1.onmessage = function(event) {
			console.log(event.data+"=== 1");
		}
		ws1.onerror = function(e){
			console.log("webSocket=====");
		};
		/*监听 窗口关闭 当窗口关闭时，主动去关闭websocket连接*/
		window.onbeforeunload = function () {
			console.log("bbbbb");
			ws1.close();  
	    }  
		// 断线重连
		ws1.onclose = function () {
	 		console.log("===webSockt断开链接=====");
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
	function sendMsg(){
		var mess = document.getElementById("messageId");
		ws1.send(mess.value);
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
