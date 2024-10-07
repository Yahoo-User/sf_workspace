package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/ContextParamSet")
public class ContextParamSetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String jdbcDriver;
	private String savePath;
	
	
	
	@Override
	public void init(ServletConfig config)
		throws ServletException {
		log.trace("init(config) invoked.");
		
		super.init(config); // ***

		ServletContext sc = config.getServletContext();
		
		// web.xml 파일에서, Context Parameter 2개 얻기
		this.jdbcDriver = sc.getInitParameter("jdbcDriver");
		this.savePath = sc.getInitParameter("savePath");
		
		log.info("\t+ jdbcDriver: {}", jdbcDriver);
		log.info("\t+ savePath: {}", savePath);
		
		
		// Application Scope 이란 공유데이터 영역에 2개의 문자열을 올려놓음
		sc.setAttribute("jdbcDriver", jdbcDriver);
		sc.setAttribute("savePath", savePath);
		
		log.info("Upload done.");		
	} // init

	
	@Override
	public void destroy() {
		log.trace("destroy() invoked.");

	} // destroy


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		log.info(this.getServletContext());
		log.info(this.getServletContext().getInitParameter("jdbcDriver"));
		log.info(this.getServletContext().getInitParameter("savePath"));

//		== 응답문서 준비 
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("1. jdbcDriver: " + jdbcDriver + "<br>");
		out.println(String.format("2. savePath: %s", savePath));
		
		out.flush();
		
	} // service

} // end class
