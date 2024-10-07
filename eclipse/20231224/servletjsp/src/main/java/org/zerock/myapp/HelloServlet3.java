package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

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
	name = "Hello3Servlet", 
	urlPatterns = { "/xxx", "/yyy" }		// String[] urlPatterns;
)
public class HelloServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		// HTTP request 메시지로부터, 전송파라미터들을 얻어내기 위해서는 아래와 같이 함
//		String name = req.getParameter("name");	// name 이란 이름의 전송파라미터의 값을 추출
//		String age  = req.getParameter("age");	// age 란 이름의 전송파라미터의 값을 추출
//		String[] hobby = req.getParameterValues("hobby");
//		
//		log.info("\t+ name: {}, age: {}", name, age);
//		log.info("\t+ hobby: {}", Arrays.toString(hobby));
		
//		============================
		
//		Enumeration<String> paramNames = req.getParameterNames();
//		
//		while(paramNames.hasMoreElements()) {
//			String name = paramNames.nextElement();
//			log.info("\t+ name: {}", name);
//		} // while
		
//		============================
		
//		@Cleanup("clear") : 이 Map 객체는 읽기전용(Read-Only)이라, clear 시키면 안됨!!!!
		Map<String, String[]> map = req.getParameterMap();
		
		log.info( "\t+ map: {}, hobby: {}", map, map.get("hobby") );
		
//		============================
		
		PrintWriter out = res.getWriter();
		out.println("<h1>World!!!</h1>");
		
		out.flush();
		out.close();
	} // service

} // end class
