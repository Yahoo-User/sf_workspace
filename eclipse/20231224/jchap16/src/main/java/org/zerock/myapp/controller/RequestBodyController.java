package org.zerock.myapp.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.Ticket;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RequestMapping("/sample4")
@RestController("requestBodyController")
public class RequestBodyController {
	
	
	
	// For POST Request including Ticket JSON.
//	@PostMapping("/ticket")													// XML
	
//	@PostMapping(path="/ticket", produces=MediaType.APPLICATION_XML_VALUE)	// XML
	
	@PostMapping(path="/ticket", produces=MediaType.APPLICATION_JSON_VALUE) // JSON
	public Ticket convertToTicket(@RequestBody Ticket ticket) {
		log.debug("convertToTicket(ticket) invoked.");
		
		log.info("\t+ ticket: " + ticket);
		
		return ticket;
	} // convertToTicket

} // end class
