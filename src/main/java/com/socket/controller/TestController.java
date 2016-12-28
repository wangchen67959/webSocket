package com.socket.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.socket.service.UseSocket;

@Controller
// @RequestMapping(value="/testController")
public class TestController {

	public static final Logger LOGGER = Logger.getLogger(TestController.class);

	public UseSocket useSocket;

	public UseSocket getUseSocket() {
		return useSocket;
	}

	public void setUseSocket(UseSocket useSocket) {
		this.useSocket = useSocket;
	}

	@RequestMapping("/test5.do")
	public void TestWebSocket() {
		System.out.println("测试webSocket");
		Thread t = new Thread(new Runnable() {
			public void run() {
				int count = 0;
				for (int i = 0; i < 5; i++) {
					count++;
					try {
						useSocket.sedMessage("one1", i + "" +Thread.currentThread().getName());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("t" + count);
			}
		});
		t.start();
		// Thread t1 = new Thread(new Runnable() {
		// public void run() {
		// int count =0;
		// for(int i=0;i<5; i++) {
		// count++;
		// new WebSocketUtil().sedMessage(i+""+Thread.currentThread().getName(),
		// "one2");
		// }
		// System.out.println("t" +count);
		// }
		// });
		// t1.start();
	}

}
