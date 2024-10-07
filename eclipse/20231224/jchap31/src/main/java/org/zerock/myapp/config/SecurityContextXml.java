package org.zerock.myapp.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.zerock.myapp.security.CustomAccessDeniedHandler;
import org.zerock.myapp.security.CustomLoginSuccessHandler;
import org.zerock.myapp.security.CustomLogoutSuccessHandler;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor(access=AccessLevel.PUBLIC)


// (**주의**) 이 어노테이션을 통해, Spring MVC와 Spring security를 연결/통합 시킴
@EnableWebSecurity(debug=true)

@Configuration("security-context.xml")

@SuppressWarnings("deprecation")
public class SecurityContextXml

	//(**주의**) Spring Security Configuration Java Class는,
	//반드시 WebSecurityConfigurerAdapter 클래스를 상속받아, 필요한 메소드를 오버라이딩 하는 형식으로 구현됨.
	extends WebSecurityConfigurerAdapter
	implements InitializingBean, DisposableBean {
	
	
	
	//===================================================//

	@Bean("customAccessDeniedHandler")
	public AccessDeniedHandler customAccessDeniedHandler() {
		log.debug("customAccessDeniedHandler() invoked.");
	
		return new CustomAccessDeniedHandler();
	} // customAccessDeniedHandler
	
	
	@Bean("customLoginSuccessHandler")
	public AuthenticationSuccessHandler customLoginSuccessHandler() {
		log.debug("customLoginSuccessHandler() invoked.");
	
		return new CustomLoginSuccessHandler();
	} // customLoginSuccessHandler
	
	
	@Bean("customLogoutSuccessHnalder")
	public LogoutSuccessHandler customLogoutSuccessHandler() {
		log.debug("customLogoutSuccessHandler() invoked.");
	
		return new CustomLogoutSuccessHandler();
	} // customLogoutSuccessHandler
	
	//===================================================//
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.debug("configure(http) invoked.");
		
		//------------------------------------------------------//
		
		
		//------------------------------------------------------//
		
//		http.authorizeRequests().antMatchers("/sample/all").permitAll();
//		http.authorizeRequests().antMatchers("/sample/member").access("hasRole('ROLE_MEMBER')");
//		http.authorizeRequests().antMatchers("/sample/admin").access("hasRole('ROLE_ADMIN')");
		
		//------------------------------------------------------//
		
		http.
			authorizeRequests().
			antMatchers("/sample/all").
			permitAll().
			
			antMatchers("/sample/member").
			access("hasRole('ROLE_MEMBER')").
			
			antMatchers("/sample/admin").
			access("hasRole('ROLE_ADMIN')");
		
		//------------------------------------------------------//
		
//		http.formLogin();
		
		
		http.
			formLogin().
			loginPage("/customLogin").
			loginProcessingUrl("/login").
			successHandler(customLoginSuccessHandler());
		
		//------------------------------------------------------//
		
//		http.csrf().disable();
		
		//------------------------------------------------------//
		
		http.
			logout().
			logoutUrl("/customLogout").
			invalidateHttpSession(true).
			logoutSuccessHandler(customLogoutSuccessHandler());
		
	} // configure

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.debug("configure(auth) invoked.");
		
		//------------------------------------------------------//
		
		// (**주의사항**) 역할의 이름 지정시, 'ROLE_' 는 자동으로 추가됨.
		// 따라서, 역할 지정시, 역할의 이름만 지정하고 'ROLE_'는 지정하지 말아야 함 (그렇지 않으면, 오류발생)
		
//		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN", "MEMBER");
//		auth.inMemoryAuthentication().withUser("member").password("{noop}member").roles("MEMBER");
		
		//------------------------------------------------------//
		
		// (**주의사항**) 역할의 이름 지정시, 'ROLE_' 는 자동으로 추가됨.
		// 따라서, 역할 지정시, 역할의 이름만 지정하고 'ROLE_'는 지정하지 말아야 함 (그렇지 않으면, 오류발생)
		
		auth.
			inMemoryAuthentication().
			
			withUser("admin").
			password("{noop}admin").
			roles("ADMIN", "MEMBER").
			
			and().
			
			withUser("member").
			password("{noop}member").
			roles("MEMBER");
		
	} // configure
	
	
	
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
