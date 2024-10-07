package org.zerock.myapp.service;

import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.config.RootContextXml;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor


//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootContextXml.class })
public class SampleServiceTests {
	
	
	@Setter(onMethod_= {@Autowired})
	private SampleService service;
	
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		Objects.requireNonNull(service);
		log.info("\t+ service: " + service);
		log.info("\t+ Type: " + service.getClass().getName());		// Proxy for target object
	} // setup
	
	
	@Test
	public void testDoAdd() throws Exception {
		log.debug("testDoAdd() invoked.");
		
		Integer result = service.doAdd("123", "456");
		log.info("\t+ result: " + result);
	} // testDoAdd
	
	
	@Test
	public void testDoAddWithError() throws Exception {
		log.debug("testDoAddWithError() invoked.");
		
		Integer result = service.doAdd("100", "삼");
		log.info("\t+ result: " + result);
	} // testDoAddWithError
	
	
	@Test
	public void testMethod2() throws Exception {
		log.debug("testMethod2() invoked.");
		
		service.method2();
	} // testMethod2
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // tearDown

} // end class
