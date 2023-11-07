<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>쿠키삭제</title>
	</head>
	<body>
	  <%
	     String cname = request.getParameter("co_name");
	     Cookie[] cks = request.getCookies(); //쿠키 전체 가져옴.
	     int check=0;
	     if(cks !=null){
	    	 for(int i=0;i<cks.length;i++){
		    	 if(cks[i].getName().equals(cname)){//
		    		 cks[i].setMaxAge(0); 
		    		 response.addCookie(cks[i]);
		    		 check=1;
		    	 }//일치하는 쿠키가 있다면 setMaxAge(0)을 호출하여 해당 쿠키의 최대 유효기간을 0으로 설정하고, r
	    	 }//response.addCookie(cks[i])를 사용하여 응답에 해당 쿠키를 추가합니다. 
	     }//check 변수를 사용하여 일치하는 쿠키가 발견되었는지 여부를 추적합니다
	    if(check==0){
	  %>
	   	<script>alert("입력하신 쿠키를 찾을수가 없습니다.");
	   	        location.href="co_list.jsp";
	   	</script>
	  <%}else{%>
	    <script>alert("쿠키를 삭제처리 했습니다.");
	   	        location.href="co_list.jsp";
	   	</script>
	  <%} %>
	  
	</body>
</html>