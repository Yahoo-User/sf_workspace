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

@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class SampleServiceTests {
	
	
	@Setter(onMethod_= {@Autowired})
	private SampleService service;
	

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		Objects.requireNonNull(service);
		log.info("\t+ service: " + service);
		log.info("\t+ Type: " + service.getClass().getName());		// Proxy for target object
	} // beforeAll
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testDoAdd")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testDoAdd() throws Exception {
		log.debug("testDoAdd() invoked.");
		
		Integer result = service.doAdd("123", "456");
		log.info("\t+ result: " + result);
	} // testDoAdd
	

//	@Disabled
	@Test
	@Order(2)
	@DisplayName("testDoAddWithError")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testDoAddWithError() throws Exception {
		log.debug("testDoAddWithError() invoked.");
		
		Integer result = service.doAdd("100", "삼");
		log.info("\t+ result: " + result);
	} // testDoAddWithError
	

//	@Disabled
	@Test
	@Order(3)
	@DisplayName("testMethod2")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testMethod2() throws Exception {
		log.debug("testMethod2() invoked.");
		
		service.method2();
	} // testMethod2

	

} // end class
