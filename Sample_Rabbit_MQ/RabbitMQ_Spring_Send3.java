package com.example.messagingrabbitmq;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner_2 implements CommandLineRunner {

  private final RabbitTemplate rabbitTemplate;
  private final Receiver receiver;

  public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
    this.receiver = receiver;
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Sending message...");
    rabbitTemplate.convertAndSend("RMQ_q1_3", "q1", "Hello from RabbitMQ!"); // Queue name "RMQ_q1"
	rabbitTemplate.convertAndSend("Hello from RabbitMQ!"); // No queue name here!
	rabbitTemplate.convertAndSend("Hello from RabbitMQ!", new MessagePostProcessor()); // No queue name here!
	rabbitTemplate.convertAndSend("RMQ_q2_3", "Hello from RabbitMQ!"); // Queue name "RMQ_q2"
	rabbitTemplate.convertAndSend("RMQ_q3_3", "Hello from RabbitMQ!", new MessagePostProcessor()); // Queue name "RMQ_q3"
	rabbitTemplate.convertAndSend("RMQ_q4_3", "q4", "Hello from RabbitMQ!", new MessagePostProcessor()); // Queue name "RMQ_q4"
    receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
  }

}