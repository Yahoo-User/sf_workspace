package org.zerock.mybatis.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zerock.mybatis.domain.BoardVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor
public final class MyBatisDynamicSQLTests {
		
	private SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
	private SqlSessionFactory sqlSessionFactory;
	

	
	@Before
	public void setup() throws IOException {
		log.debug("setup() invoked.");
		
		//-----------------------------------------------------//
		
		//--1st. method by using CLASSPATH.
		String mybatisConfigXml = "mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(mybatisConfigXml);
		
		//-----------------------------------------------------//
		
		//--2nd. method by using file path.
//		String mybatisConfigXml = "C:/temp/mybatis-config.xml";
//		File f = new File(mybatisConfigXml);
//		FileInputStream is = new FileInputStream(f);
		
		//-----------------------------------------------------//
		
		try (is) {
			this.sqlSessionFactory = builder.build(is);
			
			Objects.requireNonNull(this.sqlSessionFactory);
			log.info("\t+ sqlSessionFactory: " + this.sqlSessionFactory);
		} // try-with-resources
	} // setup
	
	
	@Test
	public void testFindBoardByBno() {
		log.debug("testFindBoardByBno() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			
			Integer bno = 172;
//			Integer bno = null;
			
			String namespace = "BoardMapper";
			String sqlId = "findBoardByBno";
			
			BoardVO board = sqlSession.<BoardVO>selectOne(namespace+"."+sqlId, bno);
			log.info("\t+ board: " + board);
		} // try-with-resources
	} // testFindBoardByBno
	
	
	@Test
	public void testFindBoardsByTitle() {
		log.debug("testFindBoardsByTitle() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			
//			String title = null;
//			String title = "777";
			
			String title = "%777%";
			
			String namespace = "BoardMapper";
			String sqlId = "findBoardsByTitle";
			
			List<BoardVO> boards = sqlSession.<BoardVO>selectList(namespace+"."+sqlId, title);
			
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
		} // try-with-resources
	} // testFindBoardsByTitle
	
	
	@Test
	public void testFindBoardsByWriter() {
		log.debug("testFindBoardsByWriter() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
//			String writer = null;
//			String writer = "trinity";
			String writer = "%1000%";
			
			String namespace = "BoardMapper";
			String sqlId = "findBoardsByWriter";
			
			List<BoardVO> boards = sqlSession.<BoardVO>selectList(namespace+"."+sqlId, writer);
			
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
		} // try-with-resources
		
	} // testFindBoardsByWriter
	
	
	@Test
	public void testFindBoardsByBnoAndTitle() {
		log.debug("testFindBoardsByBnoAndTitle() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
//			Integer bno = null;
//			String title = null;
			
//			Integer bno = 172;
//			String title = null;
			
//			Integer bno = null;
//			String title = "%1000%";
			
			Integer bno = 313;
			String title = "%1000%";
			
			String namespace = "BoardMapper";
			String sqlId = "findBoardsByBnoAndTitle";
			
			Map<String, Object> params = new HashMap<>();
			params.put("bno", bno);
			params.put("title", title);
			
			List<BoardVO> boards = sqlSession.selectList(namespace+"."+sqlId, params);
			
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
		} // try-with-resources
	} // testFindBoardsByBnoAndTitle
	
	
	@Test
	public void testFindBoardsBySomeBnos() {
		log.debug("testFindBoardsBySomeBnos() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
//			Integer[] bnoList = { 353, 354, 355, 356 };
//			log.info("\t+ bnoList: " + Arrays.toString(bnoList));
			
			List<Integer> bnoList = Arrays.asList(353, 354, 355, 356);
			log.info("\t+ bnoList: " + bnoList);
			
			
			String namespace = "BoardMapper";
			String sqlId = "findBoardsBySomeBnos";
			
			List<BoardVO> boards = sqlSession.<BoardVO>selectList(namespace+"."+sqlId, bnoList);
			
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
		} // try-with-resources
	} // testFindBoardsBySomeBnos
	
	
	@Test
	public void testFindBoardsByBnoOrTitle() {
		log.debug("testFindBoardsByBnoOrTitle() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			
			Map<String, Object> params = new HashMap<>();
			
//			params.put("bno", 372);
//			params.put("title", "%1000%");
	
			
			String namespace = "BoardMapper";
			String sqlId = "findBoardsByBnoOrTitle";
			
			List<BoardVO> boards = sqlSession.selectList(namespace+"."+sqlId, params);
			
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
		} // try-with-resources
	} // testFindBoardsByBnoOrTitle
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // tearDown

} // end class
