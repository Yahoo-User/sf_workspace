package org.zerock.myapp.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



/**
 * Handles requests for the application home page.
 */
@Log4j2
@NoArgsConstructor

@Controller("homeController")
public class HomeController
	implements InitializingBean, DisposableBean {
	
	@Resource(lookup="java:comp/env/jdbc/OracleCloud")		// JNDI lookup
	private DataSource dataSource;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info(String.format("Welcome home! The client locale is %s.", locale));
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	} // home
	
	
	//============================================================//

	@Override
	public void destroy() throws Exception {
		log.debug("destroy() invoked.");

		
	} // destroy


	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("afterPropertiesSet() invoked.");
		
		log.info("\t+ dataSource: " + dataSource);
		
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
		
		try(conn) {
			log.info("\t+ conn: " + conn);
			
			try(stmt) {
				
				try(rs) {
					
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
				
				log.info("\t+ rs.isClosed(): " + rs.isClosed());
				
			} // try-with-resources
			
			log.info("\t+ stmt.isClosed(): " + stmt.isClosed());
			
		} // try-with-resources
		
		log.info("\t+ conn.isClosed(): " + conn.isClosed());
	} // afterPropertiesSet
	
} // end class
