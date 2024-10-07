package org.zerock.myapp.persistence;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.EmployeeVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations= { 
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class SqlSessionFactoryTest {
	
//	@Setter(onMethod_= { @Autowired })
//	@Setter(onMethod_= { @Inject })
	
//	@Setter(onMethod_= { @Resource })
//	@Setter(onMethod_= { @Resource(type=SqlSessionFactory.class) })
	
	
//	@Setter(onMethod_= @Autowired)
//	@Setter(onMethod_= @Inject)
	
	
//	@Setter(onMethod_= @Resource)
//	@Setter(onMethod_= @Resource(type=SqlSessionFactory.class))
	
//	@Inject
//	@Autowired
	
//	@Resource
	@Resource(type = SqlSessionFactory.class)
	private SqlSessionFactory sqlSessionFactory;
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		assertNotNull(sqlSessionFactory);
		
		log.info("\t+ sqlSessionFactory: " + sqlSessionFactory);
	} // setup
	
	
//	@Test
	@Test(timeout=1000)
	public void testSqlSessionFactory() {
		log.debug("testSqlSessionFactory() invoked.");
		
		SqlSession session = sqlSessionFactory.openSession();
		
		try(session) {
			
			log.info("\t+ session: " + session);
			
			Connection conn = session.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
			
			try(conn; stmt; rs;) {
				log.info("\t+ conn: " + conn);
				log.info("\t+ stmt: " + stmt);
				log.info("\t+ rs: " + rs);

						
				while(rs.next()) {
					String employee_id = rs.getString("EMPLOYEE_ID");
					String first_name = rs.getString("FIRST_NAME");
					String last_name = rs.getString("LAST_NAME");
					String email = rs.getString("EMAIL");
					String phone_number = rs.getString("PHONE_NUMBER");
					String hire_date = rs.getString("HIRE_DATE");
					String job_id = rs.getString("JOB_ID");
					String salary = rs.getString("SALARY");
					String commission_pct = rs.getString("COMMISSION_PCT");
					String department_id = rs.getString("DEPARTMENT_ID");
					
					String employee = String.format(
							"%s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
							employee_id, first_name, last_name, email, phone_number,
							hire_date, job_id, salary, commission_pct, department_id);
					
					log.info(employee);
				} // while
				
			} // try-with-resources
			
		} catch(SQLException e) {
			e.printStackTrace();
		} // try-with-resources
		
	} // testSqlSessionFactory
	
	
	@Test
	public void testSQLMapper1() {
		log.debug("testSQLMapper1() invoked.");
		
		//---------------------------------------//
		Object obj = true;
		log.info(obj.getClass().getName());
		//---------------------------------------//
		
		SqlSession session = sqlSessionFactory.openSession();
		List<EmployeeVO> emps = session.selectList("sqlmapper1.DQL1", 150);
		
//		emps.forEach(e -> {
//			
//			String employee = String.format(
//					"%s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
//					e.getEmployeeId(),
//					e.getFirstName(),
//					e.getLastName(),
//					e.getEmail(),
//					e.getPhoneNumber(),
//					e.getHireDate(),
//					e.getJobId(),
//					e.getSalary(),
//					e.getCommissionPct(),
//					e.getDepartmentId()
//				);
//			
//			log.info(employee);
//			
//		}); // forEach
		
		emps.forEach(log::info);
		
		emps.clear();
		emps = null;
	} // testSQLMapper1
	
	
	@Test
	public void testSQLMapper2() {
		log.debug("testSQLMapper2() invoked.");
		
		SqlSession session = sqlSessionFactory.openSession();
		
		
		//----------------------------------------------------//
//		Map<String, String> params = new HashMap<>();
//		params.put("email", "L%");
//		params.put("salary", "150");
		//----------------------------------------------------//
		

		//----------------------------------------------------//
		@SuppressWarnings("unused")
		class Parameters {
			
			private String email;
			private Integer salary;

			
			String getEmail() {
				return email;
			} // getEmail
			
			Integer getSalary() {
				return salary;
			} // getSalary
			
			void setEmail(String email) {
				this.email = email;
			} // setEmail
			
			void setSalary(Integer salary) {
				this.salary = salary;
			} // setSalary
			
		} // end class
		
		
		Parameters params = new Parameters();
		params.setEmail("L%");
		params.setSalary(150);
		//----------------------------------------------------//
		
		
		List<EmployeeVO> emps = session.selectList("sqlmapper2.DQL2", params);
		
//		emps.forEach(e -> {
//			
//			String employee = String.format(
//					"%s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
//					e.getEmployeeId(),
//					e.getFirstName(),
//					e.getLastName(),
//					e.getEmail(),
//					e.getPhoneNumber(),
//					e.getHireDate(),
//					e.getJobId(),
//					e.getSalary(),
//					e.getCommissionPct(),
//					e.getDepartmentId()
//				);
//			
//			log.info(employee);
//			
//		});	// forEach
		
		emps.forEach(log::info);
		
		emps.clear();
		emps = null;
	} // testSQLMapper2
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");

	} // tearDown

} // end class
