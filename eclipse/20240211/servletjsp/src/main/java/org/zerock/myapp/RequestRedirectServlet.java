package org.zerock.myapp;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/RequestRedirect")
public class RequestRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		// Step.1 비지니스 데이터(Model)를 Request Scope에 속성바인딩
//		req.setAttribute("name", "홍길동");
//		req.setAttribute("address", "서울");
		
//		HttpSession sess = req.getSession();
//		sess.setAttribute("name", "홍길동");
//		sess.setAttribute("address", "서울");
		
		// Step2. Redirect 응답 전송
//		URLEncoder.encode("문자열");						// Deprecated
//		URLEncoder.encode("문자열", "utf-8");	// OK
		
		String queryStr = "?name="+URLEncoder.encode("홍길동", "utf-8")+"&address="+URLEncoder.encode("서울", "utf-8");
		log.info("\t+ queryStr: {}", queryStr);
		
		res.sendRedirect("/ResponseRedirect"+queryStr);
		log.info("Succeed to send a Redirection Resonse to the web browser.");
	} // service

} // end class
