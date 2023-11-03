<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>표</title>
		<style>
		  table,th,td{border:1px solid black; border-collapse: collapse;
		  font-size:20px; text-align: center;}
		  table{width:500px; margin:50px auto; }
		  th,td{width:200px; height:40px; }
		  .back{background-color: yellow}
		  
		</style>
	</head>
	<body>
	    <table>
	       <tr>
	         <th>단</th>
	         <th>*</th>
	         <th>숫자</th>
	         <th>=</th>
	         <th>곱셈</th>
	       </tr>
	       <%
	       for(int i=2;i<=9;i++){
	    	   for(int j=1;j<=9;j++){
	       		if(j%2==0) countinue; 
	       		 
	    	%>	   
	       <tr>
	         <td><%= i %></td>
	         <td>*</td>
	         <% if(i%3==0)  
	         <td class="back"></td>
	         else %>
	         <td><%= j %></td>
	        
	         <td>=</td>
	         <td><%= (i*j) %></td>
	       </tr>
	    	<%
	    	}
	    }
	    	%>
	    </table>
	
	</body>