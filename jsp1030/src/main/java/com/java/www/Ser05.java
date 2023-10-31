package com.java.www;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Ser05")
public class Ser05 extends HttpServlet {
	//프로그램 시작 시 딱 1번 실행됨 
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init()메소드는 시작 시 1번 실행됨");
	}
	//프로그램 종료 시 딱 1번 실행됨
	public void destroy() {
		System.out.println("destroy()메소드는 종료 시 1번 실행됨");
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doAction ");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doget url 접속 시 계속 실행");
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dopost post방식으로 들어올 때 계속 실행");
		doAction(request, response);
	}

}
