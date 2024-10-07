package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/CartSaveCookie")
public class CartSaveCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.debug("service(req, res) invoked.");		
		
		// Step.1 화면에서 전송한, 장바구니에 저장할 품목(전송파라미터)을 획득
		String product = req.getParameter("product");
		
		
		// Step.2 요청메시지(Request Message) 의 헤더에 포함되어 있는, 모든 쿠키 획득
		Cookie[] cookies = req.getCookies();	// Cookie 타입의 객체를 원소로 하는 객체배열 반환
		
		
		// Step.3 우리가 직접 새로운 쿠키를 생성하자!!
		Cookie cookie = null;
		
		if(cookies == null || cookies.length ==0) {	// 위 Step.2에서 얻는 쿠키 배열이 무효하다면...
			cookie = new Cookie("product", product);
		} else {	// 위 Step.2 에서 얻은 쿠키 배열이 유효하다면...
			cookie = new Cookie("product"+(cookies.length + 1), product);
		} // if-else
		
		
		// Step.4 우리가 생성한 쿠키의 만료기간 설정
		cookie.setMaxAge(60 * 60);	// in seconds, 1시간동안 유지하라!(어디?브라우저에서)
		
		
		// Step.5 응답메시지의 헤더에, 우리가 위 step.3 에서 생성한 쿠키객체를 저장
		res.addCookie(cookie);		// 응답문서에 새로운 쿠키를 추가하여 응답으로 보냄!
		
		
		// Step.6 응답문서 생성 및 전송
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup	
		PrintWriter out = res.getWriter();
		
		out.print("<html><body>");
		
			out.print("Product 추가<br>");
			out.print("<a href='CartBasketCookie'>장바구니 보기</a>");
		
		out.print("</body></html>");
		
		out.flush();
	} // service

} // end class
