package com.java.www.controller;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.java.www.service.BListService;
import com.java.www.service.DoLoginService;
import com.java.www.service.Service;
@WebServlet("*.do")
public class FController extends HttpServlet {
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doAction 메소드호출");
		request.setCharacterEncoding("utf-8");
		//모든페이지는 FController 들어옴.
		//요청페이지 알아내기
		String url = null;
		String uri = request.getRequestURI();
		String cPath = request.getContextPath();
		String fileName = uri.substring(cPath.length());
		System.out.println("호출파일이름 : "+fileName);
		
		if(fileName.equals("/index.do")) {
			System.out.println("index.jsp페이지를 열어줍니다.");
			url = "index.jsp";
		}else if(fileName.equals("/login.do")) {
			System.out.println("login.jsp페이지를 열어줍니다.");
			response.sendRedirect("login.jsp");
		}else if(fileName.equals("/mwrite.do")) {
			System.out.println("mwrite.jsp페이지를 열어줍니다.");
			response.sendRedirect("mwrite.jsp");
		}else if(fileName.equals("/logout.do")) {
			System.out.println("logout.jsp페이지를 열어줍니다.");
			response.sendRedirect("logout.jsp");
		}else if(fileName.equals("/list.do")) {
			System.out.println("list.jsp페이지를 열어줍니다.");
			//db에 접근
			Service service = new BListService();
			service.execute(request, response);
			url = "list.jsp";
		}else if(fileName.equals("/doLogin.do")) {
			System.out.println("id,pw를 가지고 로그인체크를 합니다.");
			//dao호출 - 부모의 참조변수로 자손의 객체를 다루는 것:다형성
			Service service = new DoLoginService();
			service.execute(request, response);
			url="doLogin.jsp";
		}
		
		if(url != null) {
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