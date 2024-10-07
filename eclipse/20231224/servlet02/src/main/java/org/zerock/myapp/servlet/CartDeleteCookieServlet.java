package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/CartDeleteCookie")
public class CartDeleteCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(
			HttpServletRequest req, HttpServletResponse res) 
				throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		
		Cookie[] basket = req.getCookies();
		
		if(basket != null) {
			
			// 클라이언트 브라우저에 저장된 모든 쿠키(=현재 브라우저)
			for(Cookie c : basket) {
				c.setMaxAge(1);			// 각 쿠키의 삭제
				
				res.addCookie(c);		// 시간만료가 설정된 쿠키를 브라우저로 전송
			} // enhanced for
			
		} // if
		
		//--------------- 응답문서 생성 및 전송 ------------//
		res.setContentType("text/html; charset=utf8");
		
		PrintWriter out = res.getWriter();
		
		out.print("<a href='product.html'>상품 선택 페이지</a>");
		
		out.flush();
		out.close();
	} // service

} // end class
