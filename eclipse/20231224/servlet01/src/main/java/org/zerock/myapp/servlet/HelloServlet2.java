package org.zerock.myapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet({ "/xxx", "/yyy" })
public class HelloServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;	

	


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		log.info("HelloServlet2::doPost invoked.");
		
		this.doGet(request, response);
	} // doPost
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		log.info("HelloServlet2::doGet invoked.");
		
		//---------------------------------------------//
		// HTTP request 문서에서, 브라우저가 보낸 데이터 추출하기
		//---------------------------------------------//
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		log.info("\t+ name: " + name);
		log.info("\t+ age: " + age);
		
		
	} // doPost

} // end class






