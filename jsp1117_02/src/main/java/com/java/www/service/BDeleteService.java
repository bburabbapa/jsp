package com.java.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.www.dao.BoardDao;

public class BDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		System.out.println("BDeleteService bno : "+bno);
		//dao접근
		BoardDao bdao = new BoardDao();
		int result = bdao.bDelete(bno);
		
		//request 추가
		request.setAttribute("result", result);

	}

}
