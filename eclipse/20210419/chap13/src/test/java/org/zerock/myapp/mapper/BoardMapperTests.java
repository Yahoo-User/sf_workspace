package org.zerock.myapp.mapper;

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
public class BoardMapperTests {
	
	@Setter(onMethod_= {@Autowired})
	private BoardMapper mapper;
	
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		Objects.requireNonNull(mapper);
		log.info("\t+ mapper: " + mapper);
		log.info("\t+ type: " + mapper.getClass().getName());
	} // setup
	
	
	@Test(timeout=1000)
	public void testGetList() {
		log.debug("testGetList() invoked.");

		Objects.requireNonNull(mapper);
		
		List<BoardVO> list = mapper.getList();
		list.forEach(log::info);	// forEach
	} // testGetList
	
	
	@Test(timeout=1000)
	public void testGetListWithPaging() {
		log.debug("testGetListWithPaging() invoked.");
		
		Objects.requireNonNull(mapper);
		
		Criteria cri = new Criteria();
		cri.setCurrPage(3);
		cri.setAmount(20);
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(log::info);
	} // testGetListWithPaging
	
	
	@Test(timeout=1000)
	public void testInsert() {
		log.debug("testInsert() invoked.");
		
		Objects.requireNonNull(mapper);
		
		BoardVO board = new BoardVO(null, "TITLE_1000", "CONTENT_1000", "WRITER_1000", null, null);
		int affectedRows = mapper.insert(board);
		log.info("\t+ affectedRows: " + affectedRows);
		log.info("\t+ board: " + board);
	} // testInsert
	
	
	@Test(timeout=1000)
	public void testInsertSelectKey() {
		log.debug("testInsertSelectKey() invoked.");
		
		Objects.requireNonNull(mapper);
		
		BoardVO board = new BoardVO(null, "TITLE_1000", "CONTENT_1000", "WRITER_1000", null, null);
		int affectedRows = mapper.insertSelectKey(board);
		log.info("\t+ affectedRows: " + affectedRows);
		log.info("\t+ board: " + board);
	} // testInsertSelectKey
	
	
	@Test(timeout=1000)
	public void testRead() {
		log.debug("testRead() invoked.");
		
		Objects.requireNonNull(mapper);
		
		BoardVO board = mapper.read(243);
		Objects.requireNonNull(board);
		
		log.info(board);
	} // testRead
	
	
	@Test(timeout=1000)
	public void testDelete() {
		log.debug("testDelete() invoked.");

		Objects.requireNonNull(mapper);
		
		int affectedRows = mapper.delete(121);
		log.info("\t+ affectedRows: " + affectedRows);
	} // testDelete
	
	
	@Test(timeout=1000)
	public void testUpdate() {
		log.debug("testUpdate() invoked.");

		Objects.requireNonNull(mapper);
		
		BoardVO board = new BoardVO(11, "TITLE-MODIFIED", "CONTENT-MODIFIED", "WRITER-MODIFIED", null, null);
		int affectedRows = mapper.update(board);
		
		log.info("\t+ affectedRows: " + affectedRows);
	} // testUpdate
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // tearDown

} // end class
