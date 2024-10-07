package org.zerock.myapp.mapper;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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
import org.zerock.myapp.mapper2.TimeMapper2;

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

@ContextConfiguration(locations= {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
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
	

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		Objects.requireNonNull(mapper);
		log.info("\t+ mapper: " + mapper);
		
		Objects.requireNonNull(this.sqlSessionFactory);
		log.info("\t+ sqlSessionFactory: " + this.sqlSessionFactory);
	} // beforeAll
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testGetTime1")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testGetTime1() {
		log.debug("testGetTime1() invoked.");
		
		String now = mapper.getTime1();
		log.info("\t+ now: " + now);
	} // testGetTime1
	

//	@Disabled
	@Test
	@Order(2)
	@DisplayName("testGetTime2")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testGetTime2() {
		log.debug("testGetTime2() invoked.");
		
		String now = mapper.getTime2();
		log.info("\t+ now: " + now);
	} // testGetTime2
	

//	@Disabled
	@Test
	@Order(2)
	@DisplayName("testGetTime3")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
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

} // end class
