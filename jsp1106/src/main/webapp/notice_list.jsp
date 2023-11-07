<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="css/notice_list.css">
</head>
<body>
<!-- top 시작 -->
<%@include file="top.jsp" %>
<!-- top 끝 -->
  <section>
    <h1>NOTICE</h1>
    <div class="wrapper">
      <form action="/search" name="search" method="post">
        <select name="category" id="category">
          <option value="0">전체</option>
          <option value="title">제목</option>
          <option value="content">내용</option>
        </select>

        <div class="title">
          <input type="text" size="16">
        </div>
  
        <button type="submit"><i class="fas fa-search"></i></button>
      </form>
    </div>

    <table>
      <colgroup>
        <col width="18%">
        <col width="60%">
        <col width="18%">
      </colgroup>
      <tr>
        <th>No.</th>
        <th>제목</th>
        <th>작성일</th>
      </tr>
      <!-- 게시글반복 시작 -->
      <% for(int i=1;i<=10;i++){ //1부터 10까지의 값을 반복하는 for 루프가 시작됩니다.
         Date today = new Date();//현재 날짜와 시간을 나타내는 Date 객체를 생성합니다.
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//"yyyy-MM-dd HH:mm:ss" 형식으로 날짜를 포맷합니다.
         String today_str = sdf.format(today);//: SimpleDateFormat을 사용하여 현재 날짜와 시간을 포맷하고 문자열로 변환합니다.
      %>
      
      <tr>
        <td><%=i %></td>   
        <td class="table-title">
           <a href="notice_read.jsp">카카오플러스 친구 오픈</a>
        </td>
        <td><%=today_str %></td>
      </tr>
      <%} %>
      <!-- 게시글반복 끝 -->
      
    </table>

    <ul class="page-num">
      <li class="first"></li>
      <li class="prev"></li>
      <!-- 페이지 반복 시작 -->
      <% for(int i=1;i<=10;i++){ %>
      <li class="num">
        <div><%=i %></div>
      </li>
      <%} %>
      <!-- 페이지 반복 끝 -->
      
      <li class="next"></li>
      <li class="last"></li>
    </ul>

    <a href="notice_write.jsp"><div class="write">쓰기</div></a>
  </section>
<!-- footer 시작 -->
<%@include file="footer.jsp" %>
<!-- footer 끝 -->
</body>
</html>
