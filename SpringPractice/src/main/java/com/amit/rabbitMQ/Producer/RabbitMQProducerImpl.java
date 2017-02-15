package com.amit.rabbitMQ.Producer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.domain.RabbitMessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.MessageProperties;


@Service
public class RabbitMQProducerImpl implements RabbitMQProducerService {
	private static Logger log = LoggerFactory.getLogger(RabbitMQProducerImpl.class);

	@Autowired
	private MessageClient messageProducer;
    
	@Override
	public boolean pushMessageInQUEUEForDirectExchange(List<RabbitMessageDTO> mesageList) throws Exception  {
		log.debug("starting intializing Producer to push msg in QUEUE");
		for(RabbitMessageDTO message : mesageList) {
		try {
			MessageProperties messageProperties = new MessageProperties();
			messageProperties.setContentType("application/json");
			messageProperties.setHeader("HEADER", "NO_HEADER");
			ObjectMapper mapper = new ObjectMapper();
			String json;
			json = mapper.writeValueAsString(message);
			messageProducer.sendMessage(new Message(json.getBytes(), messageProperties));
			log.debug("exiting from Producer");
		} catch (Exception ex) {
			log.error("Exception occured while sending notification for event {} {}", ex);
			throw ex;
			
		}
		}
		return true;
	}
	
	@Override
	public boolean pushSingleMessageInQUEUEForDirectExchange(RabbitMessageDTO  message) throws Exception {
		log.debug("starting intializing Producer to push msg in QUEUE");
		try {
			MessageProperties messageProperties = new MessageProperties();
			messageProperties.setContentType("application/json");
			messageProperties.setHeader("HEADER", "NO_HEADER");
			ObjectMapper mapper = new ObjectMapper();
			String json;
			json = mapper.writeValueAsString(message);
			messageProducer.sendMessage(new Message(json.getBytes(), messageProperties));
			log.debug("exiting from Producer");
		} catch (Exception ex) {
			log.error("Exception occured while sending notification for event {} {}", ex);
			throw ex;
		}
		return true;
	}
}
