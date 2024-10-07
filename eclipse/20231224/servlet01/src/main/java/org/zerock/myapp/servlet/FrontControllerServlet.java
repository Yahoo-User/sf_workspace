package org.zerock.myapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.Command;
import org.zerock.myapp.service.SelectService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("*.do")	// 확장자 패턴으로, 끝이 .do로 끝나는 모든 요청을 받음
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    

	@Override
	protected void service(
		HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		if(req.getParameter("empno") == null) {
			throw new NullPointerException("TEST 용도로 던집니다.");
		} // if
		
		//-Step1. 전송 파라미터(무조건 문자열 타입)의 획득 ----//
		String empno = req.getParameter("empno");
		
		
		//-Step2. DTO 객체 생성 ----//
		EmpDTO empDTO = new EmpDTO();
		
		if(empno != null) {
			empDTO.setEmpno( Integer.parseInt(empno) );
		} // if
		
		
		//-Step3. 요청의 식별 --//
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();	// ex00
		
		String command = requestURI.substring(contextPath.length()+1);
		
		Command service = null;
		String view = null;
		
		switch(command) {
			case "select.do":
				
				// 이 요청을 처리할 비지니스 객체(서비스객체) 생성
				service = new SelectService();
				
				// 이 서비스 객체의 수행결과 발생할 데이터(Model)를 가장 잘 보여줄
				// View 를 선택
				view = "/WEB-INF/views/select.jsp";
				
				break;
		} // switch
		
		log.info("\t+ service: " + service);
		log.info("\t+ view: " + view);
		

		//-Step4. 식별된 요청에 따라, 비지니스 로직을 수행하고 그 결과(model)를 저장 --/
		Object model = null;
		
		try {
			model = service.execute(empDTO);
			
		} catch(ServiceException e) {
			throw new ServletException(e);
		} // try-catch
		
		
		//-Step5. model을 View에 전달하고 View 호출하여 응답생성 및 전송
		
		// Request Scope에, Model 데이터를 속성으로 바인딩 시킴(공유시킴)
		req.setAttribute("MODEL", model);	
		
		// MVC 패턴의 마지막인 View(응답화면을 모델데이터를 이용해서 생성 및 전송)를 호출
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher(view);
		
		dispatcher.forward(req, res);
		
	} // service

} // end class
