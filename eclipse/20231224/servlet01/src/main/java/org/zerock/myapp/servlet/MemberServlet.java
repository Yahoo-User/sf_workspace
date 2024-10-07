package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/Member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.info("doGet(req, res) invoked.");
		
		doPost( req, res );
	} // doGet

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.info("doPost(req, res) invoked.");
		
		// post 방식의 한글처리
		req.setCharacterEncoding("UTF-8");
		
		Enumeration<String> enu = req.getParameterNames();
		
		//--------------------------------------------//
		
		res.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = res.getWriter();
		
		out.print("<html><body>");
		
		while ( enu.hasMoreElements() ){	// 각 전송파라미터마다...
			
			// 이 서블릿이 수신한 모든 전송 파라미터의 이름과 값을 추출
			String name = enu.nextElement();
			String value = req.getParameter(name);
			
			out.print( name + " : " + value +"<br>");
			
			// 확인할 전송파라미터: userid, passwd 확인
			if("userid".equals(name) || "passwd".equals(name)) {
				out.println("- Found name: " + name);
			} // if
			
			//----------------------//
			
			switch(name) {
				case "userid":
				case "passwd":
					out.println("- Found name: " + name);
			} // switch
			
			
		} // while
		
		out.print("</body></html>");
		
		out.flush();
		out.close();
	} // doPost

} // end class
