package org.zerock.myapp.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor


@EnableWebMvc
@ComponentScan(basePackages= { "org.zerock.myapp.controller" })

@Configuration("servlet-context.xml")
public class ServletContextXml
	implements WebMvcConfigurer, InitializingBean, DisposableBean {
	
	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.debug("addResourceHandlers(registry) invoked.");
		
		registry.
			addResourceHandler("/resources/**").
			addResourceLocations("/resources/");
	} // addResourceHandlers
	

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		log.debug("configureViewResolvers(registry) invoked.");
		
		//-- 1st. method
		registry.jsp("/WEB-INF/views/", ".jsp");
		
		//-------------------------------------//
		
		//-- 2nd. method
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/WEB-INF/views/");
//		resolver.setSuffix(".jsp");
//		resolver.setViewClass(JstlView.class);
//		
//		registry.viewResolver(resolver);
	} // configureViewResolvers
	
	
	//===============================================================//

	@Override
	public void destroy() throws Exception {
		log.debug("destroy() invoked.");
		
	} // destroy

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("afterPropertiesSet() invoked.");
		
	} // getServletMappings
	
} // end class
