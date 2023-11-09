<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
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
		  request.setCharacterEncoding("utf-8");
		  String uid = request.getParameter("id");
	      String upw = request.getParameter("pw");
		
	      //커넥션풀에서 Connection을 가져오기
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
		  try{
			  Context context = new InitialContext();//클래스를 사용하여 JNDI 컨텍스트를 초기화합니다
			  DataSource ds =(DataSource)context.lookup("java:comp/env/jdbc/Oracle18"); //데이터소스로 형변환
			  conn = ds.getConnection();
			  
			  //이하 동일함
			  String query = "select * from member where id=? and pw=?"; //?= placeholder(자리표시자)
	          pstmt = conn.prepareStatement(query);
	          pstmt.setString(1, uid);
	          pstmt.setString(2, upw);
	          rs = pstmt.executeQuery();
	          while(rs.next()){  
	        	  uid = rs.getString("id");
		          upw = rs.getString("pw");
		      if(rs.next()){
		    	  session.setAttribute("session_id", uid);
		    	  session.setAttribute("session_nicname", "뿌라빠파");
	    	   %>
		    	  <script>
		    	  	alert("로그인 되었습니다!")
		    	  	location.href="jsp_main.jsp";
		    	  </script>  
		      <% }else{ %> 
		    	  <script>
		    	  	alert("아이디 또는 패스워드가 일치하지 않습니다.!")
		    	  	location.href="jsp_login.jsp";
		    	  </script>  
				
		      <% } %> 
		      
		      <%
	          }
	          }catch(Exception e){
	        	  e.printStackTrace();
	          }finally{
	        	  try{
	        		  if(rs!=null) rs.close();
	        		  if(pstmt!=null) pstmt.close();
	        		  if(conn!=null) conn.close();
	        	  }catch(Exception e2){
	        		 e2.printStackTrace();
	        	  }
	          }
	          %>
	
	</body>
	</html>