package org.zerock.myapp.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor
public class JDBCTests {
	
//	private static final String localJdbcUrl = "jdbc:oracle:thin:@seoul";
	private static final String localJdbcUrl = "jdbc:log4jdbc:oracle:thin:@seoul";
	
//	private static final String cloudJdbcUrl = "jdbc:oracle:thin:@atp20191201_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP";
	private static final String cloudJdbcUrl = "jdbc:log4jdbc:oracle:thin:@atp20191201_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP";
	
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
	} // setup
	
	
//	@Test(timeout=1000)
	@Test
	public void testPDBConnection() throws SQLException {
		log.debug("testPDBConnection() invoked.");
		
		Connection conn = DriverManager.getConnection(localJdbcUrl, "HR", "oracle");
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

	} // testPDBConnection
	
	
//	@Test(timeout=3000)
	@Test
	public void testCloudConnection() throws SQLException {
		log.debug("testCloudConnection() invoked.");
		
		Connection conn = DriverManager.getConnection(cloudJdbcUrl, "HR", "Oracle12345!!!");
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

	} // testCloudConnection
	
	
	@After
	public void tearDown() throws SQLException {
		log.debug("tearDown() invoked.");
		
	} // tearDown
	
} // end class
