package org.zerock.myapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

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
public class AJaxUploadController {
	
	private static final String uploadKey = "__FILES__";
	private static final String uploadDir = "C:/Temp/file_upload/";
	

	
	//====================================================================================//
	// By using Spring multi-part facilities.
	//====================================================================================//
	
	@PostMapping("/uploadFilesByAjax1")
	public String processUploadFilesByAjax1(@RequestParam("uploadFiles") List<MultipartFile> files, Model model) {
		log.debug("processUploadFilesByAjax1(files, model) invoked.");
		
		//------------------------------------------------//
		
		assert files != null;
		
		//------------------------------------------------//
		
//		log.info(Arrays.toString(files));	// if array

		log.info("\t+ type: " + files.getClass().getName());
		files.forEach(log::info);			// if list
		
		//------------------------------------------------//
		
		processUploadFiles(files, model);
		
		return "uploadFiles";
	} // processUploadFilesByAjax1
	
	
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
	
	
	
	//====================================================================================//
	// By using multi-part facilities in the standard servlet 3.0 and above.
	//====================================================================================//
	
	@PostMapping("/uploadFilesByAjax2")
	public String processUploadFilesByAjax2(
			@RequestParam("uploadFiles") MultipartFile[] files, 
			Model model) throws Exception {
		log.debug("processUploadFilesByAjax2(files, model) invoked.");
		
		//------------------------------------------------//
		
		assert files != null;
		
		log.info("\t+ files: " + Arrays.toString(files));
		
		//------------------------------------------------//
		
		Map<String, String> map = new HashMap<>();
		
		//------------------------------------------------//
		
		for(MultipartFile f : files) {
			
			printMultipartFile(f);
			
			//--1st. method : to maintain a file's original name.
//			File dest = new File(file.getOriginalFilename());
			
			//--2nd. method : to change a file's original name with UUID.
			UUID uuid = UUIDGenerator.generateType4UUID();
			
			map.put(f.getOriginalFilename(), uuid.toString());

			File dest = new File(uuid.toString());
			this.transferToDestination(f, dest);
			
		} // enhanced for
		
		
		return "uploadFiles";
	} // processUploadFilesByAjax2
	
	
	private void printMultipartFile(MultipartFile file) throws IOException {
		log.debug("\t+ printMultipartFile(file) invoked.");

		log.info("\t\t>> file: "+ file);
		log.info("\t\t>> Request Parameter Name: " + file.getName());
		
		log.info("\t\t\t>>> 1. contentType: " + file.getContentType());
		log.info("\t\t\t>>> 2. originalFilename: " + file.getOriginalFilename());
		log.info("\t\t\t>>> 3. size: " + file.getSize() + " bytes");
		log.info("\t\t\t>>> 4. length: " + file.getBytes().length );
		log.info("\t\t\t>>> 5. isEmpty: " + file.isEmpty());
	} // printMultipartFile
	
	
	private void transferToDestination(MultipartFile file, File dest) 
			throws NullPointerException, IllegalStateException, IOException {
		log.debug("\t+ transferToDestination(file, dest) invoked.");
		
		Objects.requireNonNull(file);
		Objects.requireNonNull(dest);
		
		file.transferTo(dest);
		
		log.info("");
	} // transferToDestination

} // end class
