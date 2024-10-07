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
@ContextConfiguration(classes= { RootContextXml.class })
public class SampleTxServiceTests {
	
	@Setter(onMethod_= {@Autowired})
	private SampleTxService service;
	
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		Objects.requireNonNull(this.service);
		
		log.info("\t+ service: " + this.service);
	} // setup
	
	
	@Test
	public void testAddData() throws Exception {
		log.debug("testAddData() invoked.");
		
		service.addData("1234567890123456789012345678901234567890123456789012345678901234567890"); 	// length: 70 chars.
		
	} // testAddData
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // tearDown

} // end class
