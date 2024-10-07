package org.zerock.myapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;

import lombok.extern.log4j.Log4j2;


@Log4j2
//@NoArgsConstructor

//@WebFilter("/*")
public class MyFilter
	extends HttpFilter
	implements Filter {
	private static final long serialVersionUID = 1L;

	
	public MyFilter() {
        super();
        
        log.trace("Default Constructor invoked.");
    } // Default Constructor    

	
    @Override
	public void destroy() {
    	log.trace("destroy() invoked.");
    	
	} // destroy

	
    @Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
		throws IOException, ServletException {
    	log.trace("doFilter(req, res, chain) invoked.");
    	
		// 1. place your Pre-processing code here
    	log.info("1. Pre-processing ....");
    	
    	// 더이상 필터 체인 뒤의 servlet 에서, 전송파라미터를 얻기 전에, utf8 인코딩
    	// 작업을 하지 않도록 해줌
    	req.setCharacterEncoding("utf8");

		// pass the request along the filter chain
		chain.doFilter(req, res);

		// 2. place your Post-processing code here
    	log.info("2. Post-processing ....");
	} // doFilter

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
    	log.trace("init(fConfig) invoked.");

	} // init

} // end class
