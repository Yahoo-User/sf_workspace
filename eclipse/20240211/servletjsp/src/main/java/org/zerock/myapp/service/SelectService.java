package org.zerock.myapp.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.EmpVO;
import org.zerock.myapp.exception.BizProcessException;
import org.zerock.myapp.persistence.EmpDAO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

public class SelectService implements Service {

	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
		throws BizProcessException {
		log.trace("execute(req, res) invoked.");
		
		try {
			// 비지니스 수행결과 데이터 발생
			List<EmpVO> list = new EmpDAO().selectAll();		// Biz.data
			
			// 공유데이터 영역에 비지니스 데이터를 속성 바인딩 시킴
//			req.getServletContext().setAttribute(Service.BIZ_RESULT, list);		// into App.Scope (Abnormal)
			req.setAttribute(Service.BIZ_RESULT, list);							// into Req.Scope (OK!)
		} catch(SQLException e) {
			throw new BizProcessException(e);
		} // try-catch
	} // execute

} // end class
