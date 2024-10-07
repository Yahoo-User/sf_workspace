package org.zerock.myapp.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.SampleVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j

//@NoArgsConstructor
@AllArgsConstructor

@RequestMapping("/sample")
@RestController("stringXMLJSONController")
public class StringXMLJSONController
	implements InitializingBean, DisposableBean {
	
	// (1) Spring v4.3 이후로, 필드가 1개이고, 매개변수 있는 생성자만 있으면
	//     생성자를 통한 빈 객체 자동주입.
	//
	// (2) 아니면, 아래의 static initializer를 통해, 스프링 DataSource 빈객체를 주입받지 않고
	//     직접 Servlet Container에 생성한 DataSource 객체(/META-INF/context.xml)를 JNDI lookup를
	//     통해서 초기화 시킴.
	private static DataSource dataSource;
	
	
	
//	static {
//		Context ctx = null;
//		
//		try {
//			ctx = new InitialContext();
//			
//			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/ShareOracleCloudUsingDriverSpy");
//			
//			Objects.requireNonNull(dataSource);
//			log.info("dataSource: " + dataSource);
//		} catch (NamingException | NullPointerException e) {
//			e.printStackTrace();
//		} finally {
//			if(ctx != null) {
//				try { ctx.close(); } catch (NamingException e) {;;}
//			} // if
//		} // try-catch-finally
//	} // static initializer

	
	
	/* 
	 * *********************************************
	 * produces = "text/plain"
	 * produces = {"text/plain", "application/*"}
	 * produces = MediaType.TEXT_PLAIN_VALUE
	 * produces = "text/plain;charset=UTF-8"
	 * *********************************************
	 */
	@RequestMapping(path= "/getText", produces= {
//			MediaType.TEXT_PLAIN_VALUE						// 한글 깨짐
			MediaType.TEXT_PLAIN_VALUE	+ "; charset=utf8"	// 한글 안깨짐
		})
	public String getText() {
		log.debug("getText() invoked.");

		return "안녕하세요";
	} // getText
	
	
	@RequestMapping(path="/getSample"								// XML: 한글 안깨짐
//		produces= {
//			MediaType.APPLICATION_JSON_VALUE						// JSON: 한글 안깨짐
//			MediaType.APPLICATION_JSON_VALUE	+ "; charset=utf8"	// JSON: 한글 안깨짐
//			,
//			MediaType.APPLICATION_XML_VALUE							// XML: 한글 안깨짐
//			MediaType.APPLICATION_XML_VALUE 	+ "; charset=utf8"	// XML: 한글 안깨짐
//		}
	)
	public SampleVO getSample() {
		log.debug("getSample() invoked.");
		
		SampleVO sample = new SampleVO(1, "성", "이름");
		
		return sample;
	} // getSample
	
	
	@GetMapping(path="/getSampleByJSON", produces=MediaType.APPLICATION_JSON_VALUE)	// JSON
	public SampleVO getSampleByJSON() {
		log.debug("getSampleByJSON() invoked.");
		
		return new SampleVO(2, "김", "시습");
	} // getSampleByJSON
	
	
	@GetMapping(path="/getSampleByXML", produces=MediaType.APPLICATION_XML_VALUE)	// XML
	public SampleVO getSampleByXML() {
		log.debug("getSampleByXML() invoked.");
		
		return new SampleVO(3, "홍", "길동");
	} // getSampleByXML
	
	
//	@GetMapping("/getSampleList")													// XML
//	@GetMapping(path="/getSampleList", produces=MediaType.APPLICATION_XML_VALUE)	// XML
	@GetMapping(path="/getSampleList", produces=MediaType.APPLICATION_JSON_VALUE)	// JSON
	public List<SampleVO> getSampleList() {
		log.debug("getSampleList() invoked.");
		
		List<SampleVO> sampleList = new ArrayList<>();
		
		for(int i=1; i<=10; i++) {
			SampleVO sample = new SampleVO(i, "firstName", "lastName");
			
			sampleList.add(sample);
		} // for
		
		sampleList.forEach(log::info);
		
		return sampleList;
	} // getSampleList
	
	
//	@GetMapping("/getSampleMap")													// XML
//	@GetMapping(path="/getSampleMap", produces=MediaType.APPLICATION_XML_VALUE)		// XML
	@GetMapping(path="/getSampleMap", produces=MediaType.APPLICATION_JSON_VALUE)	// JSON
	public Map<String, SampleVO> getSampleMap() {
		log.debug("getSampleMap() invoked.");
		
		Map<String, SampleVO> sampleMap = new HashMap<>();
		
		for(int i=1; i<=10; i++) {
			SampleVO sample = new SampleVO(i, "firstName", "lastName");
			
			sampleMap.put("Sample"+i, sample);
		} // for
		
		sampleMap.forEach((k, v) -> {
			log.info(String.format("\t+ k: %s, v: %s", k, v));
		}); // forEach
		
		return sampleMap;
	} // getSampleMap
	
	
//	@GetMapping("/getBoardList")													// XML
//	@GetMapping(path="/getBoardList", produces=MediaType.APPLICATION_XML_VALUE)		// XML
	@GetMapping(path="/getBoardList", produces=MediaType.APPLICATION_JSON_VALUE)	// JSON
	public List<BoardVO> getBoardList() {
		log.debug("getBoardList() invoked.");
		
		List<BoardVO> boardList = null;
		
		try {
			Connection conn = dataSource.getConnection();
			
			String sql = "SELECT /*+ INDEX_DESC(tbl_board) */ * FROM tbl_board";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
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
		
		return boardList;
	} // getBoardList

	
	
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
