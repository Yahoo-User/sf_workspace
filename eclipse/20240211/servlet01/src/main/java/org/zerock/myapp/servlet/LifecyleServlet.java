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
@NoArgsConstructor(access=lombok.AccessLevel.PUBLIC)

@WebServlet("/Lifecyle")
public class LifecyleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	@Override
	public void init(ServletConfig config) throws ServletException {
		log.info("init(config) invoked.");
		
	} // init

	@Override
	public void destroy() {
		log.info("destroy() invoked.");
		
	} // destroy
	
	@Override
	protected void service(
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		log.info("service(request, response) invoked.");
		
		
		PrintWriter out = response.getWriter();
		
		out.println("<h1>OK!!!!</h1>");
		
		out.flush();
		out.close();
	} // service

} // end class
