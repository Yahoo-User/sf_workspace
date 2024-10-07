package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet(
	description = "Sevlet for Sever-Sent Event", 
	urlPatterns = { "/SSE" }
)
public class SSEServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private int count = 1;

		
	protected void service(
			HttpServletRequest req, HttpServletResponse res) 
					throws ServletException, IOException {
		log.debug("service(req, res) invoked.");

		// (**주의**) EventSource's response has a charset ("iso-8859-1") that is not UTF-8.
		//           Aborting the connection.
//		res.setContentType("text/event-stream");					// XX
		
		res.setContentType("text/event-stream; charset=UTF-8");		// OK
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		
		res.setHeader("Cache-Control", "no-cache");
		res.setHeader("Connection", "keep-alive");
		
		PrintWriter out = res.getWriter();

		//---------------------------------------------------------------------//
		// 웹브라우저로 보낼 메시지 구성
		//---------------------------------------------------------------------//
		
		// OK : 메시지ID, 끝에 2개의 개행문자 필요.
		out.write("id: "+ (int) Math.floor(100000 * Math.random())+"\n\n");
		
		// OK : (**주의**) 매번 세션아이디는 변경됨
//		out.write("id: "+ req.getSession().getId()+"\n\n");

		
		// OK : 메시지ID, 끝에 2개의 개행문자 필요.
//		out.write("id: "+ count++ +"\n\n");
		

		// 끝에 2개의 개행문자로 다음 행과의 구분 필요
		out.write("event: custom\n\n");			// OK : 메시지 유형(type)	
		
		// OK : 메시지, 마지막 행이 아닌 행은, 1개의 개행문자로 구분해야 함.
		out.write("data: Yoseph<br>\n");		
		
		// OK : 메시지, 마지막 행은 2개의 개행문자로 구분해야 함.
		Date now = new Date();
		out.write("data: "+now+"\n\n");
		
		// 끝에 1개의 개행문자로 다음 행과의 구분 필요
		out.write("retry: 5000\n");				// OK : Next polling interval (ms)
		
		//---------------------------------------------------------------------//
		
		
		out.flush();
		out.close();
	} // service

} // end class
