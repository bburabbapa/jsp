package com.java.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/S01")
public class S1101_01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doAction");
		request.setCharacterEncoding("utf-8"); //post한글처리 항상 위에 위치해놓아야함
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		String [] hobbys = request.getParameterValues("hobby");
		String hobby = "";
		for(int i=0;i<hobbys.length;i++) {
			if(i==0) hobby = hobbys[i];//게임
			else hobby += ","+ hobbys[i];//골프,요리....
			System.out.println("hobby: "+Arrays.toString(request.getParameterValues("hobby")));//체크박스만 파리미터밸류즈값으로 출력
		}
		response.setContentType("text/html; charset=utf-8");//html로 출력
		PrintWriter writer = response.getWriter();//웹에 페이지출력
		
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>form데이터</title>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h3>아이디: "+id);
		writer.println("</h3>");
		writer.println("<br>");
		writer.println("<h3>패스워드: "+pw);
		writer.println("</h3>");
		writer.println("<br>");
		writer.println("<h3>이름: "+name);
		writer.println("</h3>");
		writer.println("<br>");
		writer.println("<h3>성별: "+gender);
		writer.println("</h3>");
		writer.println("<br>");
		writer.println("<h3>주소: "+address);
		writer.println("</h3>");
		writer.println("<br>");
		writer.println("<h3>취미: "+hobby);
		writer.println("</h3>");
		writer.println("<br>");
		writer.println("<h3>취미배열: "+Arrays.toString(request.getParameterValues("hobby"))); //html의 hooby임
		writer.println("</h3>");
		writer.println("<br>");
		writer.println("</body>");
		writer.println("</html>");
		
		writer.close();
	}
		
		
		

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		doAction(request, response);
	}

}
