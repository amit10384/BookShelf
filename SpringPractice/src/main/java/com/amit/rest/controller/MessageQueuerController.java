package com.amit.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amit.domain.RabbitMessageDTO;
import com.amit.rabbitMQ.Producer.RabbitMQProducerImpl;

@Controller
@RequestMapping(value="/message")
public class MessageQueuerController {
	
	private static Logger log = LoggerFactory.getLogger(MessageQueuerController.class);
	
	@Autowired
	private RabbitMQProducerImpl rabbitProducerService;

	@RequestMapping(value="/pushMessage",method=RequestMethod.POST)	
   public ResponseEntity<String> pushBulkMessagesInQueueForDefaultExchange(@RequestBody List<RabbitMessageDTO> request) {
		ResponseEntity<String> resp = null;
	   try {
	   rabbitProducerService.pushMessageInQUEUEForDirectExchange(request);
	   resp = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
	   } catch(Exception e) {
		   log.error("error occured during message Queueing!!");
		   resp = new ResponseEntity<String>("FAILURE",HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	   return resp;
	   
   }
	
	
	@RequestMapping(value="/pushSingleMessage",method=RequestMethod.POST)	
	   public  ResponseEntity<String> pushSingleMessageInQueueForDefaultExchange(@RequestBody RabbitMessageDTO request) {
		   ResponseEntity<String> resp = null;
		   try { 
		   rabbitProducerService.pushSingleMessageInQUEUEForDirectExchange(request);
		   resp = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		   } catch(Exception e) {
			   log.error("error occured during message Queueing!!");
			   resp = new ResponseEntity<String>("FAILURE",HttpStatus.INTERNAL_SERVER_ERROR);
		   }
		   return resp;
		   
		   
	   }
}
