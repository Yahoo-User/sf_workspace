package org.zerock.myapp.service;

import java.util.Objects;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.myapp.mapper.Sample1Mapper;
import org.zerock.myapp.mapper.Sample2Mapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor

@Service("sampleTxService")
public class SampleTxServiceImpl
	implements SampleTxService, InitializingBean, DisposableBean {
	
	
	@Setter(onMethod_= {@Autowired})
	private Sample1Mapper mapper1;
	
	@Setter(onMethod_= {@Autowired})
	private Sample2Mapper mapper2;

	
	
	@Transactional
	
//	@Transactional(propagation=Propagation.REQUIRED)		// default
//	@Transactional(propagation=Propagation.MANDATORY)		// 반드시 특정 TX 하에서만 수행가능
//	@Transactional(propagation=Propagation.NOT_SUPPORTED)	// 기존 TX가 있는 경우, 이 TX이 끝날 때까지 보류한 후, 수행
//	@Transactional(propagation=Propagation.SUPPORTS)		// TX가 필요없으나, TX 하에 있다면 포함되어 실행
//	@Transactional(propagation=Propagation.REQUIRED)		// 기존 Tx가 있는 경우 사용, 없으면 새로운 TX 생성하여 실행
//	@Transactional(propagation=Propagation.REQUIRES_NEW)	// 무조건 자신만의 고유한 TX 생성하여 실행
//	@Transactional(propagation=Propagation.NESTED)			// 기존 TX 이 존재하는 경우, 이 Tx에 포함되어 수행
//	@Transactional(propagation=Propagation.NEVER)			// TX 없이 수행. 만일 TX하에서 수행하면 오류발생
	
	@Override
	public void addData(String value) throws Exception {
		log.debug("addData(value) invoked.");
		
		log.info("\t+ value: " + value);
		
		Objects.requireNonNull(mapper1);
		Objects.requireNonNull(mapper2);
		
		log.info("\t+ mapper1: " + mapper1 + ", type: " + mapper1.getClass().getName());
		log.info("\t+ mapper2: " + mapper2 + ", type: " + mapper2.getClass().getName());
		
		mapper1.insertCol(value);
		mapper2.insertCol(value);
		
		log.info("Done.");
	} // addData

	
	
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
