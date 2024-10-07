package org.zerock.myapp.domain;

import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor

public class CriteriaTests {
	
	private Criteria cri;
	
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		this.cri = new Criteria();
		this.cri.setCurrPage(1);
		this.cri.setAmount(20);
		this.cri.setType("T");
		this.cri.setKeyword("1000");
		
		log.info("\t+ cri: " + cri);
	} // setup
	
	
	@Test
	public void testGetPagingUri() {
		log.debug("testGetPagingUri() invoked.");
		
		Objects.requireNonNull(cri);
		log.info("\t+ getPagingUri: " + this.cri.getPagingUri());
	} // testGetPagingUri
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // tearDown

} // end class
