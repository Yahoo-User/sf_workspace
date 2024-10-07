package org.zerock.myapp.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
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
		
//		registry.jsp("/WEB-INF/views/", ".jsp");
		
		//---------------------------------------------//
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		
		registry.viewResolver(viewResolver);
	} // configureViewResolvers
	
	
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
