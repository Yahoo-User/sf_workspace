package org.zerock.myapp;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class URLEncodingDecodingTests {

	
	@Test
	@Order(1)
	@DisplayName("testURLEncoder")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)	// 1초안에 종료되어야 한다!
	void testURLEncoder() throws UnsupportedEncodingException {
		log.trace("testURLEncoder() invoked.");
		
//		String str = "한글";
//		String str = "ABCDefg1234567";
//		String str = "ABC EFG";
		String str = "http://localhost:8080/servlet?name=홍길동&age=23&address=서울";
		
				
		String urlencodedStr = URLEncoder.encode(str, "utf8");
		log.info("\t+ urlencodedStr: {}", urlencodedStr);
		
//		String urldecodedStr = URLDecoder.decode(urlencodedStr, "utf8");
		String urldecodedStr = URLDecoder.decode(urlencodedStr, StandardCharsets.UTF_8);
		log.info("\t+ urldecodedStr: {}", urldecodedStr);
	} // testURLEncoder
	
	
} // end class
