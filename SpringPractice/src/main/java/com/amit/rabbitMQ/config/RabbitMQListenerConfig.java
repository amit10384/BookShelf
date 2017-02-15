package com.amit.rabbitMQ.config;

import org.aopalliance.aop.Advice;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import com.amit.rabbitMQ.Consumer.ListenerClientImpl;


@Configuration
public class RabbitMQListenerConfig {
	
	@Value("${message.queue.name}")
	private String queueName;

	@Autowired
	MessageFailureRecoverer messageFailureRecoverer;
	
	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	Environment environment;

	@Bean
	ListenerClientImpl inputMessageListener() {
		return new ListenerClientImpl();
	}

	@Bean
	ChannelAwareMessageListener inputMessageListernerAdaptor(ListenerClientImpl inputMessageListener) {
		return inputMessageListener;
	}

	@Bean
	SimpleMessageListenerContainer inputMessageListenerContainer(ConnectionFactory connectionFactory,
			ChannelAwareMessageListener inputMessageListernerAdaptor) {

		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(inputMessageListernerAdaptor);
		container.setAdviceChain(new Advice[] {
		        org.springframework.amqp.rabbit.config.RetryInterceptorBuilder
		                .stateless()
		                .maxAttempts(1)
		                .backOffOptions(2000, 1, 12000)
		                .recoverer(messageFailureRecoverer)
		                .build()
		});
		
		return container;
	}

}