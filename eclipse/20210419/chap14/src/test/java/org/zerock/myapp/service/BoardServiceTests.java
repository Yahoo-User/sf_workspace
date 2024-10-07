package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.Criteria;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations= {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class BoardServiceTests {
	
	@Setter(onMethod_= {@Autowired})
	private BoardService service;
	
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		Objects.requireNonNull(service);
		log.info("\t+ service: " + service);
	} // setup
	
	
	@Test(timeout=1000)
	public void testRegister() {
		log.debug("testRegister() invoked.");
		
		BoardVO board = new BoardVO(null, "TITLE_NEW", "CONTENT_NEW", "WRITER_NEW", null, null);
		log.info("\t+ board: " + board);
		
		if(service.register(board)) {
			log.info("\t+ New board registered.");
		} else {
			log.info("\t+ No board registered.");
		} // if-else
	} // testRegister
	
	
	@Test(timeout=1000)
	public void testGetList() {
		log.debug("testGetList() invoked.");
		
		List<BoardVO> boards = service.getList();
		
		boards.forEach(log::info); // forEach
		
		boards.clear();
		boards = null;
	} // testGetList
	
	
	@Test(timeout=1000)
	public void testGetListPerPage() {
		log.debug("testGetListPerPage() invoked.");
		
		Criteria cri = new Criteria();
		cri.setAmount(20);
		cri.setCurrPage(2);
		cri.setPagesPerPage(10);
		
		List<BoardVO> boards = service.getListPerPage(cri);
		
		boards.forEach(log::info);
		
		boards.clear();
		boards = null;
	} // testGetListPerPage
	
	
	@Test(timeout=1000)
	public void testGetTotal() {
		log.debug("testGetTotal() invoked.");
		
		Criteria cri = new Criteria();
		cri.setCurrPage(3);
		cri.setAmount(20);
		cri.setPagesPerPage(10);
		
		int total = service.getTotal(cri);
		log.info("\t+ total: " + total);
	} // testGetTotal
	
	
	@Test(timeout=1000)
	public void testGet() {
		log.debug("testGet() invoked.");
		
		BoardVO board = service.get(56);
		
		Objects.requireNonNull(board);
		log.info("\t+ board: " + board);
	} // testGet
	
	
	@Test(timeout=1000)
	public void testRemove() {
		log.debug("testRemove() invoked.");
		
		if(service.remove(56)) {
			log.info("\t+ board removed.");
		} else {
			log.info("\t+ No board removed.");
		} // if-else
	} // testRemove
	
	
	@Test(timeout=1000)
	public void testModify() {
		log.debug("testModify() invoked.");
		
		BoardVO board = new BoardVO(14, "TITLE-Modified", "CONTENT-Modified", "WRITER-Modified", null, null);
		
		if(service.modify(board)) {
			log.info("\t+ board modified.");
		} else {
			log.info("\t+ No board modified.");
		} // if-else
	} // testModify
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // tearDown

} // end class
