package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet(name = "ResponseSample", urlPatterns = { "/AAA" })
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    
	public void init(ServletConfig config) 
			throws ServletException {
		log.info("init(config) invoked.");
		
	} // init

	public void destroy() {
		log.info("destroy() invoked.");
		
	} // destroy

	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response) 
				throws ServletException, IOException {
		log.info("doGet(request, response) invoked.");
		
		// MIME 타입 설정
		response.setContentType("text/html");
		
		// 자바I/O
		PrintWriter out = response.getWriter();
		
		// html작성 및 출력
		out.print("<html><body>");
		out.print("ResponseServlet 요청성공");
		out.print("</body></html>");
		
		// 자원반납
		out.flush();
		out.close();
	} // doGet

} // end class
