<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>쿠키저장</title>
	</head>
	<body>
	<%
	   String cname = request.getParameter("co_name");
	   String cvalue = request.getParameter("co_value");
	   Cookie c1 = new Cookie(cname,cvalue); //cname과 cvalue변수로 c1이라는 새로운 쿠키생성
	   c1.setMaxAge(60*10); // 쿠키의 최대 유효기간을 10분(60초 * 10분)으로 설정합니다
	   response.addCookie(c1);//생성한 쿠키를 응답에 추가합니다.
	   
	   response.sendRedirect("co_list.jsp");//쿠키가 설정된 후에 사용자를 다른 페이지로 이동시킵니다.
	%>
	
	</body>
</html>
