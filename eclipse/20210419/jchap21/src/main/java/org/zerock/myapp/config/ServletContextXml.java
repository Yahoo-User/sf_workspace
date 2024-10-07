package org.zerock.myapp.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor


@EnableWebMvc

@ComponentScan(basePackages= {
	"org.zerock.myapp.controller",
	"org.zerock.myapp.exception"
})

@Configuration("servlet-context.xml")
public class ServletContextXml
	implements WebMvcConfigurer, InitializingBean, DisposableBean {
	

	
	// ============================================================ //
	// *** NOT any more required for spring ***
	// ============================================================ //
	// Spring's multipart resolver
	
//	@Bean(name="multipartResolver")
//	public CommonsMultipartResolver multipartResolver() throws IOException {
//		
//		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//		
//		resolver.setDefaultEncoding("utf8");
//		resolver.setMaxInMemorySize(1048576);
//		resolver.setMaxUploadSize(10485760);
//		resolver.setMaxUploadSizePerFile(2097152);
//		resolver.setPreserveFilename(true);
//		
//		FileSystemResource uploadTempDir = new FileSystemResource("C:/temp/file_upload");
//		
//		resolver.setUploadTempDir(uploadTempDir);
//		
//		return resolver;
//	} // multipartResolver
	

	// ============================================================ //
	// Servlet 3.0 and above standard file upload
	// ============================================================ //

	// Spring's multipart resolver using Standard servlet since servlet 3.0
	
	@Primary
	@Bean(name="multipartResolver2")
	public MultipartResolver multipartResolver2() {
		log.debug("multipartResolver2() invoked.");
		
		StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
		log.info("\t+ multipartResolver: " + multipartResolver);
		
		return multipartResolver;
	} // multipartResolver2
	
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		log.debug("addViewControllers(registry) invoked.");
		
		registry.
			addViewController("/uploadForm").
			setStatusCode(HttpStatus.OK).
			setViewName("uploadForm");
		
		registry.
			addViewController("/uploadAjax").
			setStatusCode(HttpStatus.OK).
			setViewName("uploadAjax");
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

//		registry.jsp("/WEB-INF/views/", ".jsp");
		
		//-----------------------------------------------------//
		
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
