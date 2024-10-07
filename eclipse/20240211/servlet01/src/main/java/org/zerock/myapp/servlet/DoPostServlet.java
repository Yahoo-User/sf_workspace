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
@WebServlet(name = "DoPost", urlPatterns = { "/DoPost" })
public class DoPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
           

	protected void doPost(
			HttpServletRequest request, 
			HttpServletResponse response) 
				throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		

		PrintWriter out = response.getWriter();
		
		//------------------- 응답 --------------//
		out.println("<h1>name: " + name + "</h1>");
		out.println("<h1>age: " + age + "</h1>");
				
		out.flush();
		out.close();
	} // doPost

} // end class
