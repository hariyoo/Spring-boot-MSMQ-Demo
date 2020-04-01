package com.hariyoo.msmqdemo.config;

import ionic.Msmq.MessageQueueException;
import ionic.Msmq.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author hariyoo
 * @Date 2020/4/1 18:03
 */
@Component
public class MsmqQueue {

	private static String fullName;

	private volatile static Queue queue;

	private MsmqQueue(){}

	public static Queue getInstance(){
		if (queue == null) {
			synchronized (Queue.class) {
				if (queue == null) {
					try {
						queue = new Queue(fullName);
					} catch (MessageQueueException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return queue;
	}

	@Value("${msmq.queue.url}")
	public void setFullName(String url) {
		fullName = url;
	}
}
