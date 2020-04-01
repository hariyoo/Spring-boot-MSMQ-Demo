package com.hariyoo.msmqdemo;

import com.hariyoo.msmqdemo.config.MsmqQueue;
import ionic.Msmq.Message;
import ionic.Msmq.MessageQueueException;
import ionic.Msmq.Queue;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

@SpringBootTest
@Slf4j
class MsmqDemoApplicationTests {


	@Test
	void contextLoads() {}

	@Test
	public void send(){
		try {
			Queue sendQueue = MsmqQueue.getInstance();
			String label = "test_send";
			String correlationId = "0";
			String body = "MSMQ真特么好用...!!??";
			Message msg= new Message(body, label, correlationId);
			sendQueue.send(msg);
			log.info("发送完成");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessageQueueException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void receive(){
		try {
			Queue queue = MsmqQueue.getInstance();
			Message message = queue.receive();
			String body = message.getBodyAsString();
			log.info("消息内容 -> {}", body);
		} catch (MessageQueueException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
