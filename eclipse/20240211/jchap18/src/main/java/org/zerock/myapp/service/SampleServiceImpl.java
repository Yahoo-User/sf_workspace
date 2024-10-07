package org.zerock.myapp.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service("sampleService")
public class SampleServiceImpl
	implements SampleService, InitializingBean, DisposableBean {

	
	
	@Override
	public Integer doAdd(String s1, String s2) throws Exception {
		log.debug("doAdd(s1, s2) invoked.");

		return Integer.parseInt(s1) + Integer.parseInt(s2);
	} // doAdd



	@Override
	public void method2() throws Exception {
		log.debug("method2() invoked.");
		
		Thread.sleep(1000 * 2);
	} // method2

	
	
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
