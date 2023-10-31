package com.java.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/S02")
public class S1031_02 extends HttpServlet {
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doAction");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		int total = kor+eng+math;
		double avg = total/3.0;
		String str_avg = String.format("%.2f", avg);
		System.out.println("subject : "+ Arrays.toString(request.getParameterValues("subject")));
		String[] subjects = request.getParameterValues("subject");
		String subject = "";
		for(int i=0;i<subjects.length;i++) {
			if(i==0) subject = subjects[i];     
			else subject += ","+ subjects[i];   
		}   
		
		request.setCharacterEncoding("utf-8"); //post한글처리
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		//배열을 hobbys 변수에 입력
		
		writer.println("<html>");
		writer.println("<head><title>form데이터</title></head>");
		writer.println("<body>");
		writer.println("no : "+request.getParameter("no"));
		writer.println("<br>");
		writer.println("kor : "+kor);
		writer.println("<br>");
		writer.println("eng : "+eng);
		writer.println("<br>");
		writer.println("math : "+math);
		writer.println("<br>");
		writer.println("total : "+total);
		writer.println("<br>");
		writer.println("str_avg : "+str_avg);
		writer.println("<br>");
		writer.println("major : "+request.getParameter("major"));
		writer.println("<br>");
		writer.println("checkbox subject배열 : "+Arrays.toString(request.getParameterValues("subject")));
		writer.println("<br>");
		writer.println("checkbox hobby 1개 문자열 : "+subject);
		writer.println("<br>");
		writer.println("select address : "+request.getParameter("address"));
		writer.println("<br>");
		writer.println("</body>");
		writer.println("</html>");
		
		writer.close();
	}//
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		doAction(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		doAction(request, response);
	}


}//class