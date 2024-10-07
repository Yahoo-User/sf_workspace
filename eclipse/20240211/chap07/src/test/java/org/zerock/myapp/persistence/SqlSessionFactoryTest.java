package org.zerock.myapp.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
	

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(sqlSessionFactory);
		
		log.info("\t+ sqlSessionFactory: " + sqlSessionFactory);
	} // beforeAll
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testSqlSessionFactory")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
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
	

//	@Disabled
	@Test
	@Order(2)
	@DisplayName("testSQLMapper1")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
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
	

//	@Disabled
	@Test
	@Order(3)
	@DisplayName("testSQLMapper2")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
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

} // end class
