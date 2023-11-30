package com.java.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.www.dao.MemberDao;
import com.java.www.dto.MemberDto;

public class DoLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		MemberDao mdao = new MemberDao();
		MemberDto mdto = mdao.doLogin(id,pw); 
		
		int result = 0;//변수초기화
		if(mdto!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("session_id", mdto.getId());// 세션에 mdto 아이디값을 저장
			session.setAttribute("session_name", mdto.getName());
			result = 1;//성공시 변수에 1 할당 실패면 0 할당
			
		}
		request.setAttribute("result", result);//리퀘스트객체에 결과값을 저장	
			

	}

}
