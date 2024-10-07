package org.zerock.myapp.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.zerock.myapp.interceptor.AuthInterceptor;
import org.zerock.myapp.interceptor.LoginInterceptor;
import org.zerock.myapp.interceptor.SampleInterceptor;
import org.zerock.myapp.interceptor.SampleInterceptor2;
import org.zerock.myapp.interceptor.SampleInterceptor3;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor


@EnableWebMvc

@ComponentScan(
	basePackages= { 
		"org.zerock.myapp.controller",
		"org.zerock.myapp.service",
		"org.zerock.myapp.persistence",
		"org.zerock.myapp.exception",
		"org.zerock.myapp.interceptor"
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
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		log.debug("addViewControllers(registry) invoked.");
		
		registry.addViewController("/user/login").setStatusCode(HttpStatus.OK).setViewName("user/login");
		
		registry.addViewController("/sboard/list").setStatusCode(HttpStatus.OK).setViewName("sboard/list");
		registry.addViewController("/sboard/register").setStatusCode(HttpStatus.OK).setViewName("sboard/register");
		registry.addViewController("/sboard/get").setStatusCode(HttpStatus.OK).setViewName("sboard/get");
		registry.addViewController("/sboard/modify").setStatusCode(HttpStatus.OK).setViewName("sboard/modify");
		registry.addViewController("/sboard/remove").setStatusCode(HttpStatus.OK).setViewName("sboard/remove");
	} // addViewControllers
	

	//===================================================//
	// Setting Interceptors
	//===================================================//
	
	@Autowired
	private SampleInterceptor2 sampleInterceptor2;
	
	@Autowired
	private SampleInterceptor3 sampleInterceptor3;
	
	@Autowired
	private AuthInterceptor authInterceptor;
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.debug("addInterceptors(registry) invoked.");
		
		SampleInterceptor sampleInterceptor = new SampleInterceptor();
		
		
		//------------------------------------------------------------//
		// To set patterns using wildcard : *, **, ?
		//------------------------------------------------------------//
		
//		registry.addInterceptor(sampleInterceptor).addPathPatterns("/sample/*");		// OK
		
		
//		org.springframework.web.util.pattern.PatternParseException: 
//		No more pattern data allowed after {*...} or ** pattern element
		
//		registry.addInterceptor(sampleInterceptor).addPathPatterns("/**/do*");		// XX
//		registry.addInterceptor(sampleInterceptor).addPathPatterns("/**/doA");		// XX
		

		//------------------------------------------------------------//
		// To set patterns NOT using wildcard : *, **, ?
		//------------------------------------------------------------//
		registry.addInterceptor(sampleInterceptor).addPathPatterns("/sample/doA", "/sample/doB", "/sample/doC");
		registry.addInterceptor(sampleInterceptor2).addPathPatterns("/sample/doB", "/sample/doC");
		registry.addInterceptor(sampleInterceptor3).addPathPatterns("/sample/doD");
		
		registry.addInterceptor(loginInterceptor).addPathPatterns("/user/loginPost");
		registry.addInterceptor(authInterceptor).addPathPatterns("/sboard/*");
		
	} // addInterceptors
	
	
	
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
