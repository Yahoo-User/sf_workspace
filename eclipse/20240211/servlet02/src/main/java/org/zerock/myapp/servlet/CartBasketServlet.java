package org.zerock.myapp.servlet;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/CartBasket")
public final class CartBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@Override
	protected void service(
			HttpServletRequest req, HttpServletResponse res) 
				throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		// 이미 Session Scope 공유영역에는, 첫번재 서블릿인 CartSave에 의해서
		// List 객체가 속성이름 product로 바인딩 되어있음.
		
		// Request.getSession(false): 세션이 이미 있으면 되돌려주고, 없으면 null 리턴
		HttpSession sess = req.getSession(false);
		log.info("\t+ sess: " + sess);
		
		if(sess != null) {
			@SuppressWarnings("unchecked")
			List<String> baskets = (List<String>) sess.getAttribute("product");
			
			baskets.forEach(log::info);	// 메소드 참조
		} // if
		
		//--------- 응답문서 생성 및 전송은, JSP에서 하자(MVC패턴) ---/
		
		RequestDispatcher dispatcher = 
			req.getRequestDispatcher("/WEB-INF/views/cartbasket.jsp");
		
		dispatcher.forward(req, res);
	} // service

} // end class
