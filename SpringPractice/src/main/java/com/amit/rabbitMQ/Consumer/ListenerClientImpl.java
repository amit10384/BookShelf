package com.amit.rabbitMQ.Consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;

@Component
public class ListenerClientImpl implements ChannelAwareMessageListener{

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		System.out.println("Consumer is up and received message from the Queue.."+message.getBody());
		String messageData = new String(message.getBody(), "UTF-8");
        System.out.println(" [x] Received '" + messageData + "'");
		
	}
  
}
