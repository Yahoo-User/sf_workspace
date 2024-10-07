package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/CartBasket")
public class CartBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		List<String> list = null;
		
		try {
			HttpSession sess = req.getSession(false);
			log.info("\t+ sess: {}", sess);
			
			Objects.requireNonNull(sess);
			
			list = (List<String>) sess.getAttribute("basket");
			
			Objects.requireNonNull(list);
			
			list.forEach(log::info);
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
		
//		=================
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<h1>장바구니 내용</h1>");
		
		out.println("<ol>");
		
		list.forEach(s -> {
			out.println("\t<li>"+s+"</li>");
		});	// .forEach
		
		out.println("</ol>");
		
		out.println("<a href='/CartDelete'>장바구니 비우기</a>");
		
		out.flush();
	} // service

} // end class
