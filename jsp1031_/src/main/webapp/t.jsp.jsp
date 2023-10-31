<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="EUC-KR">
		<title>JSP HTML태그</title>
			<style>
			
			
			
			
			</style>
		
		
		
	</head>
	<body>
	<h1>JSP 페이지입니다.</h1>
	<%
		int a =1;
		int b= 100;
		int sum = 0;
		for( a=1;a<=b;a++){
			sum += a;
		}
		out. println(sum);
	
	%>
	
	</body>
</html>