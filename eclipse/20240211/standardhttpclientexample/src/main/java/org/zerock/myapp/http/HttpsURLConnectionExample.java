package org.zerock.myapp.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.cert.Certificate;
import java.util.Arrays;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class HttpsURLConnectionExample {
//	private static Charset charset = Charset.forName("utf8");
	private static Charset charset = StandardCharsets.UTF_8;
			
	
	public static void main(String[] args) throws IOException {
		log.trace("main({}) invoked.", Arrays.toString(args));
		
		
		// --------------------------------- //
		// 1. URLConnection creation
		// --------------------------------- //
//		URL url = new URL("https://localhost:8080/search?q=java");
		URL url = new URL("https://developer.mozilla.org/en-US/search?q=400");
		
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		
		// --------------------------------- //
		// 2. Connection settings
		// --------------------------------- //
		conn.setHostnameVerifier(new HostnameVerifier() {

			@Override
			public boolean verify(String host, SSLSession session) {
				log.trace("verify({}, {}) invoked.", host, session);
				
				return true;
			} // verify
			
		}); // setHostnameVerifier
		
		conn.setConnectTimeout(1000);
		conn.setUseCaches(false);
		conn.setDoOutput(false);
		conn.setDoInput(true);
		conn.setReadTimeout(3000);
		conn.setRequestMethod("GET");
		
		
		// --------------------------------- //
		// 3. Connect to the target
		// --------------------------------- //
		conn.connect();
		
		log.debug("Cipher Suite : {}", conn.getCipherSuite());
		
		Certificate[] certs = conn.getServerCertificates();
		
		for(Certificate cert : certs) {
			log.debug("Cert Type : {}", cert.getType());
			log.debug("Cert Hash Code : {}", cert.hashCode());
			log.debug("Cert Public Key Algorithm : {}", cert.getPublicKey().getAlgorithm());
			log.debug("Cert Public Key Format : {}", cert.getPublicKey().getFormat());
			log.debug("\n");
		} // enhanced for
		
		
		// --------------------------------- //
		// 4. Output to the target (automatically POST)
		// --------------------------------- //		
//		BufferedWriter bw =
//			new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), charset));
//		
//		bw.write("name=Yoseph&age=23");
//		
//		bw.flush();
//		bw.close();
		
		
		// --------------------------------- //
		// 5. Input from the target
		// --------------------------------- //	
		if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			
			BufferedReader br =
				new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
			
			String line = null;
			
			while((line=br.readLine()) != null) {	// null: EOF
				System.out.println(line);
			} // while
			
			br.close();
			
		} else {
			log.info("HTTP status code: {}", conn.getResponseCode());
		} // if-else
		
		
		// --------------------------------- //
		// 6. Release resources`
		// --------------------------------- //		
		conn.disconnect();
	} // main

} // end class
