package org.zerock.myapp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.exception.BizProcessException;


public interface Service {
	
	public static final String BIZ_RESULT = "__RESULT__";
	public static final String DTO = "__DTO__";
	
	
	public abstract void execute(HttpServletRequest req, HttpServletResponse res)
		throws BizProcessException;

} // end class
