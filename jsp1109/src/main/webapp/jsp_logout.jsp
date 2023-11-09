<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	<body>
	
		<% session.invalidate(); //세션모두삭제
		   session.removeAttribute("session_id");	//세션한개삭제
		%>
		<script>
			alert("로그아웃되었습니다.");
			location.href="jsp_main.jsp";
		</script>
	
	
	</body>
	</html>