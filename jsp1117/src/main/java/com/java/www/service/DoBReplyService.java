package com.java.www.service;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.java.www.dao.BoardDao;
import com.java.www.dto.BoardDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
public class DoBReplyService implements Service {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//dao 접근
		BoardDao bdao = new BoardDao();
		//변수선언
		String btitle="",id="",bcontent="",bfile="";
		int result=0;
		BoardDto bdto = null;
		int bgroup = 0,bstep=0,bindent=0;
		
		//form 파일 가져오기
		String upath = "c:/upload";
		int size = 10*1024*1024;
		try {
			MultipartRequest multi = new MultipartRequest(request, upath, size, "utf-8", new DefaultFileRenamePolicy());
			btitle = multi.getParameter("btitle");
			bcontent = multi.getParameter("bcontent");
			id = multi.getParameter("id");
			bgroup = Integer.parseInt(multi.getParameter("bgroup"));
			System.out.println("DoBReplyService bgroup: "+bgroup);
			bstep = Integer.parseInt(multi.getParameter("bstep"));
			bindent = Integer.parseInt(multi.getParameter("bindent"));
			//파일이름 가져오기
			Enumeration files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String f = (String)files.nextElement();
				bfile = multi.getFilesystemName(f);//파일이름 가져옴
			}
			
			bdto = new BoardDto(btitle, bcontent, id, bgroup, bstep, bindent, bfile);
			//bstep 1증가
			bdao.bstepUp(bgroup,bstep);
			
			result = bdao.bReply(bdto);
			System.out.println("DoBReplyService result: "+result);
		} catch (IOException e) {e.printStackTrace();}
		
		
	}
}