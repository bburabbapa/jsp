package com.java.www.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.java.www.dao.BoardDao;
import com.java.www.dto.BoardDto;
public class BSearchService implements Service {
	//dao접근
	BoardDao bdao = new BoardDao();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String bcategory = request.getParameter("bcategory");
		String bsearch = request.getParameter("bsearch");
		
		//---------하단넘버링------------
		
		//1. 현재페이지
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
			System.out.println("request.getParameter page : "+page);
		}
		System.out.println("현재페이지 : "+page);
		
		// --> numbering 메소드 호출
		Map<String, Object> map = numbering(page,bcategory,bsearch);
		int listCount = (int) map.get("listCount");
		int maxPage = (int) map.get("maxPage");
		int startPage = (int) map.get("startPage");
		int endPage = (int) map.get("endPage");
		int startRow = (int) map.get("startRow");
		int endRow = (int) map.get("endRow");
		
		
		//게시글 검색 - select
		ArrayList<BoardDto> list = bdao.bSearch(bcategory,bsearch,startRow,endRow); 
		
		//request 추가
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		request.setAttribute("listCount", listCount);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		//검색부분
		request.setAttribute("bcategory", bcategory);
		request.setAttribute("bsearch", bsearch);
	}//execute
	
	public Map<String , Object> numbering(int page, String bcategory, String bsearch){
		Map<String, Object> map = new HashMap();
		//----
		int rowPage = 10; //한페이지당 10개의 게시글 표시
		int bottomPage = 10; //하단넘버링 개수
		
		//1. 전체개수,검색게시글수개수
		int listCount = bdao.listCount(bcategory,bsearch);
		//2. 최대 하단넘버링 페이지
		int maxPage = (int)Math.ceil((double)(listCount/rowPage));
		//3. startPage - 하단넘버링 시작번호
		int startPage = (int)((page-1)/bottomPage) * bottomPage + 1;
		//4. endPage - 하단넘버링 끝번호
		int endPage = startPage+bottomPage-1;
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		
		//5. startrow - oracle에서 가져오는 시작번호
		int startRow = (page-1)*rowPage+1;
		//6. endrow - oracle에서 가져오는 끝번호
		int endRow = startRow+rowPage-1;
		
		//map listCount,maxPage,startPage,endPage,startRow,endRow
		map.put("listCount", listCount);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		//----
		
		
		return map;
	}//numbering
}//class