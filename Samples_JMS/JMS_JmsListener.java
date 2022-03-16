package com.home.apacheMQEx.service;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.home.apacheMQEx.pojo.Employee;

@Component
public class JMSListener implements MessageListener {

	@Override
	@JmsListener(destination = "Queue22")
	public void onMessage(Message message) {
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			Employee employee = (Employee) objectMessage.getObject();
			System.out.println("Received Message from Topic: " + employee.toString());
		} catch (Exception e) {
			System.out.println("Received Exception while processing message: " + e);
		}
	}

}