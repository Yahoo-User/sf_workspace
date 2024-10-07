package org.zerock.myapp.config;

import java.io.IOException;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
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

@ComponentScan(basePackages= {
	"org.zerock.myapp.controller",
	"org.zerock.myapp.exception"
})

@Configuration("servlet-context.xml")
public class ServletContextXml
	implements WebMvcConfigurer, InitializingBean, DisposableBean {
	
	

	@Bean(name="multipartResolver")
	public CommonsMultipartResolver multipartResolver() throws IOException {
		
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf8");
		resolver.setMaxInMemorySize(1048576);
		resolver.setMaxUploadSize(10485760);
		resolver.setMaxUploadSizePerFile(2097152);
		resolver.setPreserveFilename(true);
		
		FileSystemResource uploadTempDir = new FileSystemResource("C:/temp/");
		
		resolver.setUploadTempDir(uploadTempDir);
		
		return resolver;
	} // multipartResolver
	
	
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
