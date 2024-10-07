package org.zerock.myapp.sample;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor


//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class DIByConstructorTest {
	
//	@Setter(onMethod_= { @Autowired })
//	@Setter(onMethod_= { @Inject })
//	@Setter(onMethod_= { @Resource })
//	@Setter(onMethod_= { @Resource(type=Hotel.class) })
	
//	@Setter(onMethod_= @Autowired)
//	@Setter(onMethod_= @Inject)
//	@Setter(onMethod_= @Resource)
//	@Setter(onMethod_= @Resource(type=Hotel.class))
	
//	@Autwired
//	@Inject
//	@Resource
	@Resource(type=Hotel.class)
	private Hotel hotel;
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		assertNotNull(hotel);
		
		log.info("\t+ Hotel: " + hotel);
	} // setup
	
	
//	@Test
	@Test(timeout=1000)
	public void testDI() {
		log.debug("testDI() invoked.");

		log.info("\t+ Chef: " + hotel.getChef());
	} // testDI
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // tearDown

} // end class
