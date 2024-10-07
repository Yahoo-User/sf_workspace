package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class InitParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String dirPath;
	private String userid;
    
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.debug("init(config) invoked.");
		
		super.init(config);
		
		this.dirPath = this.getInitParameter("dirPath");
		this.userid = this.getInitParameter("userid");
		
		log.info("\t+ dirPath: " + dirPath);
		log.info("\t+ userid: " + userid);
	} // doPost
	
	
	@PostConstruct
	public void postConstruct() {
		log.debug("postConstruct() invoked.");
		
		/**********************************/
		/* 이 위치에서는 초기화 파라미터를 얻을 수 없음 -> why? ServletConfig 객체가
		 * 아직 이 싯점에서는 생성되지 못하기 때문
		 */
		/**********************************/
//		this.dirPath = this.getInitParameter("dirPath");
//		this.userid = this.getInitParameter("userid");
//		
//		log.info("\t+ dirPath: " + dirPath);
//		log.info("\t+ userid: " + userid);
	} // postConstruct

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.debug("doGet(req, res) invoked.");
		
		doPost( req, res );
	} // doGet

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.debug("doPost(req, res) invoked.");
		
//		String dirPath = this.getInitParameter("dirPath");
//		String userid = this.getInitParameter("userid");
		
		//---------------------------------------------//
		
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		out.print("<html><body>");
		out.print("디렉터리경로: " + dirPath +"<br>");
		out.print("아이디 값: " + userid +"<br>");
		out.print("</body></html>");
		
		out.flush();
		out.close();
	}

} // end class
