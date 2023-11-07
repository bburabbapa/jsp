<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인체크</title>
	</head>
	<body>
	    <%
	      //id=admin, pw=1111
	      String id = request.getParameter("id");
	      String pw = request.getParameter("pw");
	      
	      if(id.equals("admin") && pw.equals("1111")){
	    	 //섹션부분추가 
	    	 session.setAttribute("session_id", "admin");
	    	 session.setAttribute("session_nicName", "길동스");
	    %>	  
	        <script>
	          alert("로그인이 되었습니다.!");
	          location.href="session_list.jsp";
	        </script>	  
	    <%}else{%>
	    	<script>
	    	  alert("아이디 또는 패스워드가 일치하지 않습니다. 다시 로그인해주세요.");
	    	  location.href="login.jsp";
	    	</script>
	    <%}%>
	
	</body>
</html>
