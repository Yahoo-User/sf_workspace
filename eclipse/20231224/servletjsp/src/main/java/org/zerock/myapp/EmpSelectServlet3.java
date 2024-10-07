package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

@WebServlet("/EmpSelect3")
public class EmpSelectServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// JNDI Lookup을 통해 CP의 주소획득
	@Resource(name="jdbc/OracleCloudATP")
	private DataSource dataSource;						// CP객체의 주소를 저장
	
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		log.debug("this.dataSource: {}", this.dataSource);
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<html><head></head><body>");
				
		try {
			Connection conn = this.dataSource.getConnection();
			log.debug("\t+ conn: " + conn.getClass().getName());
			
			String sql = "SELECT empno, ename, sal, deptno FROM emp";
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			ResultSet rs = pstmt.executeQuery();
			
			try (conn; pstmt; rs;) {
			
				while(rs.next()) {
					String empno 	= rs.getString("empno");
					String ename 	= rs.getString("ename");
					String sal 		= rs.getString("sal");
					String deptno 	= rs.getString("deptno");
					
					// 응답문서로 출력
					out.println( String.format("<p>%s\t%s\t%s\t%s</p>", empno, ename, sal, deptno) );				
				} // while
			
			} // try-with-resources
		} catch(Exception e) {
			throw new IOException(e);
		} finally {
			out.println("</body></html>");
		} // try-catch-finally

		out.flush();
	} // service
	

} // end class
