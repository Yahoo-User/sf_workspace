package org.zerock.myapp.service;

import java.util.concurrent.ExecutionException;


public interface KafkaMessagingService {
	
	public static final String 	topic = "TOPIC-1";
	public static final Integer partition = 0;
	
	
	public abstract void sendMessage(String message) throws InterruptedException, ExecutionException;

} // end interface
