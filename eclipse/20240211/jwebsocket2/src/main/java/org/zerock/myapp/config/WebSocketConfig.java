package org.zerock.myapp.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.zerock.myapp.handler.EchoHandler;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

// Spring Bean Configuration File(XML)에서 설정하지 않고, 자바설정으로 웹소켓을 설정하는 경우에 사용
@EnableWebSocket

@Configuration("webSocketConfig")
public class WebSocketConfig
	implements WebSocketConfigurer, InitializingBean, DisposableBean {
	
	
	
	@Bean("servletServerContainerFactory")
	public ServletServerContainerFactoryBean configureWebSocketContainer() {
		log.debug("configureWebSocketContainer() invoked.");
		
		ServletServerContainerFactoryBean factory = new ServletServerContainerFactoryBean();
		
		factory.setMaxTextMessageBufferSize(16384);
		factory.setMaxBinaryMessageBufferSize(16384);
		factory.setMaxSessionIdleTimeout(30000L);
		factory.setAsyncSendTimeout(10000L);
		
		return factory;
	} // configureWebSocketContainer
	
	
	@Bean("echoHandler")
	public EchoHandler echoHandler() {
		log.debug("echoHandler() invoked.");
		
		return new EchoHandler();
	} // echoHandler

	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		log.debug("registerWebSocketHandlers(registry) invoked.");
		
//		registry.addHandler(echoHandler(), "/echo");	// Add WebSocket Handler without HttpSession
		
		registry.										// Add WebSocket Handler with HttpSession
			addHandler(echoHandler(), "/echo").
			addInterceptors(new HttpSessionHandshakeInterceptor());
	} // configureWebSocketContainer
	
	
	
	//===================================================//

	@Override
	public void destroy() throws Exception {
		log.debug("destroy() invoked.");
		
	} // destroy

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("afterPropertiesSet() invoked.");
		
	} // afterPropertiesSet

} // end class
