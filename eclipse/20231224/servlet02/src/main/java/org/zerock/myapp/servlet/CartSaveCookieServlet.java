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

@WebServlet("/CartSaveCookie")
public class CartSaveCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	@Override
	protected void service(
			HttpServletRequest req, HttpServletResponse res) 
				throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		
		String product = req.getParameter("product");
		
		Cookie[] cookies = req.getCookies();
		
		Cookie c = null;
		
		if(cookies == null || cookies.length ==0) {
			c = new Cookie("product", product);
		} else {
			c = new Cookie("product"+(cookies.length + 1), product);
		} // if-else
		
//		c.setMaxAge(60 * 60);	// 1시간동안 유지하라!(어디?브라우저에서)
		
		// 브라우저에 전송시킨 쿠키를 저장하라!!!
		res.addCookie(c);		// 응답문서에 새로운 쿠키를 추가하여 응답으로 보냄!
		
		res.setContentType("text/html; charset=utf8");
		
		PrintWriter out = res.getWriter();
		
		out.print("<html><body>");
		out.print("Product 추가<br>");
		out.print("<a href='CartBasketCookie'>장바구니 보기</a>");
		out.print("</body></html>");
		
		out.flush();
		out.close();
	} // service

} // end class
