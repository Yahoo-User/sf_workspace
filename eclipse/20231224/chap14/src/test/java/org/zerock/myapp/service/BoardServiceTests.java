package org.zerock.myapp.service;

import java.util.List;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.Criteria;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


//For JUnit 4
//@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)

//For JUnit 5
@ExtendWith(SpringExtension.class)

@ContextConfiguration(locations= {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class BoardServiceTests {
	
	@Setter(onMethod_= {@Autowired})
	private BoardService service;
	

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		Objects.requireNonNull(service);
		log.info("\t+ service: " + service);
	} // beforeAll
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testRegister")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
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
	

//	@Disabled
	@Test
	@Order(2)
	@DisplayName("testGetList")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testGetList() {
		log.debug("testGetList() invoked.");
		
		List<BoardVO> boards = service.getList();
		
		boards.forEach(log::info); // forEach
		
		boards.clear();
		boards = null;
	} // testGetList
	

//	@Disabled
	@Test
	@Order(3)
	@DisplayName("testGetListPerPage")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
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
	

//	@Disabled
	@Test
	@Order(4)
	@DisplayName("testGetTotal")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testGetTotal() {
		log.debug("testGetTotal() invoked.");
		
		Criteria cri = new Criteria();
		cri.setCurrPage(3);
		cri.setAmount(20);
		cri.setPagesPerPage(10);
		
		int total = service.getTotal(cri);
		log.info("\t+ total: " + total);
	} // testGetTotal
	

//	@Disabled
	@Test
	@Order(5)
	@DisplayName("testGet")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testGet() {
		log.debug("testGet() invoked.");
		
		BoardVO board = service.get(56);
		
		Objects.requireNonNull(board);
		log.info("\t+ board: " + board);
	} // testGet
	

//	@Disabled
	@Test
	@Order(6)
	@DisplayName("testRemove")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testRemove() {
		log.debug("testRemove() invoked.");
		
		if(service.remove(56)) {
			log.info("\t+ board removed.");
		} else {
			log.info("\t+ No board removed.");
		} // if-else
	} // testRemove
	

//	@Disabled
	@Test
	@Order(7)
	@DisplayName("testModify")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testModify() {
		log.debug("testModify() invoked.");
		
		BoardVO board = new BoardVO(14, "TITLE-Modified", "CONTENT-Modified", "WRITER-Modified", null, null);
		
		if(service.modify(board)) {
			log.info("\t+ board modified.");
		} else {
			log.info("\t+ No board modified.");
		} // if-else
	} // testModify

} // end class
