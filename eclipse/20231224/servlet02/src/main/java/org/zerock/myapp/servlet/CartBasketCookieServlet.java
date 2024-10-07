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

@WebServlet("/CartBasketCookie")
public class CartBasketCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	@Override
	protected void service(
			HttpServletRequest req, HttpServletResponse res) 
				throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		
		Cookie[] baskets = req.getCookies();
		
		
		res.setContentType("text/html; charset=utf8");
		
		PrintWriter out = res.getWriter();
		
		if(baskets == null) {
			out.print("장바구니비었음<br>");
		} else {
			
			for(Cookie c : baskets) {
				String name = c.getName();
				String value = c.getValue();
				
				out.print(name + " : " + value + "<br>");
			} // enhanced for
			
		} // if-else
		
		out.print("<a href='product.html'>상품 선택 페이지</a><br>");
		out.print("<a href='CartDeleteCookie'>장바구니 비우기</a>");
		
		out.flush();
		out.close();
	} // service

} // end class
