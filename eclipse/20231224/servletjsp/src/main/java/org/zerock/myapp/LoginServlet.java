package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor(access=lombok.AccessLevel.PUBLIC)

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
//		= 1. 전송파라터 수신하기 ================
		
		String userid = req.getParameter("userid");
		String passwd = req.getParameter("passwd");
		
		log.info("\t+ userid: {}, passwd: {}", userid, passwd);
		
		
//		= 2. 응답문서 만들기 ===============
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<html><body>");
		out.println("아이디값: " + userid +"<br>");
		out.println("비밀번호값: " + passwd +"<br>");
		out.println("</body></html>");
		
		out.flush();
	} // service

} // end class
