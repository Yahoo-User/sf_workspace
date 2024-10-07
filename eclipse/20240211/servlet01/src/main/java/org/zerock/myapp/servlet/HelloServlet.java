package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet(
	description = "For the first time Servlet",
	name = "HelloServlet",
	urlPatterns = { "/Hello1", "/Hello2", "/Hello3" }
)
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    	    
	
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 우리가 비지니스 로직을 넣을 코드부분....
    	log.info("HelloServlet 요청");
    	
    	PrintWriter out = response.getWriter();
    	out.println("<html>");
    	out.println("<head>");
    	out.println("</head>");
    	out.println("<body>");
    	out.println("<h1>"+ new Date() + "</h1>");
    	out.println("</body>");
    	out.println("</html>");
    	
    	out.flush();
    	out.close();
	
	} // service

} // end class
