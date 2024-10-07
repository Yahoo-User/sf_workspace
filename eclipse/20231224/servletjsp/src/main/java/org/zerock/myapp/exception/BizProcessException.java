package org.zerock.myapp.exception;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class BizProcessException extends Exception {
	private static final long serialVersionUID = 1L;

	
	public BizProcessException(String msg) {
		super(msg);
	} // constructor1
	
	public BizProcessException(Exception e) {
		super(e);
	} // constructor2
	
} // end class
