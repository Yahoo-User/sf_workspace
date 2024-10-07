package org.zerock.myapp.service;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.exception.ServiceException;


public interface Command {
	
	
	public abstract Object execute(EmpDTO empDTO)
		throws ServiceException;
	
} // end interface
