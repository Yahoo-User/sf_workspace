package org.zerock.myapp.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.sql.DataSource;

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
import org.zerock.myapp.config.RootContextXml;

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

@ContextConfiguration(classes= RootContextXml.class)
public class DataSourceTests {

//	@Setter(onMethod_= { @Autowired })
//	@Setter(onMethod_= { @Inject })
	
//	@Setter(onMethod_= { @Resource })
//	@Setter(onMethod_= { @Resource(type=DataSource.class) })
	
	
//	@Setter(onMethod_= @Autowired)
//	@Setter(onMethod_= @Inject)
	
	
//	@Setter(onMethod_= @Resource)
//	@Setter(onMethod_= @Resource(type=DataSource.class))
	
//	@Inject
//	@Autowired
	
//	@Resource
	@Resource(type = DataSource.class)
	private DataSource dataSource;
	

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(dataSource);
		
		log.info("\t+ dataSource: " + dataSource);
	} // beforeAll
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testHikariCP")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testHikariCP() throws SQLException {
		log.debug("testHikariCP() invoked.");
		
		Connection conn = dataSource.getConnection();
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
		
	} // testHikariCP
	

//	@Disabled
	@Test
	@Order(2)
	@DisplayName("testPooledDataSource")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testPooledDataSource() throws SQLException {
		log.debug("testPooledDataSource() invoked.");
		
		Connection conn = dataSource.getConnection();
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
		
	} // testPooledDataSource

	
	

} // end class
