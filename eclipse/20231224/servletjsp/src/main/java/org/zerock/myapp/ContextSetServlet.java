package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/SetApplicationScope")
public class ContextSetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		ServletContext sc = this.getServletContext();
		sc.setAttribute("name", "Yoseph");
		sc.setAttribute("age", 23);			// Auto-Boxing : int -> Integer
		
//		===============================
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		out.println("<h1>Application Scope에 새로운 속성 추가 성공</h1>");		
		out.flush();
	} // service

} // end class
