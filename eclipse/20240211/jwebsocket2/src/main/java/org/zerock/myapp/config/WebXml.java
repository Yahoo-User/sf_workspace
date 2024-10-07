package org.zerock.myapp.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Configuration("web.xml")
public class WebXml
	extends AbstractAnnotationConfigDispatcherServletInitializer
	implements InitializingBean, DisposableBean {
	

	@Override
	protected Class<?>[] getRootConfigClasses() {
		log.debug("getRootConfigClasses() invoked.");
		
		return new Class<?>[] { RootContextXml.class, WebSocketConfig.class };
	} // getRootConfigClasses

	@Override
	protected Class<?>[] getServletConfigClasses() {
		log.debug("getServletConfigClasses() invoked.");

		return new Class<?>[] { ServletContextXml.class };
	} // getServletConfigClasses
	

	@Override
	protected String[] getServletMappings() {
		log.debug("getServletMappings() invoked.");
		
		return new String[] { "/" };
	} // getServletMappings
	
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		log.debug("customizeRegistration(registration) invoked.");
		
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	} // customizeRegistration
	

	@Override
	protected Filter[] getServletFilters() {
		log.debug("getServletFilters() invoked.");
		
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("utf8");
		encodingFilter.setForceEncoding(true);
		
		return new Filter[] { encodingFilter };
	} // afterPropertiesSet
	
	
	//===================================================//

	@Override
	public void destroy() throws Exception {
		log.debug("destroy() invoked.");
		
	} // destroy

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("afterPropertiesSet() invoked.");
		
	} // afterPropertiesSet

} // end class
