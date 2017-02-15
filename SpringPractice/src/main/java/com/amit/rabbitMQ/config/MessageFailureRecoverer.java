package com.amit.rabbitMQ.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.stereotype.Component;

@Component
public class MessageFailureRecoverer implements MessageRecoverer {

	@Override
	public void recover(Message message, Throwable cause) {
		ListenerExecutionFailedException lefex = (ListenerExecutionFailedException) cause;
		lefex.printStackTrace();
		System.exit(1);

	}

}