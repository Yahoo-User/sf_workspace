package org.zerock.myapp.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
import org.zerock.myapp.config.RootContextXml;
import org.zerock.myapp.domain.EmployeeVO;

import lombok.NoArgsConstructor;
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

@ContextConfiguration(classes = RootContextXml.class )
public class LastDynamicSQLTests {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	

	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		Objects.requireNonNull(this.sqlSessionFactory);
		log.info("\t+ sqlSessionFactory: " + this.sqlSessionFactory);
	} // beforeAll
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testFindEmployeesByEmpIds")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testFindEmployeesByEmpIds() {
		log.debug("testFindEmployeesByEmpIds() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		try(sqlSession) {
			
			String namespace = "LastDynamicSQLTest";
			String sqlId = "findEmployeesByEmpIds";
			
			List<Integer> empIds = Arrays.asList(197, 198, 199, 200);
			
			List<EmployeeVO> employees = 
				sqlSession.<EmployeeVO>selectList(namespace+"."+sqlId, empIds);
			
			employees.forEach(log::info);	// 메소드 참조(람다식의 생략 끝판왕!!)
			
			employees.clear();
			employees = null;
		} // try-with-resources
	} // testFindEmployeesByEmpIds

	

} // end class
