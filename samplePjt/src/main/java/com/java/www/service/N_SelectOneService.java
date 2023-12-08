package com.java.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.www.dao.BoardDao;
import com.java.www.dto.BoardDto;

public class N_SelectOneService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//id
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("session_id");//세션에서 사용자 아이디를 가져옴 
		//String id ="aaa";
		//dao접근
		int page = Integer.parseInt(request.getParameter("page"));//클라이언트에서 전송된 페이지 번호를 파라미터에서 가져옴
		int bno = Integer.parseInt(request.getParameter("bno"));//클라이언트에서 전송된 게시물 번호를 파라미터에서 가져옴
		String category = request.getParameter("category");//클라이언트에서 전송된 카테고리 정보를 파라미터에서 가져옴
		String sword = request.getParameter("sword");//클라이언트에서 전송된 검색어 정보를 파라미터에서 가져옴
		//객체선언
		BoardDao bdao = new BoardDao();
		BoardDto bdto = bdao.selectOne(bno);//게시물에 해당하는 게시물 정보를 데이터베이스에서 가져옴
		
		//----- view일때
		//파일이름 추출 이전글, 다음글을 구분하기 위해
		String uri = request.getRequestURI();
		String cPath = request.getContextPath();
		String fileName = uri.substring(cPath.length());
		BoardDto preDto = null;
		BoardDto nextDto = null;
		if(fileName.equals("/n_view.do")) {
			//bhit 1증가
			bdao.bhitUp(bno); //update board set bhit = bhit+1 where bno=? 게시물의 조회수를 1 증가시킴
			preDto = bdao.preSelectOne(bno);  //+1 이전글 정보
			nextDto = bdao.nextSelectOne(bno); //-1 다음글 정보
		}
	
		//좋아요 : 내가 좋아요 누른상태, 전체좋아요 개수 - id,bno
		int my_like_count = bdao.myLikeSelect(id,bno);//사용자가 현재 게시물에 대해 누른 좋아요 개수를 가져옴
		int all_like_count = bdao.allLikeSelect(bno); //현재 게시물에 대한 전체 좋아요 개수를 가져옴
		
		
		//request추가
		request.setAttribute("bdto", bdto);//게시물정보
		request.setAttribute("preDto", preDto);//이전글정보
		request.setAttribute("nextDto", nextDto);//다음글정보
		request.setAttribute("page", page);//현재페이지번호
		request.setAttribute("category", category);//카테고리정보
		request.setAttribute("sword", sword);//검색어정보
		request.setAttribute("my_like_count", my_like_count); //현재 게시물에 누른 좋아요 개수
		request.setAttribute("all_like_count", all_like_count);// 현재 게시물에 대한 전체 좋아요 개수

	}

}