package org.zerock.myapp.service;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	
	@Override
	public void testCronJob() {
		log.trace("testCronJob() invoked.");
		
	} // testCronJob

	@Override
	public void testSimpleJob() {
		log.trace("testSimpleJob() invoked.");
		
	} // testSimpleJob

} // end class
