package org.zerock.myapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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

@WebServlet("/EmpInsert")
public class EmpInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name="jdbc/OracleLocalDB")
	private DataSource dataSource;
	
	

	@Override
	public void init(ServletConfig config)
			throws ServletException {
		log.debug("init(config) invoked.");
		
		super.init(config);
		
		Objects.requireNonNull(dataSource);
		log.info("\t+ dataSource: " + dataSource);
	} // init
	
    
	@Override
	protected void service(
			HttpServletRequest req, HttpServletResponse res) 
				throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		// =========== 요청 처리 ================== //
		req.setCharacterEncoding("utf8");	// GET or POST 동일
		
		// 전송파라미터 획득(=request parameter) => 사용자가 전송한 데이터
		String empno = req.getParameter("empno");
		String ename = req.getParameter("ename");
		String sal	 = req.getParameter("sal");
		String deptno = req.getParameter("deptno");
		
		//============ 비지니스 로직 수행 ============//
		try {
			
			Connection conn = dataSource.getConnection();
			
			// Prepared SQL : ? - 바인드 변수라고 부른다
			String sql = 
				"INSERT INTO emp (empno, ename, sal, deptno) "+
				"VALUES(?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			try(conn; pstmt;) {
				
				// 바인드변수에 바인딩 한다 라고 부릅니다.
				pstmt.setInt(1, Integer.parseInt(empno));
				pstmt.setString(2, ename);
				pstmt.setDouble(3, Double.parseDouble(sal));
				pstmt.setInt(4, Integer.parseInt(deptno));
				
				int rows = pstmt.executeUpdate();	// DML, DQL : executeQuery()
				
				ServletContext sc = this.getServletContext();
				
				Objects.requireNonNull(sc);
				
				// Application Scope 에 "공유데이터(=속성) 바인딩" 수행
				// Object obj = rows;	// Auto-boxing(Integer객체)
				sc.setAttribute("ROWS", rows);
				
				
				//========= 응답문서를 만들자!! =========//
				// RequestDispatcher 객체는, 현재 요청처리에 대한 나머지 처리를
				// 지정된 웹컴포넌트에게 "위임(dispatch)"하게 해주는 객체
				RequestDispatcher dispatcher =
					req.getRequestDispatcher(
						"/WEB-INF/views/empinsert.jsp");
				
				//--- 요청처리를 위임하는 방법에는 아래와 같이, 2가지가 있다. 
				// (1) Request Forwarding 이라고 부른다
				dispatcher.forward(req, res);
				
				// (2) Request Including 이라고 부른다
//				dispatcher.include(req, res);
			} // try-with-resources
			
		} catch(SQLException e) {
			throw new IOException(e);
		} // try-catch
		
	} // service







} // end class
