package com.java.www;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/DoWrite")
public class DoM_write extends HttpServlet {
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DoM_write doAction");
		request.setCharacterEncoding("utf-8");
		String msg ="";
		String url = "";
		String id = request.getParameter("id");
		System.out.println("id: "+id);
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String[] hobbys = request.getParameterValues("hobby");
		String hobby="";
		for(int i=0;i<hobbys.length;i++) {
			if(i==0) hobby = hobbys[i];
			else hobby += ","+hobbys[i];
		}
		
		//db연결
		MemberDto mdto = new MemberDto(id,pw,name,phone,gender,hobby);
		MemberDao mdao = new MemberDao();
		
		int result = mdao.insertMember(mdto);
		
		if(result!=1) {
			msg = "회원정보가 저장되지 않았습니다.";
			url= "m_login.jsp";
		}else {
			msg="회원정보가 저장되었습니다.";
			url="m_main.jsp";
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>회원가입</title>");
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