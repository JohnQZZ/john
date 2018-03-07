package com.test.controller;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.service.ConsumerService;
import com.test.service.ProducerService;

@Controller
public class MessageController {

	private Logger logger = LoggerFactory.getLogger(MessageController.class);
	// @Resource(name = "demoQueueDestination")
	@Autowired
	private Destination destination;

	// 队列消息生产者
	@Resource(name = "producerService")
	private ProducerService producer;

	// 队列消息消费者
	@Resource(name = "consumerService")
	private ConsumerService consumer;

	@RequestMapping(value = "/SendMessage", method = RequestMethod.POST)
	@ResponseBody
	public void send(String msg, HttpServletRequest request) {
		logger.info(Thread.currentThread().getName() + "------------send to jms Start");
		producer.sendMessage(destination, msg);
		System.out.println(request.getRequestURL());
		logger.info(Thread.currentThread().getName() + "------------send to jms End");
	}

	@RequestMapping(value = "/ReceiveMessage", method = RequestMethod.GET)
	@ResponseBody
	public String receive() throws JMSException {
		logger.info(Thread.currentThread().getName() + "------------receive from jms Start");
		TextMessage tm = consumer.receive(destination);
		logger.info(Thread.currentThread().getName() +
				"------------receive from jms End");
		return tm.getText();
	}

}
