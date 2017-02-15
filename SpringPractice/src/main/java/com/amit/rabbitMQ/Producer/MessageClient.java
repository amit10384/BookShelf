package com.amit.rabbitMQ.Producer;

import org.springframework.amqp.core.Message;

public interface MessageClient {
	public void sendMessage(Message message) ;
}
