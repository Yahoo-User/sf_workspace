package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet("/CartSave")
public final class CartSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	@Override
	protected void service(
			HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		String product = req.getParameter("product");
		log.info("\t+ product: " + product);
		
		
		// HttpSession 객체를 얻기
		// Request.getSession() 메소드는, 기존에 세션이 없으면 새로이 생성하고
		// 기존 세션이 있으면 있는 것을 돌려준다!!!
		// === Request.getSession(true);
		// 기존 셋션이 없으면, 이때야 말로, 브라우저에 할당할 이름(=세션ID)을 생성해서
		// 응답문서에 담아서 보냅니다 --> 응답을 받은 브라우저는 할당받은 이름(=세션ID)를
		// 브라우저 메모리에 저장해서, 두번째 요청부터 그 이후로, 요청을 보낼 때마다,
		// 요청문서에 세션ID(자기의 이름)을 담아서 서버에 전송
		HttpSession sess = req.getSession();
		log.info("\t+ sess: " + sess);
		log.info("\t+ >>>>>> session ID: " + sess.getId());
		
		// Session Scope 공유영역에 저장된 장바구니(List<String>)를 획득
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) sess.getAttribute("product");
		
		if(list == null) {  // 장바구니가 없으면...새로운 장바구니를 생성해서,
							// Session Scope에 저장
			list = new ArrayList<>();
			
			sess.setAttribute("product", list);
		} // if
		
		list.add(product);	// 장바구니에 제품담기
		
		//---------------------------------------------//
		
		res.setContentType("text/html; charset=utf8");
		
		PrintWriter out = res.getWriter();
		
		out.print("<html><body>");
		out.print("Product 추가<br>");
		out.print("<a href='CartBasket'>장바구니보기</a>");
		out.print("</body></html>");
		
		out.flush();
		out.close();
	} // service

} // end class
