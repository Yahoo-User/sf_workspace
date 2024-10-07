package org.zerock.myapp.sample;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.config.RootContextXml;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


//---------------------- lombok library ----------------------//
@Log4j
@NoArgsConstructor


//-------------------- spring-text library --------------------//
//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)

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
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		assertNotNull(restaurant);
		
		log.info("\t+ Restaurant: " + restaurant);
	} // setup
	
	
//	@Test
	@Test(timeout=1000)
	public void testDI() {
		log.debug("testDI() invoked.");

		log.info("\t+ Chef: " + restaurant.getChef());
	} // testDI
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // tearDown

} // end class
