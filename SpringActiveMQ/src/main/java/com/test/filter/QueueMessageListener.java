package com.test.filter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * ���ü���������Ϣ����ʱ�����߽����ղ�����Ϣ
 * @author John
 *
 */
public class QueueMessageListener implements MessageListener {
	public void onMessage(Message message) {
		TextMessage tm = (TextMessage) message;
		try {
			System.out.println("QueueMessageListener���������ı���Ϣ��\t" + tm.getText());
			// do something ...
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
