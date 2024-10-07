package org.zerock.myapp.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor(access=AccessLevel.PUBLIC)


// (**주의**) 이 어노테이션을 통해, Spring MVC와 Spring security를 연결/통합 시킴
@EnableWebSecurity(debug=true)

@Configuration("security-context.xml")
public class SecurityContextXml

	//(**주의**) Spring Security Configuration Java Class는,
	//반드시 WebSecurityConfigurerAdapter 클래스를 상속받아, 필요한 메소드를 오버라이딩 하는 형식으로 구현됨.
	extends WebSecurityConfigurerAdapter
	implements InitializingBean, DisposableBean {
	
	
	
	
	
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
