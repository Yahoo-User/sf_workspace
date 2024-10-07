package org.zerock.myapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebFilter("/*")
public class MyFilterImpl implements Filter {

	
	public void init(FilterConfig fConfig) 
			throws ServletException {
		log.debug("init(fConfig) invoked.");
		
	} // init

	
	public void doFilter(
		ServletRequest req, 
		ServletResponse res, 
		FilterChain chain) 
				throws IOException, ServletException {
		log.debug("doFilter(req, res, chain) invoked.");
		
		// place your code here

		// pass the request along the filter chain
		
		//==============================================//
		// 요청필터작업 영역(=선처리 작업영역)
		//==============================================//
		log.info(">>>>> 1. MyFilter 요청필터 코드작업");	// 선처리작업 
		req.setCharacterEncoding("UTF-8");	// 선처리작업
		//==============================================//
		
		
		chain.doFilter(req, res);	/* 선처리와 후처리의 구분자 역할 */
		
		
		//==============================================//
		// 응답필터작업 영역(=후처리 작업영역)
		//==============================================//
		log.info(">>>>> 2. MyFilter 응답필터 코드작업");	// 후처리작업
		//==============================================//
		
	} // doFilter
	
	public void destroy() {
		log.debug("destory() invoked.");
		
	} // destroy

} // end class
