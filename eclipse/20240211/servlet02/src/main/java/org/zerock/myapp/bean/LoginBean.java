package org.zerock.myapp.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2

//@Accessors(fluent=false, chain=true)

// (3)
@Getter
@Setter
@NoArgsConstructor				//	(1)
public class LoginBean
	implements Serializable {	// (4)
	private static final long serialVersionUID = 1L;
	
	// (2)
	private String userid;
	private String passwd;
	
	
	
	public String getUserid() {
		log.debug("********* getUserid() invoked. *********");
	
		return userid;
	} // getUserid


} // end class
