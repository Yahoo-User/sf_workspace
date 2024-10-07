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
public class DeleteService implements Service {

	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
		throws BizProcessException {
		log.trace("execute(req, res) invoked.");
		
		try {
			EmpDTO dto = (EmpDTO) req.getAttribute(Service.DTO);
			
			EmpDAO dao = new EmpDAO();
			
			int deletedRows = dao.delete(dto);		// Biz.data
			
//			req.getServletContext().setAttribute(Service.BIZ_RESULT, deletedRows);	// into App.Scope (Abnormal)
			req.setAttribute(Service.BIZ_RESULT, deletedRows);						// into Req.Scope (OK!)
		} catch(SQLException e) {
			throw new BizProcessException(e);
		} // try-catch
	} // execute

} // end class
