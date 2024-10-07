package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/PostPre")
public class PostPreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Connection Pool 역할을 하는 데이터소스 객체를 저장하는 필드
	private DataSource dataSource;
       
	
	public void init(ServletConfig config) 
		throws ServletException {
		log.info("init(config) invoked.");
		
		try {
			// JNDI라고 부르는 WAS 안에 나무의 뿌리(Root)를 얻어내는 코드
			Context ctx = new InitialContext();
			
			// JNDI lookup 을 통해, 나무에 있는 과실을 획득
//			Object obj = ctx.lookup("java:comp/env/jdbc/OracleLocalDB");
//			Object obj = ctx.lookup("java:comp/env/jdbc/OracleLocalDBWithDriverSpy");
			
//			Object obj = ctx.lookup("java:comp/env/jdbc/SharedLocalDB");
			Object obj = ctx.lookup("java:comp/env/jdbc/SharedLocalDBWithDriverSpy");
			
			Objects.requireNonNull(obj);
			log.info("\t+ obj: " + obj);
			
			this.dataSource = (DataSource) obj;	// 데이터소스객체 획득
		} catch(NamingException e) {
			e.printStackTrace();
		} // try-catch
		
	} // init
	
	@PostConstruct
	public void postConstruct() {
		log.info("postConstruct() invoked.");
		
	} // postConstruct

	
	//-----------------------------//
	
	
	public void destroy() {
		log.info("destroy() invoked.");
		
	} // destroy
	
	@PreDestroy
	public void preDestory() {
		log.info("preDestory() invoked.");
	} // preDestory

	
	//-----------------------------//

	
	protected void doGet(
			HttpServletRequest req, 
			HttpServletResponse res) 
				throws ServletException, IOException {
		log.info("doGet(req, res) invoked.");
		
		Connection conn = null;
		
		try {
			
			//--1. Connection Pool(DataSource)에서 DBConnection 얻기
			conn = dataSource.getConnection();			
			log.info("\t+ conn: "+ conn);
			
			//--2. Connection 객체로부터, Statement 객체 얻기
			String sql = "SELECT sysdate FROM dual";			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//--3. 조회SQL의 실행은 pstmt.executeQuery() 메소드로 수행
			ResultSet rs = pstmt.executeQuery();
			String now = null;
			if(rs.next()) {
				now = rs.getString("sysdate");
				log.info("\t+ now: " + now);
			} // if
			
			//-4. 클라이언트 웹브라우저에 응답문서로 HTML문서를 만들어 전송
			res.setContentType("text/html; charset=utf8");
			
			PrintWriter out = res.getWriter();
			
			out.println("<html><body>"+now+"</body></html>");
			
			out.flush();
			out.close(); 	// 자원반납
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new IOException(e);
		} finally {
			try {
				Objects.requireNonNull(conn);
				conn.close();
			} catch (SQLException e) { e.printStackTrace(); }
		} // try-with-finally
		
		
	} // doGet

} // end class
