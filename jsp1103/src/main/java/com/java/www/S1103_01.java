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
public class S1103_01 extends HttpServlet {

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doAction");
		for(int i=2;i<=9;i++) {
			System.out.println("<br>["+i+"단]</br>");
			for(int j=1;j<=9;j++) {
				System.out.println(i+"*"+j+"="+(i*j)+"<br>");
			}
			
		}
		
		  System.out.println("<h2>1에서 100까지 합</h2>");
		   int sum = 0;
		   for(int i=1;i<=100;i++){
			   sum += i;
			   System.out.println ("더할 숫자 : "+i+" ");
		   }
		   System.out.println("합계 : "+sum+"<br>");
		   
		   response.setContentType("text/html; charset=utf-8");
		   PrintWriter writer = response.getWriter();
		   writer.println("<html>");
			writer.println("<head>");
			writer.println("<title>구구단표</title>");
			writer.println("<style>");
			writer.println("table,th,td{border:1px solid black; border-collapse: collapse;}");
			writer.println("</style>");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<table>");
			writer.println("<tr><th>단</th><th>*</th><th>숫자</th><th>=</th><th>곱셈</th></tr>");
			writer.println("<tr>");
			
			
			for(int i=2;i<=9;i++) {
				for(int j=1;j<=9;j++) {
					writer.println("<th>"+i+"</th>");
					writer.println("<th>*</th>");
					writer.println("<th>"+j+"</th>");
					writer.println("<th>=</th>");
					writer.println("<th>"+(i*j)+"</th>");
					writer.println("</tr>");
				}
				
			}
	
			writer.println("</table>");
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
