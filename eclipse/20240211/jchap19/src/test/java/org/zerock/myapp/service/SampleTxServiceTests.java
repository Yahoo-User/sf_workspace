package org.zerock.myapp.service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.myapp.config.RootContextXml;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


//For JUnit 4
//@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)

//For JUnit 5
@ExtendWith(SpringExtension.class)

@ContextConfiguration(classes= { RootContextXml.class })
public class SampleTxServiceTests {
	
	@Setter(onMethod_= {@Autowired})
	private SampleTxService service;
	

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		Objects.requireNonNull(this.service);
		
		log.info("\t+ service: " + this.service);
	} // beforeAll
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testAddData")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testAddData() throws Exception {
		log.debug("testAddData() invoked.");
		
		service.addData("1234567890123456789012345678901234567890123456789012345678901234567890"); 	// length: 70 chars.
		
	} // testAddData
	
	
	

} // end class
