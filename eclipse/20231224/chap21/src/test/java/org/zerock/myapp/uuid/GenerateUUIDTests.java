package org.zerock.myapp.uuid;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.zerock.myapp.util.UUIDGenerator;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GenerateUUIDTests {
	
	
	//================ UUID versions ================//
//	•1 Time-based UUID 
//	•2 DCE security UUID 
//	•3 Name-based UUID 
//	•4 Randomly generated UUID 
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testUUIDVersion1")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testUUIDVersion1() {
		log.debug("testUUIDVersion1() invoked.");
		
		UUID uuid = UUIDGenerator.generateType1UUID();
		
		log.info("\t+ uuid: " + uuid);
		log.info("\t+ version: " + uuid.version());
	} // testUUIDVersion1
	

//	@Disabled
	@Test
	@Order(2)
	@DisplayName("testUUIDVersion3")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testUUIDVersion3() throws UnsupportedEncodingException {	// From namespace + name
		log.debug("testUUIDVersion3() invoked.");
		
		String namespace = "12345678901234567890123456789012";				// Must be 32 lengths.
		String name = "myapp";
		
		assert namespace.length() == 32;
		
		UUID uuid = UUIDGenerator.generateType3UUID(namespace, name);
		
		log.info("\t+ uuid: " + uuid);
		log.info("\t+ version: " + uuid.version());
	} // testUUIDVersion3
	

//	@Disabled
	@Test
	@Order(3)
	@DisplayName("testUUIDVersion4")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testUUIDVersion4() {
		log.debug("testUUIDVersion4() invoked.");
		
		UUID uuid = UUIDGenerator.generateType4UUID();
		
		log.info("\t+ uuid: " + uuid);
		log.info("\t+ version: " + uuid.version());
	} // testUUIDVersion4
	

//	@Disabled
	@Test
	@Order(4)
	@DisplayName("testUUIDVersion5")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testUUIDVersion5() throws UnsupportedEncodingException {
		log.debug("testUUIDVersion5() invoked.");
		
		String namespace = "12345678901234567890123456789012";				// Must be 32 lengths.
		String name = "myapp";
		
		assert namespace.length() == 32;
		
		UUID uuid = UUIDGenerator.generateType5UUID(namespace, name);
		
		log.info("\t+ uuid: " + uuid);
		log.info("\t+ version: " + uuid.version());
	} // testUUIDVersion5
	

//	@Disabled
	@Test
	@Order(5)
	@DisplayName("testUUIDAndMessageDigest")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testUUIDAndMessageDigest() 
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		log.debug("testUUIDAndMessageDigest() invoked.");
		
		String uuid = UUIDGenerator.generateUniqueKeysWithUUIDAndMessageDigest();
		
		log.info("\t+ uuid: " + uuid);
	} // testUUIDAndMessageDigest

	

} // end class
