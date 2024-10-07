package org.zerock.myapp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.myapp.domain.SampleDTO;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor

@RequestMapping("/return/*")
@Controller
public class ReturnTypesController {
	
	//--------------------------------------------------------//
	// 1. void return type
	//--------------------------------------------------------//
	@GetMapping("/void")
	public void returnVoid() {
		log.debug("returnVoid() invoked.");
		
		//=> /WEB-INF/views/ + return/void + .jsp
	} // ex01

	
	//--------------------------------------------------------//
	// 2. String return type
	//--------------------------------------------------------//
	@GetMapping("/String")
	public String returnString() {
		log.debug("returnString() invoked.");
		
		return "return/void";	//=> /WEB-INF/views/ + return/void + .jsp
	} // returnString

	
	//--------------------------------------------------------//
	// 2-1. String return type : "redirect:" keyword
	//--------------------------------------------------------//
	@PostMapping("/redirect")
	public String returnRedirect() {
		log.debug("returnRedirect() invoked.");
		
//		return "redirect:/return/void";				// OK
		return "redirect:void";						// OK
//		return "redirect:http://localhost:8007";	// OK
	} // returnRedirect

	
	//--------------------------------------------------------//
	// 2-2. String return type : "forward:" keyword
	//--------------------------------------------------------//
	@GetMapping("/forward")
	public String returnForward() {
		log.debug("returnForward() invoked.");
		
//		return "forward:/WEB-INF/views/return/void.jsp";
//		return "forward:/return/void";			// OK
		return "forward:void";					// OK
//		return "forward:http://localhost:8007";	// XX: search "/return/http://localhost:8007"
	} // returnForward
	
	
	//--------------------------------------------------------//
	// 3. Object return type using @ResponseBody
	//--------------------------------------------------------//
	// To return JSON format document
	//--------------------------------------------------------//
	@PostMapping("/ResponseBody")
	public @ResponseBody SampleDTO returnResponseBody(
			@NonNull @RequestParam("name") String NAME,
			@NonNull @RequestParam("age") Integer AGE
		) {
		log.debug("returnResponseBody(name, age) invoked.");
		
		SampleDTO dto = new SampleDTO();
		dto.setName(NAME);
		dto.setAge(AGE);
		
		log.info("\t+ dto: " + dto);
		
		return dto;
	} // returnResponseBody
	
	
	//--------------------------------------------------------//
	// 4. Object return type using @ResponseEntity
	//--------------------------------------------------------//
	// To return JSON format document including User-defined HEADERS and BODY, HTTP status
	//--------------------------------------------------------//
	@PostMapping("/ResponseEntity")
	public ResponseEntity<String> returnResponseEntity() {
		log.debug("returnResponseEntity() invoked.");
		
		String json = "{ 'name': 'Yoseph', 'age': 23 }";
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf8");
		
		return new ResponseEntity<>(json, headers, HttpStatus.OK);
	} // returnResponseEntity
	
} // end class
