package org.zerock.myapp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor
public class CustomLogoutSuccessHandler
	implements LogoutSuccessHandler {

	
	
	@Override
	public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth)
			throws IOException, ServletException {
		log.debug("onLogoutSuccess(req, res, auth) invoked.");

		
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

		
		res.sendRedirect("/customLogin");
	} // onLogoutSuccess

} // end class
