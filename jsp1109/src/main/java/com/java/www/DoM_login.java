package com.java.www;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/doLogin")
public class DoM_login extends HttpServlet {
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DoM_login doAction");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String uid = request.getParameter("id");
		String upw = request.getParameter("pw");
		String msg = "";
		String url = "";
		
		System.out.println("id : "+uid);
		System.out.println("pw : "+upw);
		
		//db연결
		//MemberDto uid,upw 입력하려면?
		MemberDao mdao = new MemberDao();
		MemberDto mdto = mdao.loginCheck(uid,upw);
		if(mdto==null) {
			msg = "아이디 또는 패스워드가 일치하지 않습니다. 다시 로그인해주세요.";
			url = "m_login.jsp";
		}else {
			msg = "로그인 되었습니다.";
			url = "m_main.jsp";
			session.setAttribute("session_id",  uid);
			session.setAttribute("session_nicname",  "뿌라빠파");
			
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>로그인체크</title>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<script>");
		writer.println("alert('"+msg+"');");
		writer.println("location.href='"+url+"';");
		writer.println("</script>");
		writer.println("</body>");
		writer.println("</html>");
		
	
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		doAction(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		doAction(request, response);
	}
}