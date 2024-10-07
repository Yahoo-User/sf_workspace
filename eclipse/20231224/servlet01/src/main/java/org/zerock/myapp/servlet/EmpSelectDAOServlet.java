package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.EmpVO;
import org.zerock.myapp.persistence.EmpDAO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/EmpSelectDAO")
public class EmpSelectDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void service(
			HttpServletRequest req,
			HttpServletResponse res) 
				throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		try {
			
			res.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = res.getWriter();
			
			try(out) {
			
				out.print("<html><body>");
				
				EmpDAO dao = new EmpDAO();
				
				List<EmpVO> list = dao.select();
				
				for(EmpVO employee : list) {
					Integer empno = employee.getEmpno();
					String ename = employee.getEname();
					Double sal = employee.getSal();
					Integer deptno = employee.getDeptno();
					
					out.printf("%s\t%s\t%s\t%s <br>", empno, ename, sal, deptno);
				} // enhanced for
				
				out.print("</body></html>");
				
				//---------- 필수호출 -----------//
				out.flush();
				
			} // try-with-resources
			
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
