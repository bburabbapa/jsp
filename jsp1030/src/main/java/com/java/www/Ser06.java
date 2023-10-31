package com.java.www;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Ser06")
public class Ser06 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doAction");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>서블릿페이지</title>");
		writer.println("<style>div{background-color: yellow; width: 600px; height:600px; font-size:20px;text-align:center;border:1px solid black}</style>");
		writer.println("<script>alert('입력한 이름: "+request.getParameter("name")+"');</script>");
		writer.println("<script>console.log("+request.getParameter("name")+");</script>");		
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<div>");
		writer.println("<h2>전달받은 name</h2>");
		writer.println("<h2>"+request.getParameter("name")+"</h2>");
		writer.println("</div>");
		writer.println("</body>");
		writer.println("</html>");
		writer.close();
	}
		
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doget");
		doAction(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println();
		doAction(request, response);
	}

}
