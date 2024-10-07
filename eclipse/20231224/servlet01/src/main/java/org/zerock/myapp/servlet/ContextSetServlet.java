package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/ContextSet")
public class ContextSetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	protected void doGet(
			HttpServletRequest req, HttpServletResponse res)
					throws ServletException, IOException {
		log.debug("doGet(req, res) invoked.");
		
		// HTTP session 을 생성하라!!!
		HttpSession session = req.getSession(true);
		log.info("\t+ session id: " + session.getId());
		
		
		ServletContext sc = this.getServletContext();
		log.info("\t+ sc: " + sc);
		
		
		//========================================//
		// Application Scope 에 Attribute Biding 수행
		//========================================//
		
		String name = "Yoseph";
		int age = 23;
		
		sc.setAttribute("name", name);
		sc.setAttribute("age", age);
		
		//-----------------------------------------------//
		res.setContentType("text/html; charset=utf8");
		PrintWriter out = res.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		
		String result = "Application Scope에 속성바인딩 성공";
		out.printf("<h1>%s</h1>", result);
		
		out.println("</body>");
		out.println("</html>");
		
		out.flush();
		out.close();
	} // doGet

} // end class
