package org.zerock.myapp.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;
import org.zerock.myapp.service.KafkaMessagingService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component
public class KafkaMessagingListener {
	
	
	@KafkaListener(
			id="KafkaMessagingListener-1",
			groupId = KafkaMessagingService.topic+"-GROUP",
			topics = { KafkaMessagingService.topic },
			topicPartitions = { 
				@TopicPartition(topic=KafkaMessagingService.topic, partitions= { "0" })
			})
	public void onReceiveMessageFromTopic(ConsumerRecord<Integer, String> recvRecord) {
		log.info("onReceiveMessageFromTopic({}) invoked.", recvRecord);
		
//		--------------------------------
//		1. Print a received message.
//		--------------------------------
		
		String message = String.format(
				"( Topic: %s, Paritition: %d, Key: %d, Value: %s, Offset: %d )", 
				recvRecord.topic(), recvRecord.partition(), recvRecord.key(), recvRecord.value(), recvRecord.offset());
		
		log.info(">> RECV: {}", message);
		
	} // onReceiveMessageFromTopic

} // end class
