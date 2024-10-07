package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/EmpInsert")
public class EmpInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// JNDI Lookup을 통해 CP의 주소획득
	@Resource(name="jdbc/OracleCloudATPWithDriverSpy")
	private DataSource dataSource;						// CP객체의 주소를 저장
	
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
//		==========================
//		전송파라미터(Request Parameters) 획득
//		==========================
		
		String empno = req.getParameter("empno");
		String ename = req.getParameter("ename");
		String sal = req.getParameter("sal");
		String deptno = req.getParameter("deptno");

		
//		==========================
//		응답화면 생성 및 전송
//		==========================
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<html><head></head><body>");
				
		try {
			Connection conn = this.dataSource.getConnection();
			log.debug("\t+ conn: " + conn.getClass().getName());
			
			String sql = "INSERT INTO emp (empno, ename, sal, deptno) VALUES (?, ?, ?, ?)";	// DML
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(empno));
			pstmt.setString(2, ename);
			pstmt.setDouble(3, Double.parseDouble(sal));
			pstmt.setInt(4, Integer.parseInt(deptno));
			
			int affectedLines = pstmt.executeUpdate();
			log.info("\t+ affectedLines: {}", affectedLines);
			
			try (conn; pstmt) {				
				// 응답문서로 출력
				out.println( String.format("<p>affectedLines: %s</p>", affectedLines) );								
			} // try-with-resources
		} catch(Exception e) {
			throw new IOException(e);
		} finally {
			out.println("</body></html>");
			out.flush();
		} // try-catch-finally
	} // service
	
} // end class
