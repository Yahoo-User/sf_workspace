package org.zerock.myapp.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor

@RequestMapping("/fileupload/*")
@Controller
public class FileUploadController {
	
	
	
	@GetMapping("/page")
	public void page() {
		log.debug("page() invoked.");
		
	} // page
	
	
	@PostMapping("/doit")
	public void doit(
			@NonNull ArrayList<MultipartFile> files
		) {
		log.debug("doit() invoked.");
		
		String uploadDir = "C:\\Temp\\file_upload\\";
		
		files.forEach(f -> {
				log.info("\t+ contentType: " + f.getContentType());
				log.info("\t+ filename: " + f.getOriginalFilename());
				log.info("\t+ filesize: " + f.getSize());
				log.info("\t=================================================");
				
				if(f.getSize() > 0) {
					try {
						
						byte[] fileData = f.getBytes();
						
						FileOutputStream fos = new FileOutputStream(uploadDir+f.getOriginalFilename());
						BufferedOutputStream bos = new BufferedOutputStream(fos);
						
						bos.write(fileData);
						
						bos.flush();
						bos.close();
						
						fos.close();
						
					} catch (IOException e) {
						e.printStackTrace();
					} // try-catch
				} // of
		}); // forEach
		
	} // doit
	

} // end class
