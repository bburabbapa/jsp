<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>메인페이지</title>
	</head>
	<body>
	<h1>메인페이지</h1>
		<jsp:forward page="sub.jsp"/> 
	<h2>인클루드페이지</h2>
	<jsp:include page="sub.jsp" flush="true">
	</body>
</html>