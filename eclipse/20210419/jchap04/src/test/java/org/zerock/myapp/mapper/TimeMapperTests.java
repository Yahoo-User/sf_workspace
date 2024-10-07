package org.zerock.myapp.mapper;

import java.util.Date;
import java.util.Objects;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.config.RootContextXml;
import org.zerock.myapp.mapper2.TimeMapper2;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= { RootContextXml.class })
public class TimeMapperTests {
	
//	@Setter(onMethod_= { @Inject })
	
//	@Setter(onMethod_= { @Resource })
//	@Setter(onMethod_= { @Resource(type=TimeMapper.class) })
	
	
//	@Setter(onMethod_= @Autowired)
//	@Setter(onMethod_= @Inject)
	
	
//	@Setter(onMethod_= @Resource)
//	@Setter(onMethod_= @Resource(type=TimeMapper.class))
	
//	@Inject
//	@Autowired
	
//	@Resource
//	@Resource(type = TimeMapper.class)
	
	@Setter(onMethod_= { @Autowired })
	private TimeMapper mapper;
	
	
	@Setter(onMethod_= {@Autowired})
	private SqlSessionFactory sqlSessionFactory;
	
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		Objects.requireNonNull(mapper);
		log.info("\t+ mapper: " + mapper);
		
		Objects.requireNonNull(this.sqlSessionFactory);
		log.info("\t+ sqlSessionFactory: " + this.sqlSessionFactory);
	} // setup
	
	
	@Test
	public void testGetTime1() {
		log.debug("testGetTime1() invoked.");
		
		String now = mapper.getTime1();
		log.info("\t+ now: " + now);
	} // testGetTime1
	
	
	@Test
	public void testGetTime2() {
		log.debug("testGetTime2() invoked.");
		
		String now = mapper.getTime2();
		log.info("\t+ now: " + now);
	} // testGetTime2
	
	
	@Test
	public void testGetTime3() {
		log.debug("testGetTime3() invoked.");

		//-----------------------------------------------//
		// 실행중에 동적으로 마이바티스 설정에 새로운 Mapper Interface 추가 (***)
		//-----------------------------------------------//
		Configuration mybatisConfig = 
				this.sqlSessionFactory.getConfiguration();
		
		mybatisConfig.addMapper(TimeMapper2.class);	// *** 추가 ***
		//-----------------------------------------------//
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try(sqlSession) {
			
			//-----------------------------------------------//
			// 동적으로 새롭게 추가한 Mapper Interface의 사용
			//-----------------------------------------------//
			TimeMapper2 mapper2 = sqlSession.getMapper(TimeMapper2.class);
			
			Objects.requireNonNull(mapper2);
			log.info("\t+ mapper2: " + mapper2);
			
			Date now = mapper2.getTime3();	// *** 사용 ***
			log.info("\t+ now: " + now);
			//-----------------------------------------------//
			
		} // try-with-resources
	} // testGetTime3
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // tearDown

} // end class
