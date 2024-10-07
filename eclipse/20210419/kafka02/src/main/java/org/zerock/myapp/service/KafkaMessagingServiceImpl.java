package org.zerock.myapp.service;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service
public class KafkaMessagingServiceImpl
	implements InitializingBean, KafkaMessagingService {
	
	public static Integer messageKey = 0;
	
//	@Autowired		-> XX: null
//	@Setter			-> XX: null
	@Setter(onMethod_= {@Autowired})	// OK
	private KafkaTemplate<Integer, String> kafkaTemplate;
	


	@Override
	public void sendMessage(String message)
			throws InterruptedException, ExecutionException {
		log.info("sendMessage({}) invoked.", message);
		
		ListenableFuture<SendResult<Integer, String>> result=
			this.kafkaTemplate.send(
				KafkaMessagingService.topic,
				KafkaMessagingService.partition,
				messageKey++,
				message
			); // send
		
//		---------------------------
		
//		ListenableFuture<SendResult<Integer, String>> result=
//			this.kafkaTemplate.sendDefault(KafkaMessagingService.partition, messageKey++, message);
		
//		---------------------------
		
		SendResult<Integer, String> sendResult = result.get();
		log.info(">> sendResult: {}", sendResult);
	} // sendMessage

	
	
//	================================== //
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("afterPropertiesSet() invoked.");
		
		log.info("\t+ kafkaTemplate: {}", this.kafkaTemplate);
	} // afterPropertiesSet

} // end class
