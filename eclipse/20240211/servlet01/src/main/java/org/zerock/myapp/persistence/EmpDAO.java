package org.zerock.myapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.zerock.myapp.domain.EmpVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class EmpDAO {
	
	private static DataSource ds;
	
	
	
	static {	// JNDI lookup 통해서, DataSource 객체를 얻기.
		
		try {
			Context ctx = new InitialContext();	// JNDI root 얻는 부분
			
			EmpDAO.ds = (DataSource) // JNDI 지정된 이름의 객체를 얻는 부분
				ctx.lookup("java:comp/env/jdbc/OracleCloud");
			
			Objects.requireNonNull(EmpDAO.ds);
			
			log.info("EmpDAO.ds: " + EmpDAO.ds);
		} catch(NamingException e) {
			e.printStackTrace();
		} // try-catch
		
	} // static initializer
	
	
	public List<EmpVO> select() throws SQLException {
		log.debug("select() invoked.");
				
		List<EmpVO> list = null;
		
		Connection conn = ds.getConnection();

		String sql = "select empno, ename, sal, deptno from emp";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		try (conn; pstmt; rs;)  {
			
			list = new ArrayList<>();
			
			while(rs.next()){
				Integer empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				Double sal = rs.getDouble("sal");
				Integer deptno = rs.getInt("deptno");
			
				EmpVO vo = new EmpVO(empno, ename, null, null, null, sal, null, deptno);
				
				list.add(vo);
			} // while
			
		} // try-with-resources	
				
		return list;
	} // select

} // end class
