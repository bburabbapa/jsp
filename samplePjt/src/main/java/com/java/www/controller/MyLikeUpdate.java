package com.java.www.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.java.www.dao.BoardDao;
import com.java.www.service.IdCheckService;

//마이라이크업데이트 웹서블릿 생성 
@WebServlet("/MyLikeUpdate")
public class MyLikeUpdate extends HttpServlet {
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doAction");
		request.setCharacterEncoding("utf-8");
		//id bno like_status 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("session_id");//세션에서 사용자 아이디를 가져옴
		int bno = Integer.parseInt(request.getParameter("bno"));//클라이언트에서 전송된 게시물 번호를 파라미터에서가져옴
		int like_status = Integer.parseInt(request.getParameter("like_status"));//클라이언트에서 전송된 좋아요 상태를 파라미터에서 가져옴
		System.out.println("MyLikeUpdate bno : "+bno);
		System.out.println("MyLikeUpdate like_status : "+like_status);
		
		//service접근
		//MyLikeUpdateService MyLikeUpdatekService = new MyLikeUpdateService();
		
		//dao 접근 - 좋아요 상태를 수정
		BoardDao bdao = new BoardDao();
		int all_like_count = bdao.myLikeUpdate(id,bno,like_status);//dao를 통해 좋아요 상태 업데이트하고 전체 좋아요 개수를 가져옴
		System.out.println("controller all_like_count : "+all_like_count);
		
		//ajax전송
		JSONObject json = new JSONObject();
		json.put("all_like_count", all_like_count);  //json 객체 생성 후 전체 좋아요 개수를 key - value 형태로 닫음
		
		response.setContentType("application/x-json; charset=utf-8");//응답의 content type을 설정
		PrintWriter writer = response.getWriter();
		writer.print(json);//json 객체를 응답으로 전송
		writer.close();
		
		
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
