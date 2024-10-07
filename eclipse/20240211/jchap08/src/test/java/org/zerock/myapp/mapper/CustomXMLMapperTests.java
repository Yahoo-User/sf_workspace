package org.zerock.myapp.mapper;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.myapp.config.RootContextXml;
import org.zerock.myapp.domain.BoardVO;

import lombok.NoArgsConstructor;
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

@ContextConfiguration(classes= RootContextXml.class)
public class CustomXMLMapperTests {
	
//	@Setter(onMethod_= { @Autowired })
//	@Setter(onMethod_= { @Inject })
	
//	@Setter(onMethod_= { @Resource })
//	@Setter(onMethod_= { @Resource(type=SqlSessionFactory.class) })
	
	
//	@Setter(onMethod_= @Autowired)
//	@Setter(onMethod_= @Inject)
	
	
//	@Setter(onMethod_= @Resource)
//	@Setter(onMethod_= @Resource(type=SqlSessionFactory.class))
	
//	@Inject
//	@Autowired
	
//	@Resource
	@Resource(type = SqlSessionFactory.class)
	private SqlSessionFactory sqlSessionFactory;
	

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		Objects.requireNonNull(sqlSessionFactory);
		log.info("\t+ sqlSessionFactory: " + sqlSessionFactory);
	} // beforeAll
	
	
//	@Test(timeout=1000)
	@Test
	public void testGetList() {
		log.debug("testGetList() invoked.");
		
		SqlSession sess = sqlSessionFactory.openSession();
		
		try(sess) {
			
			String sql = "CustomBoardMapper" + "." + "getList";
			
			List<BoardVO> list = sess.<BoardVO>selectList(sql);
			
//			list.forEach(b -> {
//				
//				String board = String.format(
//						"%s, %s, %s, %s, %s, %s",
//						b.getBno(),
//						b.getTitle(),
//						b.getContent(),
//						b.getWriter(),
//						b.getInsert_ts(),
//						b.getUpdate_ts()
//					);
//				
//				log.info(board);
//			}); // forEach
			
			list.forEach(log::info);
			
			list.clear();
			list = null;
			
		} // try-with-resource on the JAVA 9 and above.
		
	} // testGetList

	

} // end class
