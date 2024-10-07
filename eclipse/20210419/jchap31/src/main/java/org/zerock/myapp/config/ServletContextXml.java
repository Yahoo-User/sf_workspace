package org.zerock.myapp.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor


@EnableWebMvc

@ComponentScan(basePackages= {
	"org.zerock.myapp.controller"
})

@Configuration("servlet-context.xml")
public class ServletContextXml
	implements WebMvcConfigurer, InitializingBean, DisposableBean {
	
	
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		log.debug("addViewControllers(registry) invoked.");
		
		registry.
			addViewController("/sample/all").
			setStatusCode(HttpStatus.OK).
			setViewName("sample/all");
		
		registry.
			addViewController("/sample/member").
			setStatusCode(HttpStatus.OK).
			setViewName("sample/member");
		
		registry.
			addViewController("/sample/admin").
			setStatusCode(HttpStatus.OK).
			setViewName("sample/admin");
		
	} // addViewControllers
	
	
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

		registry.jsp("/WEB-INF/views/", ".jsp");
		
		//-----------------------------------------------------//
		
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setPrefix("/WEB-INF/views/");
//		viewResolver.setSuffix(".jsp");
//		viewResolver.setViewClass(JstlView.class);
//		
//		registry.viewResolver(viewResolver);
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
