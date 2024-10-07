package org.zerock.myapp.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;


//---------------------- lombok library ----------------------//
@Log4j
@NoArgsConstructor(access=AccessLevel.PUBLIC)


//-------------------- spring-text library --------------------//
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)

@ContextConfiguration(locations= {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class DIBySetterTest {
	
	@Setter(onMethod_= { @Autowired })
//	@Setter(onMethod_= { @Inject })
	
//	@Setter(onMethod_= { @Resource })
//	@Setter(onMethod_= { @Resource(type=Restaurant.class) })
	
	
//	@Setter(onMethod_= @Autowired)
//	@Setter(onMethod_= @Inject)
	
	
//	@Setter(onMethod_= @Resource)
//	@Setter(onMethod_= @Resource(type=Restaurant.class))
	
//	@Inject
//	@Autowired
	
//	@Resource
//	@Resource(type = Restaurant.class)
	private Restaurant restaurant;
	
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		assertNotNull(restaurant);
		
		log.info("\t+ Restaurant: " + restaurant);
	} // setup
	
	
	@Test(timeout=1000)
//	@Test
	public void testDI() {
		log.debug("testDI() invoked.");

		log.info("\t+ Chef: " + restaurant.getChef());
	} // testDI
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // tearDown

} // end class
