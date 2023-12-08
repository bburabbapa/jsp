package com.java.www.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.java.www.dao.EventDao;
import com.java.www.dto.EcommentDto;



@WebServlet("/CInsert")
public class CInsert extends HttpServlet {
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doAction");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		int bno = Integer.parseInt(request.getParameter("bno"));
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
		
		//하단 댓글 1개 ajax로 보내기(json형태로 보냄)  xml형태 -- html-자바jsp-파이선
		JSONObject json = new JSONObject();
		json.put("cno",ecdto.getCno());//key value
		json.put("id",ecdto.getId());//key value
		json.put("ccontent",ecdto.getCcontent());//key value
		json.put("cdate",""+ecdto.getCdate());//key value
		
	
		response.setContentType("application/x-json; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.print(json);
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