package com.amit.rabbitMQ.Producer;

import java.util.List;

import com.amit.domain.RabbitMessageDTO;

public interface RabbitMQProducerService {
	public boolean pushMessageInQUEUEForDirectExchange(List<RabbitMessageDTO> message) throws Exception;
	public boolean pushSingleMessageInQUEUEForDirectExchange(RabbitMessageDTO message) throws Exception;
}
