package org.zerock.myapp.uuid;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zerock.myapp.util.UUIDGenerator;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor

public class GenerateUUIDTests {
	
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
	} // setup
	
	
	//================ UUID versions ================//
//	•1 Time-based UUID 
//	•2 DCE security UUID 
//	•3 Name-based UUID 
//	•4 Randomly generated UUID 
	
	
	@Test
	public void testUUIDVersion1() {
		log.debug("testUUIDVersion1() invoked.");
		
		UUID uuid = UUIDGenerator.generateType1UUID();
		
		log.info("\t+ uuid: " + uuid);
		log.info("\t+ version: " + uuid.version());
	} // testUUIDVersion1
	
	
	@Test
	public void testUUIDVersion3() throws UnsupportedEncodingException {	// From namespace + name
		log.debug("testUUIDVersion3() invoked.");
		
		String namespace = "12345678901234567890123456789012";				// Must be 32 lengths.
		String name = "myapp";
		
		assert namespace.length() == 32;
		
		UUID uuid = UUIDGenerator.generateType3UUID(namespace, name);
		
		log.info("\t+ uuid: " + uuid);
		log.info("\t+ version: " + uuid.version());
	} // testUUIDVersion3
	
	
	@Test
	public void testUUIDVersion4() {
		log.debug("testUUIDVersion4() invoked.");
		
		UUID uuid = UUIDGenerator.generateType4UUID();
		
		log.info("\t+ uuid: " + uuid);
		log.info("\t+ version: " + uuid.version());
	} // testUUIDVersion4
	
	
	@Test
	public void testUUIDversion5() throws UnsupportedEncodingException {
		log.debug("testUUIDversion5() invoked.");
		
		String namespace = "12345678901234567890123456789012";				// Must be 32 lengths.
		String name = "myapp";
		
		assert namespace.length() == 32;
		
		UUID uuid = UUIDGenerator.generateType5UUID(namespace, name);
		
		log.info("\t+ uuid: " + uuid);
		log.info("\t+ version: " + uuid.version());
	} // testUUIDversion5
	
	
	@Test
	public void testUUIDAndMessageDigest() 
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		log.debug("testUUIDAndMessageDigest() invoked.");
		
		String uuid = UUIDGenerator.generateUniqueKeysWithUUIDAndMessageDigest();
		
		log.info("\t+ uuid: " + uuid);
	} // testUUIDAndMessageDigest
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // tearDown

} // end class
