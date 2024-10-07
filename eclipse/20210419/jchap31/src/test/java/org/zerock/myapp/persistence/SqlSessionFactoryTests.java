package org.zerock.myapp.persistence;

import org.apache.ibatis.session.SqlSessionFactory;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= { RootContextXml.class })
public class SqlSessionFactoryTests {
	
	@Setter(onMethod_= {@Autowired})
	private SqlSessionFactory sqlSessionFactory;
	
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
//		assertNotNull(this.sqlSessionFactory);
		assert this.sqlSessionFactory != null;
		
		log.info("\t+ sqlSessionFactory: " + this.sqlSessionFactory);
	} // setup
	
	
	@Test
	public void testXXX() {
		log.debug("testXXX() invoked.");
		
	} // testXXX
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // tearDown

} // end class
