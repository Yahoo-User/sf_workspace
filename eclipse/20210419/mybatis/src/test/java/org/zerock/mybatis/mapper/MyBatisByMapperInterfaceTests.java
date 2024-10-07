package org.zerock.mybatis.mapper;

import java.util.List;
import java.util.Objects;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zerock.mybatis.domain.BoardVO;
import org.zerock.mybatis.domain.MemberVO;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor
public class MyBatisByMapperInterfaceTests {

	private SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
	private SqlSessionFactory sqlSessionFactory;
	
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		//-----------------------------------------------------//
		
		HikariConfig hikariConfig = new HikariConfig();
		
//		hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		
//		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@atp20191201_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@atp20191201_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP");
		
		hikariConfig.setUsername("ADMIN");
		hikariConfig.setPassword("Oracle12345!!!");
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setConnectionTimeout(1000);
		hikariConfig.setDataSourceJNDI("jdbc/HikariCP");
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		
		Objects.requireNonNull(hikariDataSource);
		log.info("\t+ dataSource: " + hikariDataSource);
		
		//-----------------------------------------------------//
		
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		log.info("\t+ transactionFactory: " + transactionFactory);
		
		//-----------------------------------------------------//
		
		Environment env = new Environment("development", transactionFactory, hikariDataSource);
		log.info("\t+ env: " + env);
		
		//-----------------------------------------------------//
		
		Configuration mybatisConfig = new Configuration(env);
		log.info("\t+ mybatisConfig: " + mybatisConfig);
		
		mybatisConfig.addMapper(BoardMapper.class);
		mybatisConfig.addMapper(MemberMapper.class);
		
		//-----------------------------------------------------//
		
		this.sqlSessionFactory = builder.build(mybatisConfig);
		Objects.requireNonNull(this.sqlSessionFactory);
		
		log.info("\t+ sqlSessionFactory: " + this.sqlSessionFactory);
	} // setup
	
	
	@Test
	public void testSelectAllBoards() {
		log.debug("testSelectAllBoards() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try (sqlSession) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			log.info("\t+ mapper: " + mapper);
			
			Objects.requireNonNull(mapper);
			
			List<BoardVO> boards = mapper.selectAllBoards();
			boards.forEach(log::info);
		} // try-with-resources
	} // testSelectAllBoards
	
	
	@Test
	public void testSelectBoard() {
		log.debug("testSelectBoard() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			log.info("\t+ mapper: " + mapper);
			
			Objects.requireNonNull(mapper);
			
			BoardVO board = mapper.selectBoard(176);
			log.info("\t+ board: " + board);
		} // try-with-resources
	} // testSelectBoard
	
	
	@Test
	public void testSelectAllMembers() {
		log.debug("testSelectAllMembers() invoked.");
		
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
	} // testSelectAllMembers
	
	
	@Test
	public void testSelectMember() {
		log.debug("testSelectMember() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
			log.info("\t+ mapper: " + mapper);
			
			Objects.requireNonNull(mapper);
			
			MemberVO member = mapper.selectMember("user9");
			log.info("\t+ member: " + member);
		} // try-with-resources
	} // testSelectMember
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
		
	} // tearDown

} // end class
