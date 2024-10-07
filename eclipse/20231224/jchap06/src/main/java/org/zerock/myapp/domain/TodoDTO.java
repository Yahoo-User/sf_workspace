package org.zerock.myapp.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;



@Data
public class TodoDTO {
	
	private String title;

//	@DateTimeFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(iso=ISO.DATE)
	private Date dueDate;
	
	

} // end class
