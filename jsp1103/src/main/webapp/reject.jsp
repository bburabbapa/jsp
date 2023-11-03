<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>금지</title>
		<style>h1{color:red;}</style>
	</head>
	<body>
		<h1>담배,술 판매금지</h1>
		<h2>현재나이: <%=request.getParameter("age") %></h2>
		<h2>이름: <%=request.getParameter("name") %></h2>
		<a href="j1103_03.jsp">페이지 이동</a>
	</body>
</html>