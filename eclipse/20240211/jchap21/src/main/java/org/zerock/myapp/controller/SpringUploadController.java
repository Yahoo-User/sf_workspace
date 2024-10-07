package org.zerock.myapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.myapp.util.UUIDGenerator;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor


@Controller
public class SpringUploadController {
	
	private static final String uploadKey = "__FILES__";
	private static final String uploadDir = "C:/Temp/file_upload/";
	
	
	@PostMapping("/uploadFilesBySpring")
	public String processUploadedFiles(
			@RequestParam("hobby") String hobby,
			
//			@RequestParam("uploadFile1") ArrayList<MultipartFile> files1,
//			@RequestParam("uploadFile2") ArrayList<MultipartFile> files2
			
//			@RequestParam("uploadFile1") List<MultipartFile> files1,
//			@RequestParam("uploadFile2") List<MultipartFile> files2
			
			@RequestParam("uploadFiles") List<MultipartFile> files,
			
			Model model
		) {
		log.debug("processUploadedFiles(files) invoked.");
		
		//------------------------------------------------//
		
		assert hobby 	!= null;
//		assert files1 	!= null;
//		assert files2 	!= null;
		
		assert files	!= null;
		
		
		//------------------------------------------------//
		
		log.info("\t+ hobby: " + hobby);
		

//		log.info("\t+ type: " + files1.getClass().getName());
//		files1.forEach(log::info);
//
//		log.info("\t+ type: " + files2.getClass().getName());
//		files2.forEach(log::info);
		
		
		log.info("\t+ type: " + files.getClass().getName());
		files.forEach(log::info);
		
		log.info("");
		
		//------------------------------------------------//
		
//		processUploadFiles(files1, model);
//		processUploadFiles(files2, model);
		
		processUploadFiles(files, model);
		
		
		return "uploadFiles";
	} // processUploadedFiles
	
	
	private void processUploadFiles(List<MultipartFile> files, Model model) {
		log.debug("processUploadFiles(files) invoked.");
		
		//------------------------------------------------//
		
		Date now = new Date();
		
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String yyyyMMdd = formatter.format(now);
		
		//------------------------------------------------//
		
		Map<String, String> map = new HashMap<>();
		
		files.forEach(f -> {
			
			log.info("\t+ 1. request parameter: " 	+ f.getName());
			log.info("\t+ 2. contentType: " 		+ f.getContentType());
			log.info("\t+ 3. filename: " 			+ f.getOriginalFilename());
			log.info("\t+ 4. filesize: " 			+ f.getSize());
			log.info("\t=================================================");
			
			if(f.getSize() > 0) {	// if uploaded file size > 0, then ...
				
				try {
					
					File lastUploadDir = new File(uploadDir + "/" + yyyyMMdd);
					
					if(!lastUploadDir.exists()) {
						lastUploadDir.mkdirs();
					} // if
					
					//-----------------------------------//

					//--1st. method : to maintain a file's original name.
//					String filename = f.getOriginalFilename();
					
					//--2nd. method : to change a file's original name with UUID.
					String filename = UUIDGenerator.generateType4UUID().toString();
					
					map.put(f.getOriginalFilename(), filename);
					
					
					FileOutputStream fos = new FileOutputStream(lastUploadDir + "/" + filename);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					
					try(fos; bos;) {
						byte[] fileData = f.getBytes();
						bos.write(fileData);
						
						bos.flush();
					} // try-with-resources
					
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					model.addAttribute(uploadKey, map);
				} // try-catch-finally
				
			} // if
				
		}); // forEach
		
	} // processUploadFiles

} // end class
