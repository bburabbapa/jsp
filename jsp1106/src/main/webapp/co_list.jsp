<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>쿠키리스트</title>
		<style>
		  * {margin:0; padding:0;}
		  h1,th,td{text-align: center;}
		  table,th,td{border:1px solid black; border-collapse: collapse;
		    font-size:16px;
		  }
		  form,table{width:800px; margin:30px auto;}
		  th,td{width:200px; height:40px; }
		  button{display:block; width:150px; height:50px; margin:0 auto;}
		  .pop{width:400px; height:424px; 
		  background:url("images/popup.jpg") no-repeat;
		  background-size: 75%;
		  position: fixed; left:50%;
		  display: none;
		  }
		  .on{display:block;}
		</style>
		
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script>
		   $(function(){
			   $("#fbtn").click(function(){
				  //alert("test"); 
				  alert($("#co_name").val());
			      co_frm.submit(); //form전송
			   });
		   });   
		</script>
	</head>
	<body>
	   <h1>쿠키리스트</h1>
	   <table>
	     <tr>
	       <th>번호</th>
	       <th>쿠키이름</th>
	       <th>쿠키값</th>
	       <th>쿠키유효시간</th>
	     </tr>
	     <% 
	       Cookie[] cks = request.getCookies(); 
	       if(cks!=null){
	    	   for(int i=0;i<cks.length;i++){
	    		 if(cks[i].getName().equals("open_popup")){
	    		%>
	    		  
	    		  <script>
	    		        alert("ok");
		    		   $(function(){
		    		       $(".pop").addClass("on");
		    		   }) 
	    		  </script>
	    		  
	    		  
	    		 <%} %> 
	     <tr>
	       <td><%=i+1 %></td>
	       <td><%=cks[i].getName() %></td>
	       <td><%=cks[i].getValue() %></td>
	       <td><%=cks[i].getMaxAge() %></td>
	     </tr>
	          <%}%>
	     <%}%>
	   </table>
	   <button onclick="javascript:location.href='co_write.jsp'">쿠키생성</button>
	   <br>
	   <hr>
	   
	   <h1>쿠키 삭제</h1>
	   <form name="co_frm" method="post" action="co_doDelete.jsp">
	     <label>쿠키이름</label>
	     <input type="text" name="co_name" id="co_name">
	     <br>
	     
	     <input type="button" id="fbtn" value="쿠키삭제">
	   </form>
	   
	   <div class="pop"></div>
	   
	   
	   
	   
	
	</body>
</html>
