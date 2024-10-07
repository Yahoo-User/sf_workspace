package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.service.Service;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/View")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		out.println("<html><head><head><body>");
		
		// 각 command(요청유형)별, 서비스 객체의 비지니스 수행 결과 데이터를
		// "Request Scope"에서 얻어서, 응답문서 생성에 사용		
		Object bizResult = req.getAttribute(Service.BIZ_RESULT);
		
		out.println("<p>"+bizResult+"</p>");		
		out.println("</body></html>");
		
		out.flush();
		
	} // service

} // end class
