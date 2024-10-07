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

@WebServlet("/Sport")
public class SportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(
			HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		doPost(req, res);
	} // doGet
       
    
	@Override
	protected void doPost(
			HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.info("doPost(req, res) invoked.");
		
		// post 방식의 한글처리
		req.setCharacterEncoding("UTF-8");
		
		String [] sports = req.getParameterValues("sports");
		String sex = req.getParameter("sex");
		
		//----------------------------//
		
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		out.print("<html><body>");
		
		//--1st. method
		if(sports != null) {
//		Objects.requireNonNull(sports);	// 2nd. method
		
			for (String sport : sports) {
				out.print("1. 좋아하는 운동: " + sport +"<br>");
			} // enhanced for
			
		} // if
		
		out.print("2. 성별: " + sex +"<br>");
		out.print("</body></html>");
		
		out.flush();
		out.close();
	} // doPost

} // end class
