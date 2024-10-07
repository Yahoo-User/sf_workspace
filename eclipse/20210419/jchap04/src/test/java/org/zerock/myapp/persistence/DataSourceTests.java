package org.zerock.myapp.persistence;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.config.RootContextXml;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)

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
	
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		assertNotNull(dataSource);
		
		log.info("\t+ dataSource: " + dataSource);
	} // setup
	
	
	@Test
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
	
	
	@Test
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
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");

	} // tearDown

} // end class
