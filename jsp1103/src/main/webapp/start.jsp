<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>메인페이지</title>
	</head>
	<body>
		<jsp:forward page="sub2.jsp"/>
			<jsp:param name="id" value="aaa"/>
			<jsp:param name="pw" value="1111"/>
		</jsp:forward>
		
	</body>
</html>