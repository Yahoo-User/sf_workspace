package org.zerock.myapp.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
import org.zerock.myapp.domain.EmployeeVO;

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
	"file:src/main/webapp/WEB-INF/spring/root-*.xml"
})
public class SqlSessionFactoryTests {
	
	@Setter(onMethod_= {@Autowired})
	private SqlSessionFactory sqlSessionFactory;
	

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.sqlSessionFactory);
		log.info("\t+ sqlSessionFactory: " + this.sqlSessionFactory);
	} // beforeAll
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testSQLMapper1")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testSQLMapper1() {
		log.debug("testSQLMapper1() invoked.");
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try(sqlSession) {
			
			String namespace = "sqlmapper1";
			String sqlId = "DQL1";
			int empId = 100;
					
			List<EmployeeVO> employees = 
				sqlSession.<EmployeeVO>selectList(namespace+"."+sqlId, empId);
			
			employees.forEach(log::info);
			
		} // try-with-resources
		
	} // testSQLMapper1
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testSQLMapper2")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testSQLMapper2() {
		log.debug("testSQLMapper2() invoked.");
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try(sqlSession) {
			String namespace = "sqlmapper2";
			String sqlId = "DQL2";
			
			Map<String, Object> params = new HashMap<>();
			params.put("email", "E");
			params.put("salary", 1000);
					
			List<EmployeeVO> employees = 
				sqlSession.
					<EmployeeVO>selectList(namespace+"."+sqlId, params);
			
			employees.forEach(log::info);
		} // try-with-resources
		
	} // testSQLMapper2


} // end class
