package com.java.www.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.www.dao.EventDao;
import com.java.www.dto.EcommentDto;



@WebServlet("/CInsert")
public class CInsert extends HttpServlet {
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doAction");
		request.setCharacterEncoding("utf-8");
		int bno = Integer.parseInt(request.getParameter("bno"));
		HttpSession session = request.getSession();
		String id = "aaa";
		String cpw = request.getParameter("cpw");
		String ccontent = request.getParameter("ccontent");
		System.out.println("doAction cpw : "+cpw);
		System.out.println("doAction ccontent : "+ccontent);
		
		EcommentDto ecdto = null;
		//dao접근 - 저장후 1개 댓글 가져오기
		EventDao edao = new EventDao();
		ecdto = edao.CInsert(bno,id,cpw,ccontent);
		
		System.out.println("controller doAction cno : "+ecdto.getCno());			
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