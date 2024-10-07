package org.zerock.mybatis.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zerock.mybatis.domain.BoardVO;
import org.zerock.mybatis.domain.MemberVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor
public final class MyBatisByMapperXmlTests {
		
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
	
	
//	@Test(timeout=1000)
	@Test
	public void testSelectAllBoards() {
		log.debug("testSelectAllBoards() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			String namespace = "BoardMapper";
			String sqlId = "selectAllBoards";
			
			List<BoardVO> boards = sqlSession.selectList(namespace+"."+sqlId);
			
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
		} // try-with-resources
	} // testSelectAllBoards
	
	
	@Test
	public void testSelectAllBoardsByMapperInterface() {
		log.debug("testSelectAllBoardsByMapperInterface() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			log.info("\t+ mapper: " + mapper);
			
			Objects.requireNonNull(mapper);
			
			List<BoardVO> boards = mapper.selectAllBoards();
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
		} // try-with-resources
	} // testSelectAllBoardsByMapperInterface
	
	
	@Test
	public void testSelectBoard() {
		log.debug("testSelectBoard() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			String namespace = "BoardMapper";
			String sqlId = "selectBoard";
			
			BoardVO board = sqlSession.selectOne(namespace+"."+sqlId, 191);
			log.info("\t+ board: " + board);
		} // try-with-resources
	} // testSelectBoard
	
	
	@Test
	public void testSelectBoardByMapperInterface() {
		log.debug("testSelectBoardByMapperInterface() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			 BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			 log.info("\t+ mapper: " + mapper);
			 
			 Objects.requireNonNull(mapper);
			 
			 BoardVO board = mapper.selectBoard(100);
			 log.info("\t+ board: " + board);
		} // try-with-resources
	} // testSelectBoardByMapperInterface
	
	
	@Test
	public void testSelectAllMembers() {
		log.debug("testSelectAllMembers() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			String namespace = "MemberMapper";
			String sqlId = "selectAllMembers";
			
			List<MemberVO> members = sqlSession.selectList(namespace+"."+sqlId);
			
			Objects.requireNonNull(members);
			members.forEach(log::info);
		} // try-with-resources
	} // testSelectAllMembers
	
	
	@Test
	public void testSelectAllMembersByMapperInterface() {
		log.debug("testSelectAllMembersByMapperInterface() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
			log.info("\t+ mapper: " + mapper);
			
			Objects.requireNonNull(mapper);
			
			List<MemberVO> members = mapper.selectAllMembers();
			Objects.requireNonNull(members);
			
			members.forEach(log::info);
		} // try-with-resources
	} // testSelectAllMembersByMapperInterface
	
	
	@Test
	public void testSelectMember() {
		log.debug("testSelectMember() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			String namespace = "MemberMapper";
			String sqlId = "selectMember";
			
			MemberVO member = sqlSession.selectOne(namespace+"."+sqlId, "user7");
			log.info("\t+ member: " + member);
		} // try-with-resources
	} // testSelectMember
	
	
	@Test
	public void testSelectMemberByMapperInterface() {
		log.debug("testSelectMemberByMapperInterface() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
			log.info("\t+ mapper: " + mapper);
			
			Objects.requireNonNull(mapper);
			
			MemberVO member = mapper.selectMember("user3");
			log.info("\t+ member: " + member);
		} // try-with-resources
	} // testSelectMemberByMapperInterface
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // tearDown

} // end class
