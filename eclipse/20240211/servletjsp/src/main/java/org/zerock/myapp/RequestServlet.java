package org.zerock.myapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

// MVC 패턴에서 Controller 역할
@WebServlet("/Request")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		// Step.1 비지니스 로직 수행
		
		// Step.2 비지니스 데이터(== Model)를 request scope 공유데이터 영역에 올려 놓음
		req.setAttribute("name", "홍길동");
		req.setAttribute("address", "서울");
		
//		---- 응답을 만들어 낼 웹컴포넌트(예: Servlet)에 요청을 위임 (View)
		// Step.3 요청 위임(by request forwarding)
		
		// // 두번째 응답 서블릿으로 포워딩
		RequestDispatcher dispatcher = req.getRequestDispatcher("/Response");
		dispatcher.forward(req, res);
		
		log.info("Forwarded request to the /Response.");
	} // service

} // end class
