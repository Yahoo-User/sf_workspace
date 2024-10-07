package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/ResponseRedirect")
public class ResponseRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(
			HttpServletRequest req, HttpServletResponse res) 
				throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		
		// 첫번째 서블릿이 공유한 속성을 얻어보자!!!
		Object name1 = req.getAttribute("NAME1");
		Object name2 = req.getAttribute("NAME2");
		
		log.info("\t+ name1: " + name1);
		log.info("\t+ name2: " + name2);
		
		//----------------------------------//
		res.setContentType("text/html; charset=utf8");
		
		PrintWriter out = res.getWriter();
		
		out.println("<h1>1. name1: " + name1 + "</h1>");
		out.println("<h1>2. name2: " + name2 + "</h1>");
		
		out.flush();
		out.close();
	} // service

} // end class
