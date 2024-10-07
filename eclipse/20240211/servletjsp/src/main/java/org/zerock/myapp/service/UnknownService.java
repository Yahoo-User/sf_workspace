package org.zerock.myapp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.exception.BizProcessException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

// 잘못된 요청(command)에 대한 처리를 수행하는 서비스 객체
public class UnknownService implements Service {

	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
		throws BizProcessException {
		log.trace("execute(req, res) invoked.");
				
//		req.getServletContext().setAttribute(Service.BIZ_RESULT, "Bad Request.");		// into App.Scope (Abnormal)
		req.setAttribute(Service.BIZ_RESULT, "Bad Request.");							// into Req.Scope (OK!)
	} // execute

} // end class
