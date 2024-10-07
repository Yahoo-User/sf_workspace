package org.zerock.myapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebServlet("*.do")
//@WebServlet("A.do")	// XX: 정확한 이름과 확장자를 가지고 처리할려는 목적 실패
public class TestExtensionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void service(
			HttpServletRequest req, 
			HttpServletResponse res) 
				throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		
	} // service

} // end class
