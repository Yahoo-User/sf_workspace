package org.zerock.myapp.domain;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CriteriaTests {
	
	private Criteria cri;
	

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		this.cri = new Criteria();
		this.cri.setCurrPage(1);
		this.cri.setAmount(20);
		this.cri.setType("T");
		this.cri.setKeyword("1000");
		
		log.info("\t+ cri: " + cri);
	} // beforeAll
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testGetPagingUri")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testGetPagingUri() {
		log.debug("testGetPagingUri() invoked.");
		
		Objects.requireNonNull(cri);
		log.info("\t+ getPagingUri: " + this.cri.getPagingUri());
	} // testGetPagingUri

	
	

} // end class
