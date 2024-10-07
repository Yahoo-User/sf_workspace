package org.zerock.myapp.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.extern.log4j.Log4j;



@Data
@Log4j
public class SampleDTOList {
	
	private List<SampleDTO> list;
	
	
	public SampleDTOList() {
		log.info("Default Constructor invoked.");
		
		list = new ArrayList<>();
	} // default constructor
	
	

} // end class
