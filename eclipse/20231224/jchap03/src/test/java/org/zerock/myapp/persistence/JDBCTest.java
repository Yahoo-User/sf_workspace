package org.zerock.myapp.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JDBCTest {
	
//	private static final String localJdbcUrl = "jdbc:oracle:thin:@seoul";
	private static final String cloudJdbcUrl = "jdbc:oracle:thin:@atp20191201_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP";	
	

	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testConnection")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	public void testConnection() throws SQLException {
		log.debug("testConnection() invoked.");
		
//		Connection conn = DriverManager.getConnection(localJdbcUrl, "HR", "oracle");
		Connection conn = DriverManager.getConnection(cloudJdbcUrl, "HR", "Oracle12345678");
		
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
		
	} // testDI
	
	
	
} // end class
