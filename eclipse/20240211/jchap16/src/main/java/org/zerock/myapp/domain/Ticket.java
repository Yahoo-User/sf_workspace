package org.zerock.myapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor

@Data
public class Ticket {
	private Integer tno;
	private String	owner;
	private String 	grade;
	
	

} // end class
