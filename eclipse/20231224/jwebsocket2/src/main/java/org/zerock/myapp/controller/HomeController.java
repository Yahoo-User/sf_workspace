package org.zerock.myapp.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


/**
 * Handles requests for the application home page.
 */
@Log4j2
@NoArgsConstructor

@Controller
public class HomeController {
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Welcome home! The client locale is " + locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	} // home
	
	
	@GetMapping("/ws")
	public void doWS(HttpSession session) {
		log.debug("doWS(session) invoked.");
		
		session.setAttribute("KEY_1", "VALUE_1");
		session.setAttribute("KEY_2", "VALUE_2");
		session.setAttribute("KEY_3", "VALUE_3");
	} // doWS
	
	
	@GetMapping("/sessionBinding")
	public void doSessionBinding(HttpSession session) {
		log.debug("doSessionBinding(session) invoked.");
		
		session.setAttribute("KEY_4", "VALUE_4");
		session.setAttribute("KEY_5", "VALUE_5");
		session.setAttribute("KEY_6", "VALUE_6");
	} // doSessionBinding
	
} // end class
