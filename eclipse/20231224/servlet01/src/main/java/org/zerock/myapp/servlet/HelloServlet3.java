package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;


//@Log4j2
@NoArgsConstructor

@WebServlet(name = "Hello3", urlPatterns = { "/HelloServlet3" })
public class HelloServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
				
		out.println("응답으로 드립니다.");
		
		out.flush();
		out.close();
	} // doGet

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		doGet(request, response);
	} // doPost

} // end class
