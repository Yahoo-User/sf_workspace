package org.zerock.myapp.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.myapp.domain.EmployeeVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor

@RunWith(SpringRunner.class)
@ContextConfiguration(locations= {
	"file:src/main/webapp/WEB-INF/spring/**/root*.xml"
})
public class LastDynamicSQLTests {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		Objects.requireNonNull(this.sqlSessionFactory);
		log.info("\t+ sqlSessionFactory: " + this.sqlSessionFactory);
	} // setup
	
	
	@Test
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
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // After

} // end class
