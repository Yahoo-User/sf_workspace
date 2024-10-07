package org.zerock.myapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.Person;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/C")
public class CServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		log.info("C 서블릿 - 저는 여기까지만 처리합니다.");
		
		// 비지니스 로직 수행결과 데이터인 Person 객체 생성해서
		// Request Scope 공유데이터 영역에 올려 놓음 (MODEL)
		Person person = new Person();
		person.setName("Yoseph");
		person.setAge(23);
		
		req.setAttribute("__MODEL__", person);
		
		req.getRequestDispatcher("/WEB-INF/views/response.jsp").forward(req, res);	// VIEW		
	} // service

} // end class
