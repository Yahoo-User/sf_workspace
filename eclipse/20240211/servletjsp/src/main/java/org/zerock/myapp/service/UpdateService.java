package org.zerock.myapp.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.exception.BizProcessException;
import org.zerock.myapp.persistence.EmpDAO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class UpdateService implements Service {

	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
		throws BizProcessException {
		log.trace("execute(req, res) invoked.");
		
		try {
			EmpDTO dto = (EmpDTO) req.getAttribute(Service.DTO);
			
			EmpDAO dao = new EmpDAO();
			
			int updatedRows = dao.update(dto);
			
//			req.getServletContext().setAttribute(Service.BIZ_RESULT, updatedRows);	// into App.Scope (Abnormal)
			req.setAttribute(Service.BIZ_RESULT, updatedRows);						// into Req.Scope (OK!)
		} catch(SQLException e) {
			throw new BizProcessException(e);
		} // try-catch
	} // execute

} // end class
