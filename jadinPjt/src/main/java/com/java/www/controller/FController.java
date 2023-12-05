package com.java.www.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.www.service.EventOneService;
import com.java.www.service.EventService;
import com.java.www.service.Service;


@WebServlet("*.do")
public class FController extends HttpServlet {
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doAction");
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String cpath = request.getContextPath();
		String fileName = uri.substring(cpath.length());
		System.out.println("FController fileName : "+fileName);
		String url = null;
		Service service = null;
		
		switch (fileName) {
		case "/layout/main.do":
			response.sendRedirect("main.jsp");
			break;
		case "/event/event.do":
			service = new EventService();//게시글 전체 가져오기
			service.execute(request, response);
			url = "event.jsp";
			break;
		case "/event/event_view.do":
			service = new EventOneService();//게시글 한개 가져오기
			service.execute(request, response);
			url = "event_view.jsp";
			break;

		default:
			System.out.println("페이지 없음!");
			break;
		}
		
		if(url!=null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		
		
	}
	
	
	//---
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		doAction(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		doAction(request, response);
	}

}
