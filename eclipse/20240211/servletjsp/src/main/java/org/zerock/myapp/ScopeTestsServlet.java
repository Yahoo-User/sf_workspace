package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.Cleanup;
import lombok.NoArgsConstructor;


@NoArgsConstructor

@WebServlet("/ScopeTests")
public class ScopeTestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		
		ServletContext appScope = req.getServletContext();	// App. Scope 관리객체
		HttpSession sessionScope = req.getSession();		// Session Scope 관리객체
		HttpServletRequest reqScope = req;					// Req. Scope 관리객체
		
//		======================
		
		String sharedData = "__SHARED_DATA__";				// Scope에서 공유할 데이터
		
//		======================

		// 1. Application Scope Test.
		appScope.setAttribute("APP_SCOPE", sharedData);
		
//		======================

		// 2. Session Scope Test.
		sessionScope.setAttribute("SESSION_SCOPE", sharedData);
		
//		======================

		// 3. Request Scope Test.
		reqScope.setAttribute("REQUEST_SCOPE", sharedData);

//		======================
//		응답문서 생성 및 전송
//		======================
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
//		out.println("<h1>APP. SCOPE에 공유데이터를 올려 놓았습니다.</h1>");
//		out.println("<h1>SESSION. SCOPE에 공유데이터를 올려 놓았습니다.</h1>");
		out.println("<h1>REQUEST. SCOPE에 공유데이터를 올려 놓았습니다.</h1>");
		
		out.flush();
	} // service

} // end class
