package org.zerock.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
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
import lombok.extern.log4j.Log4j;



@Log4j
@NoArgsConstructor


@Controller
public class StandardServletUploadController {
	
	private static final String uploadKey = "__FILES__";
	
	
	
	@PostMapping("/uploadFilesByStandardServlet")
	public String processUploadedFiles(
			@RequestParam("hobby") String hobby,
			@RequestParam("uploadFile1") MultipartFile[] files1, 
			@RequestParam("uploadFile2") MultipartFile[] files2,
			Model model) throws Exception {
		log.debug("processUploadedFiles(hobby, files1, files2, model) invoked.");
		
		log.info("\t+ hobby: " + hobby);
		
		//------------------------------------------------//
		
		Map<String, String> map = new HashMap<>();
		
		//------------------------------------------------//
		
		Objects.requireNonNull(files1);
		log.info("\t+ uploadFile1: " + Arrays.toString(files1));
		
		for(MultipartFile file : files1) {
			
			printMultipartFile(file);
			
			//--1st. method : to maintain a file's original name.
//			File dest = new File(file.getOriginalFilename());
			
			//--2nd. method : to change a file's original name with UUID.
			UUID uuid = UUIDGenerator.generateType4UUID();
			
			map.put(file.getOriginalFilename(), uuid.toString());

			File dest = new File(uuid.toString());
			this.transferToDestination(file, dest);
			
		} // enhanced for
		
		//------------------------------------------------//
		
		Objects.requireNonNull(files2);
		log.info("\t+ uploadFile2: " + Arrays.toString(files2));
		
		for(MultipartFile file : files2) {
			
			printMultipartFile(file);
			
			//--1st. method : to maintain a file's original name.
//			File dest = new File(file.getOriginalFilename());
			
			//--2nd. method : to change a file's original name with UUID.
			UUID uuid = UUIDGenerator.generateType4UUID();
			
			map.put(file.getOriginalFilename(), uuid.toString());

			File dest = new File(uuid.toString());
			this.transferToDestination(file, dest);
			
		} // enhanced for
		
		
		model.addAttribute(uploadKey, map);
		
		return "uploadFiles";
	}// processUploadedFiles
	
	
	private void printMultipartFile(MultipartFile file) 
			throws IOException {
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
