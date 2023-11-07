<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인확인</title>
	</head>
	<body>
 		<%
	      String id = request.getParameter("id"); //변수생성
	      String pw = request.getParameter("pw");
	      
	      if(id.equals("admin") && pw.equals("1111")){
	    	  session.setAttribute("session_id", id);
	    	  session.setAttribute("session_nicName", "뿌라빠파");
	   %>
	      <script>
	        alert("로그인 되었습니다!");
	        location.href="../layout/main.jsp";
	      </script>
	   <%}else{ %>
	      <script>
	        alert("아이디 또는 패스워드가 일치하지 않습니다. 다시 로그인해 주세요.");
	        location.href="login.jsp";
	        //history.back();//이전페이지로 이동
	      </script>
	   <%} %>
	
	</body>
	</html>
	