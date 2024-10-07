package org.zerock.myapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor


@Controller
public class CommonController {
	
	public static final String messageKey = "__MESSAGE__";
	public static final String errorKey = "__ERROR__";
	public static final String logoutKey = "__LOGOUT__";
	
	
	
	@GetMapping("/accessDenied")
	public void accessDenied(Authentication auth, Model model) {
		log.debug("accessDenied(auth, model) invoked.");
		
		log.info("\t+ auth: " + auth);
		
		model.addAttribute(messageKey, auth);
	} // accessDenied
	
	
	@GetMapping("/customLogin")
	public void customLoginForm(String error, String logout, Model model) {
		log.debug("customLoginForm(error, logout, model) invoked.");
		
		log.info("\t+ 1. error: " + error);
		log.info("\t+ 2. logout: " + logout);
		
		if(error != null) {
			model.addAttribute(errorKey, "Login Error! Check Your Account....");
		} // if
		
		if(logout != null) {
			model.addAttribute(logoutKey, "Signed Out.....");
		} // if
	} // customLoginForm
	
	
	@GetMapping("/customLogout")
	public void customLogoutGet() {
		log.debug("customLogoutGet() invoked.");
		
	} // customLogoutGet
	
	
	@PostMapping("/customLogout")
	public void customLogoutPost() {
		log.debug("customLogoutPost() invoked.");
		
	} // customLogoutPost

} // end class
