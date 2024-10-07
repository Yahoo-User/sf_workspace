package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
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

@WebServlet("/Lifecycle")
public class LifecycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.trace("init({}) invoked.", config);
		
	} // init


	@Override
	public void destroy() {
		log.trace("destroy() invoked.");
		
	} // destroy


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<h1>LifecycleServlet</h1>");
		out.flush();
	} // service

} // end class
