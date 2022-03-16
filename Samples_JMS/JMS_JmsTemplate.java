package com.home.apacheMQEx.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.home.apacheMQEx.pojo.Employee;

@Component
public class JMSProducer {

	JmsTemplate jmsTemplate;
	private String topic = "Queue 88";

	public void sendMessage(Employee message) {
		jmsTemplate.convertAndSend(topic, message);
		jmsTemplate.browse(topic, message);
		jmsTemplate.convertAndSend(topic, message);
		jmsTemplate.doCreateProducer(null, topic);
		jmsTemplate.receive(topic);
		jmsTemplate.send(topic, message);
	}
}