package org.zerock.myapp.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Aspect
@Component("logAdvice")
public class LogAdvice {
	
	
	
	@Before( "execution(* org.zerock.myapp.service.SampleTxService.addData(String)) && args(value)" )
	public void logBeforeWithParams(String value) {
		log.debug("================================================");
		log.debug("logBeforeWithParams(value) invoked.");
		log.debug("================================================");
		
		if(value != null) {
			log.info("\t+ value: " + value);
			log.info("\t+ length: " + value.length());
		} // if
	} // logBeforeWithParams
	

} // end class
