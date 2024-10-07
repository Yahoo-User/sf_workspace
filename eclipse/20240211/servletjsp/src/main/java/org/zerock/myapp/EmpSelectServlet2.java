package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebServlet("/EmpSelect2")
public class EmpSelectServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String jdbcUrl;
	private String driverClass;
	private String user;
	private String pass;
	
	private Connection conn;
		
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.trace("init(config) invoked.");
		
		super.init(config);
		
		this.jdbcUrl = config.getInitParameter("jdbcUrl");
		this.driverClass = config.getInitParameter("driverClass");
		this.user = config.getInitParameter("user");
		this.pass = config.getInitParameter("pass");
		
		log.debug("\t+ jdbcUrl: {}", jdbcUrl);
		log.debug("\t+ driverClass: {}, user: {}, pass: {}", driverClass, user, pass);
		
//		-------
		
		try {
			Class.forName(driverClass);			
			Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);
			log.debug("\t+ conn: " + conn);
			
			this.conn = conn;
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<html><head></head><body>");
				
		try {			
			String sql = "SELECT empno, ename, sal, deptno FROM emp";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			try (pstmt; rs;) {
			
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

	
	@Override
	public void destroy() {
		log.trace("destroy() invoked.");
		
		try { if(conn != null && !conn.isClosed()) conn.close(); }
		catch(SQLException e) {;;}
	} // destroy

} // end class
