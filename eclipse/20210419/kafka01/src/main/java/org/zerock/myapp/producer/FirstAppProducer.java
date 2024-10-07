package org.zerock.myapp.producer;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class FirstAppProducer {
	private static final String topic="TOPIC-1";
	private static final Integer partition = 0;
	

	
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
//		--------------------------------------------

		Properties conf = new Properties();
		
		conf.setProperty("bootstrap.servers", 	"vfx-lenovo.localdomain:9092");
		conf.setProperty("key.serializer", 		"org.apache.kafka.common.serialization.IntegerSerializer");
		conf.setProperty("value.serializer", 	"org.apache.kafka.common.serialization.StringSerializer");
		
		log.info("\t+ 1. conf: {}", conf);
		
//		--------------------------------------------
		
		Producer<Integer, String> producer = new KafkaProducer<>(conf);
		log.info("\t+ 2. producer: {}", producer);
		
		int key;
		String value;
		
		try (producer) {
		
			for(int i=0;i<1;i++) {
				
				key = i+1;
				value = "MESSAGE_"+String.valueOf(i);
				
	//			----------------------
				
				ProducerRecord<Integer, String> record = 
//						new ProducerRecord<>(topic, value);
//						new ProducerRecord<>(topic, key, value);
						new ProducerRecord<>(topic, partition, new Date().getTime(), key, value);
				
				log.trace("\t+ 3. record: {}", record);
				
	//			----------------------
				
//				producer.send(record);
				producer.send(record, new Callback() {

					@Override
					public void onCompletion(RecordMetadata metaData, Exception e) {
						log.debug("onCompletion({}, {}) invoked.", metaData, e);
						
						if(e != null) {	// failed to send.
							e.printStackTrace();
						} else {		// succeed to send.
							log.info("3. metaData: {}", metaData);
						} // if-else
					} // onCompletion
					
				}); // send
				
			} // for
			
			producer.flush();
		} catch(Exception e) {
			log.error("==================================");
			e.printStackTrace();
		} // try-with-finally
	} // main

} // end class
