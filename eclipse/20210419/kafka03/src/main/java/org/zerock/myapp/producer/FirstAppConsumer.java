package org.zerock.myapp.producer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class FirstAppConsumer {
	private static final String topic = "TOPIC-1";
	private static final int partition = 0;
	

	
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
//		-----------------------

		Properties conf = new Properties();
		
		conf.setProperty("bootstrap.servers", "vfx-lenovo.localdomain:9092");
		
		conf.setProperty("group.id", "FirstConsumerGroup");
		
//		conf.setProperty("enable.auto.commit", "true");
		conf.setProperty("enable.auto.commit", "false");	// To directly commit a message in the code.
		
		conf.setProperty("key.deserializer",   "org.apache.kafka.common.serialization.IntegerDeserializer");
		conf.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		log.info("1. conf: {}", conf);
		
//		-----------------------
		
		Consumer<Integer, String> consumer = new KafkaConsumer<>(conf);
		consumer.subscribe(Collections.<String>singletonList(topic));
		
		log.info("2. consumer: {}", consumer);
		
//		-----------------------
		
		try (consumer) {
			
			for(int i=0;i<300;i++) {
				
//				ConsumerRecords<Integer, String> records = consumer.poll(1l);		// Deprecared (***)
				ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofSeconds(1));
				
//				-------------------------------
				
				for(ConsumerRecord<Integer, String> record : records) {
					
//					--------------------------------
//					1. Print a received message.
//					--------------------------------
					
					String message = String.format(
							"( topic: %s, paritition: %d, key: %d, value: %s, offset: %d )", 
							record.topic(), record.partition(), record.key(), record.value(), record.offset());
					
					log.info("3. RECV: {}", message);
					
//					--------------------------------
//					2. Commit a received message.
//					--------------------------------
					
					// Topic name and Partition number representation object.
					TopicPartition tp = new TopicPartition(topic, partition);
					log.info("4-1. TopicPartition: {}", tp);
					
//					The Kafka offset commit API allows users to provide additional metadata (in the form of a string)
//					when an offset is committed.
//					This can be useful (for example) to store information about which node made the commit, 
//					what time the commit was made, etc.
					OffsetAndMetadata oam = new OffsetAndMetadata(record.offset()+1);
					log.info("4-2. OffsetAndMetadata: {}", oam);
					
					Map<TopicPartition, OffsetAndMetadata> commitLog = Collections.singletonMap(tp, oam);
					log.info("4-3. commitLog: {}", commitLog);
					
					// commit synchronously.
//					consumer.commitSync(commitLog);
//					log.info("4-4. Commited.");
					
					// commit asynchronously.
					consumer.commitAsync(commitLog, new OffsetCommitCallback() {

						@Override
						public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception e) {
							log.debug("onComplete({}, {}) invoked.", offsets, e);
							
						} // onComplete
						
					}); // commitAsync
					
//					--------------------------------
					
					log.info("-----------------------------");
					
					try { Thread.sleep(1000); }
					catch(InterruptedException e) {}
				} // enhanced for
				
			} // for
			
		} // try-with-resources
		
		log.info("* END *");
	} // main

} // end class
