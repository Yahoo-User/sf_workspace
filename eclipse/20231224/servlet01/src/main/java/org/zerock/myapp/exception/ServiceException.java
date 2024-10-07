package org.zerock.myapp.exception;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class ServiceException extends Exception {	
	private static final long serialVersionUID = -1446656243709121605L;
		
	
	public ServiceException(String message) {
		super(message);
	} // constructor
	
	public ServiceException(Exception e) {
		super(e);
	} // constructor
	
} // end class
