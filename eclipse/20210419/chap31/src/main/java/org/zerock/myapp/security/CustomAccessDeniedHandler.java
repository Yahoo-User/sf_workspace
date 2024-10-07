package org.zerock.myapp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.zerock.myapp.controller.CommonController;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor
public class CustomAccessDeniedHandler
	implements AccessDeniedHandler {

	
	
	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e)
			throws IOException, ServletException {
		log.debug("handle(req, res, e) invoked.");
		
		log.info("\t+ e: " + e);
		
		if(e != null) {
			
			HttpSession session = req.getSession();
			
			if(session != null) {
				session.setAttribute(CommonController.errorKey, e);
			} // if
			
		} // if
		
		log.info("\t+ Redirect to /accessDenied");
		
		res.sendRedirect("/accessDenied");
	} // handle

} // end class
