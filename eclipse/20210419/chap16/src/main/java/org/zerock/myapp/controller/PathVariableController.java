package org.zerock.myapp.controller;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor

@RequestMapping("/sample3")
@RestController("pathVariableController")
public class PathVariableController
	implements InitializingBean, DisposableBean {
	
	
	
//	@GetMapping("/product/{category}/{productId}")													// XML
	
//	@GetMapping(path="/product/{category}/{productId}", produces=MediaType.APPLICATION_XML_VALUE)	// XML
	
	@GetMapping(path="/product/{category}/{productId}", produces=MediaType.APPLICATION_JSON_VALUE)	// JSON
	public String[] getPathVariables(
			@PathVariable("category") String category,
			@PathVariable("productId") Integer productId
		) {
		log.debug("getPathVariables() invoked.");
		
		log.info(String.format("\t+ category: %s, productId: %d", category, productId));
		
		return new String[] { "category: " + category, "productId: " + productId };
	} // getPathVariables

	
	
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
