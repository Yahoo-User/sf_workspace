package org.zerock.openapi;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class OpenAPITests {
	
	private final String baseURI = "https://api.odcloud.kr/api/15077586/v1/centers";
	
	
	
	@Test
	public void testOpenAPI() throws ClientProtocolException, IOException {
		log.debug("testOpenAPI() invoked.");
		
		//---------------------------------------------//
		// Step.1 Http Client 생성
		//---------------------------------------------//
		
		// 1. HTTP protocol 전용 client
//		HttpClientBuilder bulder = HttpClientBuilder.create();
		
		// 2. HTTPS protocol 전용 client
		HttpClientBuilder bulder = 
				HttpClientBuilder.
					create().
					setSSLHostnameVerifier(new NoopHostnameVerifier());
		
		CloseableHttpClient httpClient = bulder.build();
		
		
		//---------------------------------------------//
		// Step.2 Request 문서 생성
		//---------------------------------------------//
		
		// 필요한 전송파라미터들 선언
		String serviceKey = "EXlMlCQsfsgyPjJSxICHyAaU8tRGEtaXa9WM/2hK0aP5mrTJdZm836I7xtLKmtGSVXxCHOU6Drqw667fmqhQVw==";
		String page = "1";
		String perPage = "10";
		
		HttpUriRequest request = 
			RequestBuilder.
				get(baseURI).	// GET 방식의 Request 를 만들겠다!
				// 필요한 전송파라미터들 설정
				addParameter("serviceKey", serviceKey).
				addParameter("page", page).
				addParameter("perPage", perPage).
				// HttpRequest 객체를 생성 및 반환
				build();
		
		//---------------------------------------------//
		// Step.3 Request 문서 전송 => Respones 문서 수신
		//---------------------------------------------//
		
		try (httpClient) {
			
			CloseableHttpResponse response = httpClient.execute(request);
			
			try (response) {
				
				int statusCode = response.getStatusLine().getStatusCode();
				log.info("- HTTP status code: {}", statusCode);
				
				assert statusCode == HttpStatus.SC_OK;
				
//				response.getEntity().writeTo(System.out);
				
				
				//---------------------------------------------//
				// Step.4 Respones 문서의 Body 에 들어있는 JSON 문자열 획득
				//---------------------------------------------//
				HttpEntity entity = response.getEntity();
				
				String json = EntityUtils.toString(entity);
				
				log.info(json);
			} // try-with-resources
			
		} // try-with-resources
		
	} // testOpenAPI

} // end class
