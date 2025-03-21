package org.zerock.myapp.sample;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.myapp.config.RootContextXml;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


//---------------------- lombok library ----------------------//
@Log4j2
@NoArgsConstructor


//-------------------- spring-text library --------------------//

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


//For JUnit 4
//@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)

//For JUnit 5
@ExtendWith(SpringExtension.class)

@ContextConfiguration(classes = { RootContextXml.class })
public class DIBySetterTest {
	
//	@Setter(onMethod_= { @Autowired })
//	@Setter(onMethod_= { @Inject })
//	@Setter(onMethod_= { @Resource })
//	@Setter(onMethod_= { @Resource(type=Restaurant.class) })
	
//	@Setter(onMethod_= @Autowired)
//	@Setter(onMethod_= @Inject)
//	@Setter(onMethod_= @Resource)
//	@Setter(onMethod_= @Resource(type=Restaurant.class))
	
//	@Autwired
//	@Inject
//	@Resource
	@Resource(type=Restaurant.class)
	private Restaurant restaurant;
	

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(restaurant);
		
		log.info("\t+ Restaurant: " + restaurant);
	} // beforeAll
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testDI")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testDI() {
		log.debug("testDI() invoked.");

		log.info("\t+ Chef: " + restaurant.getChef());
	} // testDI

	

} // end class
