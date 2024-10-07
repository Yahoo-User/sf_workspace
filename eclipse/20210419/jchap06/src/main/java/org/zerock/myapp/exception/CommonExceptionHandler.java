package org.zerock.myapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor

@ControllerAdvice		// For all packages.
//@ControllerAdvice(basePackages= { "org.zerock.myapp.controller" })	// Only for the specified packages.
public class CommonExceptionHandler {
	
	
	@ExceptionHandler( BindException.class )
	public String handleBindException(Exception e, Model model) {
		log.debug("handleBindException(e, model) invoked.");
		
		log.error("1. Exception Type: " + e.getClass().getName());
		log.error("2. Exception Message: " + e.getMessage());
		
		model.addAttribute("exception", e);
		
		return "errorPage";
	} // handleBindException
	
	
	@ExceptionHandler( IllegalStateException.class )
	public String handleIllegalStateException(Exception e, Model model) {
		log.debug("handleIllegalStateException(e, model) invoked.");
		
		log.error("1. Exception Type: " + e.getClass().getName());
		log.error("2. Exception Message: " + e.getMessage());
		
		model.addAttribute("exception", e);
		
		return "errorPage";
	} // handleIllegalStateException
	
	
	// To determine HTTP status code of the response.
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	@ExceptionHandler( { NoHandlerFoundException.class } )
	public String handleNoHandlerFoundException(Exception e, Model model) {
		log.debug("handleNoHandlerFoundException(e, model) invoked.");
		
		log.error("1. Exception Type: " + e.getClass().getName());
		log.error("2. Exception Message: " + e.getMessage());
		
		model.addAttribute("exception", e);
		
		return "errorPage";
	} // handleNoHandlerFoundException

} // end class
