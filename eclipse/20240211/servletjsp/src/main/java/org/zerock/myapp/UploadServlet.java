package org.zerock.myapp;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

@MultipartConfig(location="C:/temp/upload", maxFileSize=1024 * 1024 * 2)	// 최대파일업로드크기 = 2MB제한

@WebServlet("/Upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");

		req.setCharacterEncoding("utf8");
		
		// ============================================= //
		// Common-Step: 
		// ============================================= //
		// multiple 속성이 있는 <input type="file" name="uploadFile" multiple> 태그로
		// 업로드된 복수 개의 파일을 저장하는 로직
		Collection<Part> parts = req.getParts();
		parts.forEach(log::info);
		
		Iterator<Part> iter = parts.iterator();
		
		while(iter.hasNext()) {
			Part part = iter.next();	// 개별 파트객체를 얻어내고
			
			// 첨부파일을 포함하고 있는 Part만 필터링
			String name = part.getName();
			if("uploadFile".equals(name)) {		// 파일데이터를 가지고 있는 Part이다!
				log.info("\t+ file size: {}", part.getSize());
				
				// 지정된 경로 디렉토리에 저장
				String submittedFileName = part.getSubmittedFileName();
				part.write(submittedFileName);	// 지정된 파일명으로 파일데이터 저장
				
				part.delete();					// 임시파일 삭제
			} else continue;	// 일반 Part 라면 제외시킴
		} // while
		
		
		// ============================================= //
		// 아래의 일련의 코드는, 1개의 Part에 대한 다양한 정보획득 및 파일저장 로직
		// ============================================= //
				
		// ----------------------------------------------
		// Step.1 일반적인 전송파라미터의 값 획득
//		String writer = req.getParameter("writer");
//		log.info("\t+ writer: {}", writer);
		
		
		// ----------------------------------------------
		// Step.2 파일데이터가 저장된 멀티파트의 개별파트마다,
		//		  @MultipartConfig 어노테이션의 location 속성에 지정된 경로에
		//        첨부파일을 저장하자!
		
		// (1) 개별 파트부터 획득
//		Part part1 = req.getPart("uploadFile");
//		log.info("\t+ part1: {}", part1);
		
		// (2) 획득한 특정 파트의 각종 정보 획득 및 출력
//		String name = part1.getName();
//		long size = part1.getSize();
//		String contentType = part1.getContentType();
//		String submittedFileName = part1.getSubmittedFileName();
//		
//		log.info("\t+ 1. name: {}", name);
//		log.info("\t+ 2. size: {}", size);
//		log.info("\t+ 3. contentType: {}", contentType);
//		log.info("\t+ 4. submittedFileName: {}", submittedFileName);
		
//		Collection<String> headerNames = part1.getHeaderNames();
//		headerNames.forEach(log::info);				// using Method Reference
		
//		headerNames.forEach(h -> {					// using Lambda Expression
//			log.info("\t+ 5. header: {}", h);
//			log.info("\t+ 6. {} : {}", h, part1.getHeader(h));
//		});	// .forEach
		
		// (3) 파트에 저장된 파일 데이터를 디스크의 지정(@MultipartConfig)된 폴더에 저장
//		part1.write(submittedFileName);		// location=C:/temp/upload/{submittedFileName}
//		part1.delete(); 					// 디스크에 파일저장시 사용된 임시파일 삭제
		
		
	} // service

} // end class










