package com.java.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/S03")
public class S1031_03 extends HttpServlet {
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doAction");
		request.setCharacterEncoding("utf-8");
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		int total = kor+eng+math;
		double avg = total/3.0;
		String str_avg = String.format("%.2f", avg);
		double d_avg = Double.parseDouble(str_avg);
		//oarcle -> round(avg,2);
		//System.out.printf("%.2f",avg);
		System.out.println("합계 : "+(kor+eng+math)); //10010099 -> 299
		String major = request.getParameter("major");
		String address = request.getParameter("address");
		// 배열 : checkbox - getPrameterValues
		String[] subjects = request.getParameterValues("subject");
		String subject="";
		for(int i=0;i<subjects.length;i++) {
			if(i==0) subject = subjects[i];
			else subject += ","+subjects[i];
		}
		
		//,기준으로 배열로 분리
		String[] subjects2 = subject.split(","); 
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("<head><title>form데이터</title></head>");
		writer.println("<body>");
		writer.println("학번 : "+no);
		writer.println("<br>");
		writer.println("이름 : "+name);
		writer.println("<br>");
		writer.println("국어점수 : "+kor);
		writer.println("<br>");
		writer.println("영어점수 : "+eng);
		writer.println("<br>");
		writer.println("수학점수 : "+math);
		writer.println("<br>");
		writer.println("합계 : "+total);
		writer.println("<br>");
		writer.println("평균 : "+str_avg);
		writer.println("<br>");
		writer.println("계열선택 : "+major);
		writer.println("<br>");
		writer.println("좋아하는 과목배열 : "+Arrays.toString(subjects));
		writer.println("<br>");
		writer.println("좋아하는 과목 1개 문자열 : "+subject);
		writer.println("<br>");
		writer.println("주소 : "+address);
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