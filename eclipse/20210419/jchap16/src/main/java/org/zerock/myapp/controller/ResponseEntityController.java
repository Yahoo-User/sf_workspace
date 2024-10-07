package org.zerock.myapp.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.tribes.util.Arrays;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.SampleVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j

@NoArgsConstructor
//@AllArgsConstructor

@RequestMapping("/sample2")
@RestController("responseEntityController")
public class ResponseEntityController
	implements InitializingBean, DisposableBean {
	
	// (1) Spring v4.3 이후로, 필드가 1개이고, 매개변수 있는 생성자만 있으면
	//     생성자를 통한 빈 객체 자동주입.
	//
	// (2) 아니면, 아래의 static initializer를 통해, 스프링 DataSource 빈객체를 주입받지 않고
	//     직접 Servlet Container에 생성한 DataSource 객체(/META-INF/context.xml)를 JNDI lookup를
	//     통해서 초기화 시킴.
//	private DataSource dataSource;
	private static DataSource dataSource;
	
	
	
	static {
		Context ctx = null;
		
		try {
			ctx = new InitialContext();
			
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/ShareOracleCloudUsingDriverSpy");
			
			Objects.requireNonNull(dataSource);
			log.info("dataSource: " + dataSource);
		} catch (NamingException | NullPointerException e) {
			e.printStackTrace();
		} finally {
			if(ctx != null) {
				try { ctx.close(); } catch (NamingException e) {;;}
			} // if
		} // try-catch-finally
	} // static initializer
	
	
	
	// 만일, HTTP request 에, params 에 지정한 요청 파라미터가 안오면, 아래의 오류 발생
	// UnsatisfiedServletRequestParameterException: 
	//		Parameter conditions "height, weight" not met for actual request parameters
	
//	@GetMapping(path="/check", params= { "height", "weight" })			// XML : 한글 안깨짐
	
//	@GetMapping(
//		path="/check",
//		params= { "height", "weight" },
//		produces=MediaType.APPLICATION_XML_VALUE
//	)																	// XML : 한글 안깨짐
	
	@GetMapping(
		path="/check",
		params = { "height", "weight" },
		produces = MediaType.APPLICATION_JSON_VALUE
	)																	// JSON	: 한글 안깨짐
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
		log.debug("check(height, weight) invoked.");
		
		log.info(String.format("\t+ height: %f, weight: %f", height, weight));
		 
		SampleVO sample = new SampleVO(1000, "홍", "길동");
		log.info("\t+ sample: " + sample);
		
		
		ResponseEntity<SampleVO> result = null;
		BodyBuilder bodyBuilder = null;
		
		if(height < 150) {
			bodyBuilder = ResponseEntity.status(HttpStatus.BAD_REQUEST);
		} else {
			bodyBuilder = ResponseEntity.status(HttpStatus.OK);
		} // if-else
		
		log.info("\t+ bodyBuilder: " + bodyBuilder);
		
//		result = bodyBuilder.<SampleVO>body(sample);
		result = bodyBuilder.body(sample);
		
		log.info("\t+ result: " + result);
		
		
		return result;
	} // check
	
	
	
//	@GetMapping(path = "/checkBoards", params = { "bno" })		// XML
	
//	@GetMapping(												// XML
//		path="/checkBoards",
//		params = "bno",
//		produces = MediaType.APPLICATION_XML_VALUE
//	)
	
	@GetMapping(												// JSON
		path="/checkBoards",
		params = "bno",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity< List<BoardVO> > checkBoards(@RequestParam("bno") Integer[] bnoList) {
		log.debug("checkBoards(bnoList) invoked.");
		
		log.info("\t+ bnoList: " + Arrays.toString(bnoList));
		
		//-----------------------------------------------------------------//
		BodyBuilder bodyBuilder = null;
		
		if(bnoList == null || bnoList.length == 0) {
			bodyBuilder = ResponseEntity.status(HttpStatus.BAD_REQUEST);
			
//			return bodyBuilder.< List<BoardVO> >body(null);
			return bodyBuilder.body(null);
		} // if
		//-----------------------------------------------------------------//
		
		List<BoardVO> boardList = null;
		
		try {
			Connection conn = dataSource.getConnection();
			
			String sql = "SELECT /*+ INDEX_DESC(tbl_board) */ * FROM tbl_board WHERE bno IN (";
			
			for(int i=1; i<= bnoList.length; i++) {
				if(i < bnoList.length) 	sql = sql + "?, ";
				if(i == bnoList.length) sql = sql + "?";
			} // for
			
			sql = sql + ")";
			
			log.info("\t+ sql: " + sql);
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			for(Integer bno: bnoList) {
				pstmt.setInt(index++, bno);
			} // enhanced for
			
			ResultSet rs = pstmt.executeQuery();
			
			try(conn; pstmt; rs) {
				
				boardList = new ArrayList<>();
				
				while(rs.next()) {
					Integer bno 	= rs.getInt("bno");
					String	title 	= rs.getString("title");
					String 	content	= rs.getString("content");
					String 	writer	= rs.getString("writer");
					
					BoardVO board = new BoardVO(bno, title, content, writer);
					
					boardList.add(board);
				} // while
				
				boardList.forEach(log::info);
				
			} // try-with-resources
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		
		
		bodyBuilder = ResponseEntity.status(HttpStatus.OK);
		
//		ResponseEntity<List<BoardVO>> result = bodyBuilder.< List<BoardVO> >body(boardList);
		ResponseEntity<List<BoardVO>> result = bodyBuilder.body(boardList);
		
		
		return result;
	} // checkBoards

	
	
	//===================================================//

	@Override
	public void destroy() throws Exception {
		log.debug("destroy() invoked.");
		
	} // destroy

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("afterPropertiesSet() invoked.");
		
	} // afterPropertiesSet

} // end class
