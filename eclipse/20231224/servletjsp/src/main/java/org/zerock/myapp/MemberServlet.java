package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

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

@WebServlet("/Member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		req.setCharacterEncoding("utf8");	// UTF-8, UTF8, utf-8, utf8

		Enumeration<String> paramNames = req.getParameterNames();
		
//		----
		
		res.setContentType("text/html; charset=utf8");
				
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		
		out.println("<head>");
		out.println("	<title>Response</title>");
		out.println("</head>");
		
		out.println("<body>");
		
		while(paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			String value = req.getParameter(name);
			
//			out.println("<h3>name: "+name+", value: "+value+"</h3>");
			out.println( String.format("<h3>name: %s, value: %s</h3>", name, value) );
		} // while
		
		out.println("</body>");
		
		out.println("</html>");
		out.flush();
	} // service

} // end class
