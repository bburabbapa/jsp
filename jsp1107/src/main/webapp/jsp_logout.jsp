<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그아웃</title>
	</head>
	<body>
	  <%
	     session.invalidate(); //모든 섹션 삭제
	  %>
	  <script>
	         alert("로그아웃 되었습니다!");
	         location.href="jsp1107_01.jsp";
	  </script>
	
	</body>
</html>
