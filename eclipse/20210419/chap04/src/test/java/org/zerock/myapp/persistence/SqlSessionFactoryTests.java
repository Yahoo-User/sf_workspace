package org.zerock.myapp.persistence;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.EmployeeVO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
	"file:src/main/webapp/WEB-INF/spring/root-*.xml"
})
public class SqlSessionFactoryTests {
	
	@Setter(onMethod_= {@Autowired})
	private SqlSessionFactory sqlSessionFactory;
	
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		assertNotNull(this.sqlSessionFactory);
		log.info("\t+ sqlSessionFactory: " + this.sqlSessionFactory);
	} // setup
	
	
	@Test
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
	
	
	@Test
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
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // tearDown

} // end class
