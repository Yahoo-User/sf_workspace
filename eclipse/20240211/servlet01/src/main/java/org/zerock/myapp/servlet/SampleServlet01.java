package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@WebServlet(name = "Sample01", urlPatterns = { "/sample01" })
public class SampleServlet01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void service(
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		out.println("<h1>Sample01</h1>");
		
		out.flush();
		out.close();
	} // service

} // end class
