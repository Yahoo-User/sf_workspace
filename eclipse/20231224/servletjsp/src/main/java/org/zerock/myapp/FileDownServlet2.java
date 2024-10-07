package org.zerock.myapp;

import java.io.FileInputStream;
import java.io.IOException;

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

@WebServlet("/FileDown")
public class FileDownServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		String fileName = req.getParameter("fileName");
		log.info("\t+ fileName: {}", fileName);
		
		String uploadPath = "C:/temp/upload/";
		String downloadPath = uploadPath + fileName;
		log.info("\t+ downloadPath: {}", downloadPath);
		
//		===== 응답 데이터 생성 =====
		
		String mimeType = this.getServletContext().getMimeType(downloadPath);
		
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		} // if
		
		log.info("\t+ mimeType: {}", mimeType);
				
		String encodedFileName = new String( fileName.getBytes("utf8"), "8859_1" );
		log.info("\t+ encodedFileName: {}", encodedFileName);

		res.setContentType(mimeType);		
		res.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName);
		
		byte[] buf = new byte[4096];
		
		@Cleanup
		FileInputStream fis = new FileInputStream(downloadPath);
		
		@Cleanup
		ServletOutputStream out = res.getOutputStream();
		
		int readBytes;
		while((readBytes = fis.read(buf, 0, buf.length)) != -1) {
			out.write(buf, 0, readBytes);
		} // while
		
		out.flush();
	} // service

} // end class










