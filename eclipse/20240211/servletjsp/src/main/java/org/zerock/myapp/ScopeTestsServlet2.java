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

@WebServlet("/ScopeTests2")
public class ScopeTestsServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;



	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		
		ServletContext appScope = req.getServletContext();	// App. Scope 관리객체
		HttpSession sessionScope = req.getSession();		// Session Scope 관리객체
		HttpServletRequest reqScope = req;					// Req. Scope 관리객체
		
//		======================

		// 1. Application Scope Test.
		String getSharedDataFromAppScope = (String) appScope.getAttribute("APP_SCOPE");
		
//		======================

		// 2. Session Scope Test.
		String getSharedDataFromSessionScope = (String) sessionScope.getAttribute("SESSION_SCOPE");

//		======================
		
		// 3. Request Scope Test.
		String getSharedDataFromRequestScope = (String) reqScope.getAttribute("REQUEST_SCOPE");
		

//		======================
//		응답문서 생성 및 전송
//		======================
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
//		-- 특정 공유영역에서 얻어낸 공유데이터를 출력 --
		String appScopeData = getSharedDataFromAppScope;					// From App.Scope
		String sessionScopeData = getSharedDataFromSessionScope;			// From Session.Scope
		String requestScopeData = getSharedDataFromRequestScope;			// From Request.Scope
		
		out.println("<h1>"+appScopeData+"</h1>");
		out.println("<h1>"+sessionScopeData+"</h1>");
		out.println("<h1>"+requestScopeData+"</h1>");
		
		out.flush();
	} // service

} // end class
