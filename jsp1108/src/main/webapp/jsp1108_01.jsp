<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.net.ConnectException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>DB연결</title>
	</head>
	<body>
		<%
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String id=null,pw=null,name=null,phone=null,gender=null,hobby=null;
		
		
		try{ 
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ora_user","1111");
			  stmt = conn.createStatement();
			  String query = "select * from member";
			  rs = stmt.executeQuery(query); 
			
			  while(rs.next()){
				 id=rs.getString("id");
				 pw=rs.getString("pw");
				 name=rs.getString("name");
				 phone=rs.getString("phone");
				 gender=rs.getString("gender");
				 hobby=rs.getString("hobby");
				 out.println("아이디: "+id+", 패스워드: "+pw+",이름: "+name+", 전화번호: "+phone+", 성별: "+gender+", 취미: "+hobby+"<br>");
				  
			  }
			
		}catch(Exception e){//예외처리 에러가 나도 데이터베이스에 연결이 되어 창이 뜸 
			e.printStackTrace();
		}finally{//에러가 나든 안나든 무조건 연결
			try{ //데이터 전송 후 닫기
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
			
			
		}
		
		%>
	</body>
	</html>