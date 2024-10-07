package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	@Override
	protected void doGet(
		HttpServletRequest req, 
		HttpServletResponse res) 
			throws ServletException, IOException {
		log.info("doGet(req, res) invoked.");
		
		//------------------------------//
		// 요청처리
		//------------------------------//
		
//		String userid = req.getParameter("userid");
		String[] userid = req.getParameterValues("userid");
		
		String passwd = req.getParameter("passwd");

		
		//------------------------------//
		// 응답처리
		//------------------------------//
		res.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = res.getWriter();
		
		out.print("<html><body>");
		out.print("아이디값: " + Arrays.toString(userid) +"<br>");
		out.print("비밀번호값: " + passwd +"<br>");
		out.print("</body></html>");
		
		out.flush();
		out.close();
	} // doGet

} // end class
