package org.zerock.myapp.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.service.KafkaMessagingService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RestController
public class KafkaController {
	
	@Setter(onMethod_= @Autowired)
	private KafkaMessagingService service;
	
	private static Integer seq = 0;
	
	
	@GetMapping("/sendMessage")
	public String sendMessage(String message)
			throws InterruptedException, ExecutionException {
		log.debug("sendMessage({}) invoked.", message);
		
		String sendMessage=String.format("%s_%03d", message, ++seq);
		this.service.sendMessage(sendMessage);
		
		return sendMessage;
	} // sendMessage

} // end class
