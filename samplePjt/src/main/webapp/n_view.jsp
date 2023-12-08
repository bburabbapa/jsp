<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
  <title>게시글 보기</title>
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean" rel="stylesheet">
  <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="css/read.css">
  <script>
    $(function(){
    	$("#deleteBtn").click(function(){
    		if(confirm("게시글을 삭제하시겠습니까?")){
    			location.href="n_delete.do?page=${page}&bno=${bdto.bno}&category=${category}&sword=${sword}";
    		}
    	});
    });
  </script>
  <style>
     .fa{font-family: 'Font Awesome 5 Free';} 
  </style>
</head>

<body>
  <%@include file="top.jsp" %>

  <section>
    <h1>NOTICE</h1>

    <table>
      <tr>
        <th><strong>제목</strong><span class="separator">|</span> ${bdto.btitle }</th>
      </tr>
      <tr>
        <td><strong>날짜</strong><span class="separator">|</span><fmt:formatDate value="${bdto.bdate}" pattern="yyyy-MM-dd"/></td>
      </tr>
      <script>
         $(function(){
        	var bno = "${bdto.bno}";
        	var myLike = "${my_like_count}"; //현재 좋아요 상태
        	var htmlData = "";
        	$("#likeStatus").click(function(){
        		if(myLike==0){
	        		alert("좋아요 추가");
	        		alert($(".likeNo").text()+1);//현재 좋아요 개수 +1알림
	        		var num = Number($(".likeNo").text())+1; //자바스크립트 형변환(number)를 사용하여 현재 좋아요 개수에서 1을 더함
	        		myLike = 1;
	        		//ajax를 통한 서버에 좋아요 상태 업데이트
	        		$.ajax({
	        			url:"MyLikeUpdate",
	        			type:"post",
	        			data:{"bno":bno,"like_status":1},
	        			dataType:"json",
	        			success:function(data){
	        				alert("성공");
	        				alert("총 좋아요 개수 : "+data.all_like_count);
	        				//좋아요 개수를 업데이트하여 화면에 표시
	        				htmlData = '';
	    	        		htmlData += '좋아요 <i class="fa-heart fa-solid"></i><span class="likeNo"> '+num+'</span>';
	    	        		$("#likeStatus").html(htmlData);
	        			},
	        			error:function(){
	        				alert("실패");
	        			}
	        		});
        		}else{
	        		alert("좋아요 취소");
	        		//alert($(".likeNo").text());
	        		var num = Number($(".likeNo").text())-1; //자바스크립트 형변환(number)를 사용하여 현재 좋아요 개수에서 1을 뺌
	        		myLike = 0;
	        		//ajax를 통한 서버에 좋아요 상태 업데이트(좋아요 취소)
	        		$.ajax({
	        			url:"MyLikeUpdate",
	        			type:"post",
	        			data:{"bno":bno,"like_status":0},
	        			dataType:"json",
	        			success:function(data){
	        				alert("성공");
	        				alert("총 좋아요 개수 : "+data.all_like_count);
	        				//좋아요 개수를 없데이트하여 화면에 표시
	        				htmlData = '';
	    	        		htmlData += '좋아요 <i class="fa-heart fa-regular"></i><span class="likeNo"> '+num+'</span>';
	    	        		$("#likeStatus").html(htmlData);
	        			},
	        			error:function(){
	        				alert("실패");
	        			}
	        		});
        		}
        	}); 
         });
      </script>
      <tr>
        <td id="likeStatus">좋아요
        <c:if test="${my_like_count==1}">
        <i class="fa-heart fa-solid"></i><span class="likeNo"> ${all_like_count}</span>
        </c:if>
        <c:if test="${my_like_count!=1}">
        <i class="fa-heart fa-regular"></i><span class="likeNo"> ${all_like_count}</span>
        </c:if>
        </td>
      </tr>
      
      <tr>
        <td class="article">${bdto.bcontent}</td>
      </tr>
      <tr>
        <td class="article">
          <img src="upload/${bdto.bfile}" alt="" width="50%">
        </td>
      </tr>
      <tr>
        <td><strong>다음글</strong> <span class="separator">|</span> 
          <a href="n_view.do?page=${page}&bno=${nextDto.bno}&category=${category}&sword=${sword}">${nextDto.btitle}</a> 
        </td>
      </tr>
      <tr>
        <td><strong>이전글</strong> <span class="separator">|</span> 
          <a href="n_view.do?page=${page}&bno=${preDto.bno}&category=${category}&sword=${sword}">${preDto.btitle}</a> 
        </td>
      </tr>
    </table>

    <a href="n_list.do?page=${page}&category=${category}&sword=${sword}"><div class="list">목록</div></a>
    <div class="list" id="deleteBtn">삭제</div>
    <a href="n_update.do?page=${page}&bno=${bdto.bno}&category=${category}&sword=${sword}"><div class="list">수정</div></a>
    <a href="n_reply.do?page=${page}&bno=${bdto.bno}&category=${category}&sword=${sword}"><div class="list">답글달기</div></a>
  </section>

  <footer>
    <div class="wrapper">
      <div class="footer-left">
        <div class="footer-logo"></div>
        <div class="copyright">© COOKIT ALL RIGHTS RESERVED</div>
      </div>
  
      <div class="footer-center">
        <ul class="footer-nav">
          <li class="first-list">이용약관</li>
          <li class="green">개인정보처리 방침</li>
          <li>법적고지</li>
          <li>사업자정보 확인</li>
        </ul>
  
        <ul class="footer-info">
          <li class="first-list">씨제이제일제당(주)</li>
          <li>대표이사 : 손경식,강신호,신현재</li>
          <li>사업자등록번호 : 104-86-09535</li>
          <li class="first-list">주소 : 서울 중구 동호로 330 CJ제일제당 센터 (우) 04560</li>
          <li>통신판매업신고 중구 제 07767호</li>
          <li class="first-list">개인정보보호책임자 : 조영민</li>
          <li>이메일 : cjon@cj.net</li>
          <li>호스팅제공자 : CJ올리브네트웍스㈜</li>
        </ul>
  
        <div id="check">고객님은 안전거래를 위해 현금등으로 결제시 LG U+ 전자 결제의 매매보호(에스크로) 서비스를 이용하실 수 있습니다. <span class="check">가입 사실 확인</span></div>
      </div>
  
      <div class="footer-right">
        <div id="shortcut">
          <span>CJ그룹계열사 바로가기</span>
          <div class="shortcut"></div>
        </div>
  
        <div class="call">고객행복센터 1668-1920</div>
        <div class="inquery">1:1 문의</div>
      </div>
  
    </div>
  </footer>
</body>
</html>
