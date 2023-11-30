package com.java.www.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.www.dao.BoardDao;
import com.java.www.dto.BoardDto;

public class BListService implements Service {

	//dao접근
	BoardDao bdao = new BoardDao();//인스턴스변수
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		//하단넘버링
		//현재페이지
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
			System.out.println("page: "+page);
		}
		System.out.println("현재페이지: "+page);
		String bcategory = request.getParameter("bcategory");
		String bsearch = request.getParameter("bsearch");
		
		
		//하단넘버링 메소드 호출
		Map<String, Object> map = numbering(page,bcategory,bsearch);
		int listCount = (int)map.get("listCount");
		int maxPage = (int)map.get("maxPage");
		int startPage = (int)map.get("startPage");
		int endPage = (int)map.get("endPage");
		int startRow = (int)map.get("startRow");
		int endRow = (int)map.get("endRow");
		
		//전체게시글가져오기
		ArrayList<BoardDto> list = bdao.bList(bcategory,bsearch,startRow,endRow);
		System.out.println("현재페이지: "+page);
		//확인
		System.out.println("BListService list : "+list.get(0).getBno());
		
		//request추가
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		request.setAttribute("listCount", listCount);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("bcategory", bcategory);
		request.setAttribute("bsearch", bsearch);

	}//excute
	//메소드 리턴 타입  메소드명(매개변수) {}
	
	

	public Map<String, Object> numbering(int page, String bcategory2, String bsearch2){
		Map<String, Object> map = new HashMap();
		String bcategory = null;
		String bsearch = null;
		
		int rowPage = 10; //한페이지당 10개의 게시글
		int bottomPage = 10; //하단넘버링개수
		
		//1.전체개수
		int listCount = bdao.listCount(bcategory,bsearch);//가로있고 소문자면 메소드
			
		//2.최대페이지 41/10 = 4.1 ---5.0 --- 5
		int maxPage = (int)Math.ceil((double)listCount/rowPage);//반올림 후 int로 형변환
		
		//3.스타트페이지 5-1/10*10+1=1
		int startPage = (int)((page-1)/bottomPage)*bottomPage+1;
		
		//4.엔드페이지 1+10-1=10
		int endPage = startPage+bottomPage-1;
		if (endPage>maxPage) endPage = maxPage;
		
		//5.스타트로우페이지 (3-1)*10+1 =21
		int startRow = (page-1)*rowPage+1;
		
		//6.엔드로우페이지 21+10-1=30
		int endRow = startRow+rowPage-1;
		
		map.put("listCount", listCount);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		
		return map;
		
	}
		
		
	
	
	
	
	
	
	
	
	

}
