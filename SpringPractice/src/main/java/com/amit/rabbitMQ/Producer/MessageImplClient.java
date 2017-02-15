package com.amit.rabbitMQ.Producer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageImplClient implements MessageClient {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${message.queue.name}")
	private String queueName;

	@Override
	public void sendMessage(Message message) {

		rabbitTemplate.convertAndSend(queueName, message);
	}

}
