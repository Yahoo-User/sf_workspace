package org.zerock.myapp.config;

import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor


// (** 주의 **)
// AbstractSecurityWebApplicationInitializer 클래스를 상속한 자식클래스를 만들면,
// 자동으로 springSecurityFilterChain 빈이 등록됨
public class SecurityInitializer 
	extends AbstractSecurityWebApplicationInitializer {

	
	
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		log.debug("beforeSpringSecurityFilterChain(servletContext) invoked.");
		
		log.info("\t+ servletContext: " + servletContext);
		
		
	} // beforeSpringSecurityFilterChain
	

	@Override
	protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
		log.debug("afterSpringSecurityFilterChain(servletContext) invoked.");
		
		log.info("\t+ servletContext: " + servletContext);

		
	} // afterSpringSecurityFilterChain

} // end class
