package com.java.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.www.dao.BoardDao;
import com.java.www.dto.BoardDto;

public class BSelectOneService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardDao bdao = new BoardDao();
		//조회수 1증가
		bdao.bHitUp(bno); //void는 리턴값이 없음
		//1개 게시글 가져오기
		BoardDto bdto = bdao.selectOne(bno);

		request.setAttribute("bdto", bdto);
		

	}

}
