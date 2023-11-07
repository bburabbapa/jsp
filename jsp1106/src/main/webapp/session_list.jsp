<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>섹션 리스트</title>
	</head>
	<body>
	   <!-- 로그인 하기전 -->
	   <% if(session.getAttribute("session_id") == null){   %>
	   <h1>로그인을 하셔야 글쓰기가 가능합니다.</h1>
	   <ul>
	     <li>회원가입</li>
	     <li><a href="login.jsp">로그인</a></li>
	   </ul>
	   <br>
	   <%}else{ %>
	   <!-- 로그인 후 -->
	   <h1><%=session.getAttribute("session_nicName") %>님 환영합니다.</h1>
	   <ul>
	     <li>회원정보</li>
	     <li><a href="logout.jsp">로그아웃</a></li>
	     <li>관리자게시판</li>
	   </ul>
	   <%} %>
	</body>
</html>