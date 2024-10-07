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
@WebServlet("/DoGet")
public class DoGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response) 
				throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		//---------- 응답을 주는 부분 ---------//
		out.println("<h1>DoGet invoked.</h1>");
		
		out.flush();
		out.close();
	} // doGet

} // end class
