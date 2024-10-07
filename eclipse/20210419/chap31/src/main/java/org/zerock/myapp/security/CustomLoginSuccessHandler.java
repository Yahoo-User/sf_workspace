package org.zerock.myapp.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor
public class CustomLoginSuccessHandler
	implements AuthenticationSuccessHandler {

	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) 
			throws IOException, ServletException {
		log.debug("onAuthenticationSuccess(req, res, auth) invoked.");
		
		log.info("\t+ auth: " + auth);
		log.info("\t=================================================");
		log.info("\t1. isAuthenticated: "+ auth.isAuthenticated());
		log.info("\t2. Authorities: "+auth.getAuthorities());
		log.info("\t3. Credentials: "+auth.getCredentials());
		log.info("\t4. Details: "+auth.getDetails());
		
		String name = auth.getName();
		log.info("\t5. name: "+ name);
		
		User user = (User) auth.getPrincipal();
		log.info("\t6. User: "+ user);
		log.info("\t=================================================");
		
		if(auth.isAuthenticated()) {
			
			List<String> roles = new ArrayList<>();
			auth.getAuthorities().forEach( authority -> {
				roles.add(authority.getAuthority());
			});
			
			//--------------------------------------------//
			
			if(roles.contains("ROLE_ADMIN")) {
				res.sendRedirect("/sample/admin");
				
				return;
			} // if
			
			if(roles.contains("ROLE_MEMBER")) {
				res.sendRedirect("/sample/member");
				
				return;
			} // if
			
		} // if
		

		res.sendRedirect("/");
	} // onAuthenticationSuccess

} // end class
