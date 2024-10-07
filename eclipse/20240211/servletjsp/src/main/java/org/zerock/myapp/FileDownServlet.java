package org.zerock.myapp;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

@WebServlet("/FileDownload")
public class FileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		req.setCharacterEncoding("utf8");
		
		// Step.1 사용자로부터 다운로드 받을 파일명을 전송파라미터로 획득
		String fileName = req.getParameter("fileName");	// 다운로드 요청 파일명
		log.info("\t+ fileName: {}", fileName);
		
		
		// Step.2 실제 다운로드 처리를 수행
		
		// (1) 다운로드 파일의 절대경로를 생성
		final String uploadPath = "C:/temp/upload/";
		final String downloadPath = uploadPath + fileName;
		log.info("\t+ downloadPath: {}", downloadPath);
				
		// (2) 다운받을 파일의 MIME type 결정
		ServletContext sc = this.getServletContext();
		
		String mimeType = sc.getMimeType(downloadPath);
		if(mimeType == null) {	// Well-known extension 이 아닌경우를 대비
			mimeType = "application/octet-stream";
		} // if
		
		log.info("\t+ mimeType: {}", mimeType);
		
		// (3) (2) 에서 얻어낸 다운로드 파일의 MIME type을 응답문서의 컨텐트 유형으로 지정
		res.setContentType(mimeType);
		
		// (4) 사용자가 지정한 다운로드 파일명에 다국어문자 또는 whitespace등이
		// 포함되어 있는 경우, 다운로드시에도, 파일명이 깨지지 않고 정상적으로
		// 나타나도록 인코딩 처리를 해줘야 됨
		
		// Encoding (부호화, 예: 전문 -> 모르스부호 변환)
		// 우리같은 경우는 파일명 -> ASCII 문자만 포함한 형태로 변환
		// ASCII 문자집합의 공식명칭 -> ISO-8859-1
		String encodedFileName = new String(fileName.getBytes("utf8"), "ISO-8859-1");
		log.info("\t+ encodedFileName: {}", encodedFileName);
		
		
		// (5) 응답문서의 헤더에 특수한 헤더를 추가해줘야 함( 필수 ) *****		
		// 일반적인 HTTP 응답에서 Content-Disposition 헤더는, 
		// 가. 컨텐츠가 브라우저에 inline 되어야 하는 웹페이지 자체이거나 웹페이지의 일부인지,
		// 나. 아니면 attachment로써 다운로드 되거나 로컬에 저장될 용도록 쓰이는 것인지를 알려주는 
		// 헤더입니다.
		res.setHeader("Content-Disposition", "attachment; filename="+encodedFileName);
		
		// (6) 다운로드 경로의 파일의 데이터를 읽어서, 응답메시지의 Body(Payload)에 넣어주자!
		
		// JAVA I/O API 사용이 필요!
		byte[] buf = new byte[100];		// 바가지
		
		@Cleanup
		FileInputStream fis = new FileInputStream(downloadPath);
		
		@Cleanup
		ServletOutputStream sos = res.getOutputStream();	// 응답메시지의 Body에 출력할 바이트기반의 출력스트림
		
		int readBytes;
		while((readBytes = fis.read(buf)) != -1) {	// @until EOF(-1)
			sos.write(buf, 0, readBytes);
		} // while
		
		sos.flush();
	} // service

} // end class










