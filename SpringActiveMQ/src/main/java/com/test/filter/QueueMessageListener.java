package com.test.filter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 启用监听接收消息，此时消费者将接收不到消息
 * @author John
 *
 */
public class QueueMessageListener implements MessageListener {
	public void onMessage(Message message) {
		TextMessage tm = (TextMessage) message;
		try {
			System.out.println("QueueMessageListener监听到了文本消息：\t" + tm.getText());
			// do something ...
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
