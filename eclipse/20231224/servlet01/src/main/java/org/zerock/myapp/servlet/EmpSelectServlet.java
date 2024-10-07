package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/EmpSelect")
public class EmpSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String driver;
	private String jdbcUrl;
	private String user;
	private String pass;
	
	static final String sql  = "SELECT empno, ename, sal, deptno FROM emp";
       


	@Override
	public void init(ServletConfig config) throws ServletException {
		log.debug("init(config) invoked.");
		
		try {
			//-------------------------------------------------//
			// web.xml 에 정의한 서블릿 초기화 파라미터 획득
			//-------------------------------------------------//
			this.driver 	= config.getInitParameter("driver");
			this.jdbcUrl 	= config.getInitParameter("jdbcUrl");
			this.user 		= config.getInitParameter("user");
			this.pass 		= config.getInitParameter("pass");
			
			log.info("\t+ driver: " + this.driver);
			log.info("\t+ jdbcUrl: " + this.jdbcUrl);
			log.info("\t+ user: " + this.user);
			log.info("\t+ pass: " + this.pass);
			
			
			//-------------------------------------------------//
			// web.xml 에 정의한 Context Parameter의 값을 얻으려면,
			// ServletContext 객체가 필요하다!!!
			//-------------------------------------------------//
//			ServletContext sc = config.getServletContext();			
//			String contextParameter = sc.getInitParameter("Parameter Name");
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // init
	
	
	
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		try {
			//--1. JDBC driver loading
			Class.forName(driver);
			
			//--2. JDBC 연동코드
			Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			try(conn; pstmt; rs;) {
				
				res.setContentType("text/html; charset=utf8");
				PrintWriter out = res.getWriter();
				
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("	<title>사원목록</title>");
				out.println("</head>");
				out.println("<body>");
				
				try(out) {
					
					out.println("	<h1>전체 사원목록</h1>");
					
					//--3. 결과셋으로 각 레코드별로 컬럼값 추출 / 출력
					while(rs.next()) {
						String empno = rs.getString("EMPNO");
	     				String ename = rs.getString("ENAME");
	     				String sal = rs.getString("SAL");
	     				String deptno = rs.getString("DEPTNO");
	     				
	     				out.print(empno+"\t"+ename +"\t"+sal +"\t" +deptno +"<br>");
					} // while
					
					out.println("</body>");
					out.println("</html>");
					
					out.flush();
				} // try-with-resources
				
			} // try-with-resources
			
		} catch(Exception e) {
			throw new IOException(e);
		} // try-catch
	} // service

} // end class
