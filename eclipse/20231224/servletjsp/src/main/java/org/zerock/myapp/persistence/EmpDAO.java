package org.zerock.myapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.domain.EmpVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class EmpDAO {	// POJO : Plain Old Java Object

	// Servlet 클래스의 내부에서는, 아래의 어노테이션으로 자원객체를 JNDI tree로부터
	// 얻어낼 수 있지만, Servlet 클래스 외부에서는 안됨!!!! (***)
//	@Resource(name="jdbc/OracleCloudATPWithDriverSpy")
	
	private static DataSource dataSource;
	
	
	static {	// JNDI lookup 을 해서, DataSource 객체의 주소를 획득
		
		try {
			Context ctx = new InitialContext();
			
			Object obj = ctx.lookup("java:comp/env/jdbc/OracleCloudATPWithDriverSpy");
			dataSource = (DataSource) obj;
		} catch (NamingException e) {;;}
		
	} // static initializer 
	
	
	public List<EmpVO> selectAll() throws SQLException {
		log.trace("selectAll() invoked.");
		
		List<EmpVO> list = new ArrayList<>(); 
				
		try {
			Connection conn = dataSource.getConnection();
			
			String sql = "SELECT empno, ename, sal, deptno FROM emp";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			try (conn; pstmt; rs) {
				while(rs.next()) {
					Integer empno = rs.getInt("empno");
					String ename = rs.getString("ename");
					Double sal = rs.getDouble("sal");
					Integer deptno = rs.getInt("deptno");
					
					// 1 개의 테이블의 1개의 레코드를 읽기전용으로 보관하는 객체 => VO (값객체)
					EmpVO vo = new EmpVO(empno, ename, sal, deptno);
					
					list.add(vo);
				} // while				
			} // try-with-resources
			
		} catch(Exception e) {
			throw new SQLException(e);
		} // try-catch		
		
		return list;
	} // select
	
	
	public int insert(EmpDTO dto) throws SQLException {
		log.trace("insert({}) invoked.", dto);
				
		try {
			Connection conn = dataSource.getConnection();
			
			String sql = "INSERT INTO emp (empno, ename, sal, deptno) VALUES (?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 	dto.getEmpno());
			pstmt.setString(2, 	dto.getEname());
			pstmt.setDouble(3, 	dto.getSal());
			pstmt.setInt(4, 	dto.getDeptno());
			
			int affectedLines = pstmt.executeUpdate();
			
			try (conn; pstmt) {
				return affectedLines;				
			} // try-with-resources
		} catch(Exception e) {
			throw new SQLException(e);
		} // try-catch
	} // insert

	
	public int update(EmpDTO dto) throws SQLException {
		log.trace("update({}) invoked.", dto);
		
		try {
			Connection conn = dataSource.getConnection();
			
			String sql = "UPDATE emp SET ename = ?, sal = ?, deptno = ? WHERE empno = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 	dto.getEname());
			pstmt.setDouble(2, 	dto.getSal());
			pstmt.setInt(3, 	dto.getDeptno());
			pstmt.setInt(4, 	dto.getEmpno());
			
			int affectedLines = pstmt.executeUpdate();
			
			try (conn; pstmt) {
				return affectedLines;				
			} // try-with-resources
		} catch(Exception e) {
			throw new SQLException(e);
		} // try-catch
	} // insert

	
	public int delete(EmpDTO dto) throws SQLException {
		log.trace("delete({}) invoked.", dto);
		
		try {
			Connection conn = dataSource.getConnection();
			
			String sql = "DELETE FROM emp WHERE empno = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getEmpno());
			
			int affectedLines = pstmt.executeUpdate();
			
			try (conn; pstmt) {
				return affectedLines;				
			} // try-with-resources
		} catch(Exception e) {
			throw new SQLException(e);
		} // try-catch
	} // insert
	
} // end class
