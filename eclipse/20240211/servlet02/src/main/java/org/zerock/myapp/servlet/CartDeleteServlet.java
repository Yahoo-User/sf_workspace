package org.zerock.myapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/CartDelete")
public final class CartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	@Override
	protected void service(
			HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		HttpSession sess = req.getSession(false);
		log.info("\t+ sess: " + sess);
		log.info("\t+ session ID: " + sess.getId()); // 브라우저의 이름
		
		if(sess != null) {	// 이미 세션이 있으면
			sess.invalidate(); // 기존 세션을 완전히 파괴!!!!
			
			log.info("\t ********* 세션 파괴 *********");
		} // if
		
		//----- 응답문서 생성 -----//
		RequestDispatcher dispatcher =
			req.getRequestDispatcher("/WEB-INF/views/cartdelete.jsp");
		
		dispatcher.forward(req, res);
	} // service

} // end class
