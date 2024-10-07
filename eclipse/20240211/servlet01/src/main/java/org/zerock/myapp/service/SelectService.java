package org.zerock.myapp.service;

import java.sql.SQLException;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.persistence.EmployeeDAO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class SelectService implements Command {

	
	@Override
	public Object execute(EmpDTO empDTO)
		throws ServiceException {
		log.debug("execute(empDTO) invoked.");
		
		EmployeeDAO dao = new EmployeeDAO();
		
		try {
			return dao.select( empDTO.getEmpno() );
		} catch(SQLException e) {
			throw new ServiceException(e);
		} // try-catch
	} // execute

} // end class
