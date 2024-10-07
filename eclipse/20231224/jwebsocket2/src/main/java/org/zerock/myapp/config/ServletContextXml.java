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
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@EnableWebMvc

@ComponentScan(
	basePackages= { 
		"org.zerock.myapp.controller",
		"org.zerock.myapp.handler"
	}
)

@Configuration("servlet-context.xml")
public class ServletContextXml
	implements WebMvcConfigurer, InitializingBean, DisposableBean {
	
	
	
	//===================================================//
	
	// ** 주의: multipartResolver 빈 설정시, 아래의 메소드를 구현해서는 안됨 -> 생략함
//	@Override
//	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//		log.debug("configureDefaultServletHandling(configurer) invoked.");
//
//		configurer.enable();
//	} // configureDefaultServletHandling


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
		
		//-----------------------------------------------------//
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		
		registry.viewResolver(viewResolver);
	} // configureViewResolvers
	

	//===================================================//
	// Setting View Controller without Controller & methods
	//===================================================//
	
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		log.debug("addViewControllers(registry) invoked.");
//		
//		registry.
//			addViewController("/user/login").
//			setStatusCode(HttpStatus.OK).
//			setViewName("user/login");		
//	} // addViewControllers
	
	
	
	
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
