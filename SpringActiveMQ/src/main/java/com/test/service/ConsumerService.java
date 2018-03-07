package com.test.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

	@Autowired
	// @Resource(name="jmsTemplate")
	private JmsTemplate jmsTemplate;

	public TextMessage receive(Destination destination) {
		TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);
//		TextMessage textMessage = (TextMessage) jmsTemplate.receive();
		try {
			System.out.println("从队列" + destination.toString() + "收到了消息：\t" + textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return textMessage;
	}
}
