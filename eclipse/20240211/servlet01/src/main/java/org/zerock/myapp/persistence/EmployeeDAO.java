package org.zerock.myapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.zerock.myapp.domain.EmpVO;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class EmployeeDAO {
	
//	@Resource(name="jdbc/OracleLocalDB")		// XX : 작동안함
	private DataSource dataSource;
	
	
	public EmployeeDAO() {	// JNDI lookup 을 통한 DataSource 획득
		try {
			Context ctx = new InitialContext();
			
			this.dataSource = (DataSource) 
					ctx.lookup("java:comp/env/jdbc/OracleLocalDB");
			
			log.info("\t+ dataSource: " + dataSource);
		} catch(NamingException e) {
			e.printStackTrace();
		} // try-catch
	} // default constructor
	
	
	
	
	// Value Object 패턴: 한 테이블의 한 개의 레코드를 읽기전용 객체로 만들어서
	//                  반환하는 패턴
	public EmpVO select(Integer empno) throws SQLException {
		log.debug("select(empno) invoked.");
		
		try {
			Connection conn = dataSource.getConnection();
			log.info("\t+ conn: " + conn);
			
			String sql = "SELECT * FROM emp WHERE empno = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			
			// SELECT문장: executeQuery() 로 수행
			// DML문장 : executeUpdate() 로 수행
			ResultSet rs = pstmt.executeQuery();
			
			EmpVO empVO = null;
			
			try (conn; pstmt; rs;) {
				
				if(rs.next()) {
					String ename = rs.getString("ename");				
					String job = rs.getString("job");
					Integer mgr = rs.getInt("mgr");
					Timestamp hireDate = rs.getTimestamp("hiredate");
					Double sal = rs.getDouble("sal");
					Double comm = rs.getDouble("comm");
					Integer deptno = rs.getInt("deptno");				
					
					empVO = new EmpVO(
							empno, ename, job, mgr, 
							hireDate, sal, comm, deptno
						);
				} // if
				
			} // try-with-resource
			
			return empVO;
		
		} catch(Exception e) {
			throw new SQLException(e);
		} // try-catch
	} // select
	

} // end class
