package com.java.www.service;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.www.dao.BoardDao;
import com.java.www.dto.BoardDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BUpdateService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		BoardDao bdao = new BoardDao();
		int bno = 0;
		String btitle="",bcontent="",id="",bfile="",oldfile="";
		int result=0;
		BoardDto bdto = null;
		
		
		String uPath = "c:/upload";
		int size = 10*1024*1024; //10Mb
		
		try {
			MultipartRequest multi = new MultipartRequest(request, uPath, size,"utf-8",new DefaultFileRenamePolicy());
			bno = Integer.parseInt(multi.getParameter("bno"));
			btitle = multi.getParameter("btitle");
			bcontent = multi.getParameter("bcontent");
			oldfile = multi.getParameter("oldfile");
			
			
			Enumeration files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String f = (String)files.nextElement(); 
				bfile = multi.getFilesystemName(f);
			}
			
			if(bfile==null) bfile = oldfile;
			
			
			bdto = new BoardDto(bno, btitle, bcontent, id, bfile);
			
		
			result = bdao.bUpdate(bdto);
			
		
			request.setAttribute("result", result);
			request.setAttribute("bno", bno);
			
			
		} catch (IOException e) {e.printStackTrace();}
		
		

	}

}
