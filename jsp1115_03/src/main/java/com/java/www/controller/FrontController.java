package com.java.www.controller;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.www.service.BListservice;
import com.java.www.service.DoLoginService;
import com.java.www.service.Service;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doAction");
		request.setCharacterEncoding("utf-8");
	
		String url = null;
		String uri = request.getRequestURI();
		String cPath = request.getContextPath();
		System.out.println("context path : "+cPath);
		String fileName = uri.substring(cPath.length());
		System.out.println("호출파일이름 : "+fileName);
		
		
		if(fileName.equals("/index.do")) {
			System.out.println("메인페이지로 이동");
			response.sendRedirect("index.jsp");
			
		}else if(fileName.equals("/login.do")) {
			System.out.println("로그인페이지로 이동");
			response.sendRedirect("login.jsp");
			
		}else if(fileName.equals("/doLogin.do")){
			System.out.println("로그인여부 페이지 이동");
			Service service = new DoLoginService();
			service.execute(request, response);
			url = "doLogin.jsp";
			
		}else if(fileName.equals("/write.do")) {
			System.out.println("회원가입 페이지 이동");
			response.sendRedirect("mwrite.jsp");
			
		}else if(fileName.equals("/logout.do")) {
			response.sendRedirect("logout.jsp");
			System.out.println("로그아웃페이지 이동");
			
		}else if(fileName.equals("/list.do")) {
			System.out.println("게시판페이지 이동");
			Service service = new BListservice();
			service.execute(request, response);
			url = "list.jsp";
		}
		
		
		
		if(url!=null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		
	
	
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
