package org.zerock.myapp.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor

@Configuration("web.xml")
public class WebXml
	extends AbstractAnnotationConfigDispatcherServletInitializer
	implements InitializingBean, DisposableBean {
	


	@Override
	protected Class<?>[] getRootConfigClasses() {
		log.debug("getRootConfigClasses() invoked.");

		return new Class<?>[] { RootContextXml.class };
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
