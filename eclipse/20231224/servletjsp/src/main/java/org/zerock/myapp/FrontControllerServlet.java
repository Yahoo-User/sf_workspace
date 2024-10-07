package org.zerock.myapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.exception.BizProcessException;
import org.zerock.myapp.service.DeleteService;
import org.zerock.myapp.service.InsertService;
import org.zerock.myapp.service.SelectService;
import org.zerock.myapp.service.Service;
import org.zerock.myapp.service.UnknownService;
import org.zerock.myapp.service.UpdateService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("*.do")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
//		=================================================== //
//		Step 1. 전송 파라미터를 DTO 객체로 수집
//		=================================================== //
		
		// req.setCharacterEncoding("utf8");	// MyFilter에서 이미 수행
		
		String empno 	= req.getParameter("empno");
		String ename 	= req.getParameter("ename");
		String sal   	= req.getParameter("sal");
		String deptno 	= req.getParameter("deptno");
		
//		-----------------
		
		// 수집된 각 전송 파라미터를 DTO 객체에 저장
		// DTO 객체는 웹3계층(표현/비지니스/영속 계층)의 뒤로 전달 되면서 사용됨
		EmpDTO dto = new EmpDTO();
		
		if(empno != null && !"".equals(empno)) {
			dto.setEmpno(Integer.parseInt(empno));
		} // if
		
		dto.setEname(ename);
		
		if(sal != null && !"".equals(sal)) {
			dto.setSal(Double.parseDouble(sal));
		} // if
		
		if(deptno != null && !"".equals(deptno)) {
			dto.setDeptno(Integer.parseInt(deptno));
		} // if
		
//		-----------------
		
		// Request Scope 공유 데이터 영역에 DTO객체를 속성으로 바인딩
		// * 주의 * : 모든 Service 객체의 비지니스 로직 수행에 필요한 전송파라미터를
		// 전달해주는 DTO 객체를, 또 다른 공유데이터 영역인 "Request Scope"을 통해 전달
		// ( accessed by HttpServletRequest )
		req.setAttribute(Service.DTO, dto);
				
//		=================================================== //
//		Step 2. 요청 URI로, command(요청유형) 결정
//		=================================================== //
		
		String command = req.getRequestURI();
		
//		=================================================== //
//		Step 3. command 에 따라, 적합한 비지니스 서비스 객체
//		        선택 및 비지니스 로직 수행
//		=================================================== //
		
		try {
		
			// command(요청유형)에 따라, 각 요청을 처리하는 서비스 객체의 생성 및
			// 비지니스 로직 수행(execute 메소드)
			// * 주의 * : 비지니스 로직 수행 결과 데이터는, 공유데이터 영역인 
			//            "Application Scope"(accessed by ServletContext)에 바인딩
			switch(command) {
				case "/insert.do": 	new InsertService().execute(req, res); break;
				case "/update.do": 	new UpdateService().execute(req, res); break;
				case "/delete.do": 	new DeleteService().execute(req, res); break;
				case "/select.do": 	new SelectService().execute(req, res); 	break;
				default: 			new UnknownService().execute(req, res); break;	// 알 수 없는 요청인 경우에 해당
			} // switch
			
		} catch(BizProcessException e) {
			throw new ServletException(e);
		} // try-catch
		
//		=================================================== //
//		Step 4. command 별, 비지니스 로직 수행 결과 데이터를
//		        이용하여 응답문서 생성
//		=================================================== //
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/View");
		dispatcher.forward(req, res);
		
		log.info("Forwarded request into /View.");
	} // service

} // end class
