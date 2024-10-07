package org.zerock.myapp.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.zerock.myapp.domain.EmpVO;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class EmpDAO2 {
	
//	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521/seoul";
	private String user = "scott";
	private String pass = "oracle";
	
	
	public EmpDAO2() {
//		try {
//			Class.forName(driver);
//		} catch(ClassNotFoundException e) {
//			e.printStackTrace();
//		}
	} // default constructor
	
	
	public List<EmpVO> select() throws SQLException {
		log.debug("select() invoked.");
		
		Connection conn =  DriverManager.getConnection(url, user, pass);
		log.info("\t+ conn: " + conn);
		
		conn.close();
		
		
		return null;
	} // select

} // end class
