<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>useBean</title>
	</head>
	<body>
	   <jsp:useBean id="score1" class="com.java.www.StuScore" scope="page" />
	   <jsp:setProperty name="score1" property="stuNum" value="1"/>
	   <jsp:setProperty name="score1" property="name" value="홍길동"/>
	   <jsp:setProperty name="score1" property="kor" value="100"/>
	   <jsp:setProperty name="score1" property="eng" value="99"/>
	   <jsp:setProperty name="score1" property="math" value="98"/>
	   <h1>
	      <jsp:getProperty name="score1" property="stuNum" />
	   </h1>
	   <h1>
	      <jsp:getProperty name="score1" property="name" />
	   </h1>
	
	</body>
</html>