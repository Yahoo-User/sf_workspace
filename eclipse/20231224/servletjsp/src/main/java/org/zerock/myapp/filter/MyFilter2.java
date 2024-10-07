package org.zerock.myapp.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }, servletNames = { "HelloServlet" })
public class MyFilter2
	extends HttpFilter
	implements Filter {
	private static final long serialVersionUID = 1L;
	

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
		throws IOException, ServletException {
    	log.trace("doFilter(req, res, chain) invoked.");
    	
		// 1. place your Pre-processing code here
    	log.info("1. Pre-processing ....");

		// pass the request along the filter chain
		chain.doFilter(req, res);

		// 2. place your Post-processing code here
    	log.info("2. Post-processing ....");
	} // doFilter

	
} // end class
