package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/ContextGet")
public class ContextGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    
	protected void service(
			HttpServletRequest req, HttpServletResponse res) 
				throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		//=====================================//
		// 첫번째 서블릿이 바인딩 한, Application Scope의
		// 속성(Attributes: 공유데이터)을 사용하자!!
		//=====================================//
		
		// Application Scope 을 사용하려면, ServletContext 객체가 필요
		ServletContext sc = this.getServletContext();
		log.info("\t+ sc: " + sc);
		
		Objects.requireNonNull(sc);
		
		String name = (String) sc.getAttribute("name");
		int age 	= (Integer) sc.getAttribute("age");
		
		int shared	= (Integer) sc.getAttribute("SHARED");
		
		log.info("\t+ name: " + name);
		log.info("\t+ age: " + age);
		
		log.info("\t+ shared: " + shared);
		
		//----------------------------------//
		res.setContentType("text/html; charset=utf8");
		PrintWriter out = res.getWriter();
		
		out.println("<html>");
		out.printf("<h1>공유 이름: %s</h1>", name);
		out.printf("<h1>공유 나이: %s</h1>", age);
		out.println("</html>");
		
		out.flush();
		out.close();
	} // service



	@Override
	public void init(ServletConfig config) throws ServletException {
		log.debug("init(config) invoked.");
		log.info("\t+ config.ServletContext: " + config.getServletContext());
		
		log.info("\t+ 1. this.ServletContext: " + this.getServletContext());			// XX
		
		super.init(config);		// init() invocation.
		
		log.info("\t+ 2. this.ServletContext: " + this.getServletContext());			// OK
	} // init



	@Override
	public void init() throws ServletException {
		log.debug("init() invoked.");
		
		log.info("\t+ this.ServletContext: " + this.getServletContext());			// OK
		
	} // init

} // end class
