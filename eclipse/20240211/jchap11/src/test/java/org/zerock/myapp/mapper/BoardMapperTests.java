package org.zerock.myapp.mapper;

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
import org.zerock.myapp.config.RootContextXml;
import org.zerock.myapp.domain.BoardVO;

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

@ContextConfiguration(classes= { RootContextXml.class })
public class BoardMapperTests {
	
	@Setter(onMethod_= {@Autowired})
	private BoardMapper mapper;
	

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		Objects.requireNonNull(mapper);
		log.info("\t+ mapper: " + mapper);
		log.info("\t+ type: " + mapper.getClass().getName());
	} // beforeAll
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testGetList")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testGetList() {
		log.debug("testGetList() invoked.");
		
		List<BoardVO> list = mapper.getList();
		list.forEach(log::info);	// forEach
	} // testGetList
	

//	@Disabled
	@Test
	@Order(2)
	@DisplayName("testInsert")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testInsert() {
		log.debug("testInsert() invoked.");
		
		BoardVO board = new BoardVO(null, "TITLE_1000", "CONTENT_1000", "WRITER_1000", null, null);
		int affectedRows = mapper.insert(board);
		log.info("\t+ affectedRows: " + affectedRows);
		log.info("\t+ board: " + board);
	} // testInsert
	

//	@Disabled
	@Test
	@Order(3)
	@DisplayName("testInsertSelectKey")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testInsertSelectKey() {
		log.debug("testInsertSelectKey() invoked.");
		
		BoardVO board = new BoardVO(null, "TITLE_1000", "CONTENT_1000", "WRITER_1000", null, null);
		int affectedRows = mapper.insertSelectKey(board);
		log.info("\t+ affectedRows: " + affectedRows);
		log.info("\t+ board: " + board);
	} // testInsertSelectKey
	

//	@Disabled
	@Test
	@Order(4)
	@DisplayName("testRead")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testRead() {
		log.debug("testRead() invoked.");
		
		BoardVO board = mapper.read(72);
		Objects.requireNonNull(board);
		
		log.info(board);
	} // testRead
	

//	@Disabled
	@Test
	@Order(5)
	@DisplayName("testDelete")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testDelete() {
		log.debug("testDelete() invoked.");
		
		int affectedRows = mapper.delete(72);
		log.info("\t+ affectedRows: " + affectedRows);
	} // testDelete
	

//	@Disabled
	@Test
	@Order(6)
	@DisplayName("testUpdate")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	public void testUpdate() {
		log.debug("testUpdate() invoked.");
		
		BoardVO board = new BoardVO(9, "TITLE-MODIFIED", "CONTENT-MODIFIED", "WRITER-MODIFIED", null, null);
		int affectedRows = mapper.update(board);
		
		log.info("\t+ affectedRows: " + affectedRows);
	} // testUpdate

	

} // end class
