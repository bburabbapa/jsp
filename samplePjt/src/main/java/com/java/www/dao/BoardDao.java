package com.java.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.java.www.dto.BoardDto;

public class BoardDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	BoardDto bdto;
	ArrayList<BoardDto> list = new ArrayList<BoardDto>();
	int bno,bgroup,bstep,bindent,bhit;
	String btitle,bcontent,id,bfile,query;
	Timestamp bdate;
	int result,listCount;
	
	//커넥션풀에서 Connection객체 가져오기
	public Connection getConnection() {
		Connection connection = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/Oracle18");
			connection = ds.getConnection();
		} catch (Exception e) { e.printStackTrace();}
		return connection;
	}//getConnection

	//전체게시글,검색 가져오기 - select
	public ArrayList<BoardDto> n_listSelect(String category, String sword, int startRow, int endRow) {
		try {
			conn = getConnection();
			if(category==null) {
				query = "select * from (select row_number() over (order by bgroup desc,bstep asc) rnum, a.* from board a) where rnum between ? and ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			}else if(category.equals("all")) {
				query = "select * from (select row_number() over (order by bgroup desc,bstep asc) rnum, a.* from board a where btitle like '%'||?||'%' or bcontent like '%'||?||'%') where rnum between ? and ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, sword);
				pstmt.setString(2, sword);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
			}else if(category.equals("btitle")) {
				query = "select * from (select row_number() over (order by bgroup desc,bstep asc) rnum, a.* from board a where btitle like '%'||?||'%') where rnum between ? and ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, sword);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}else {
				query = "select * from (select row_number() over (order by bgroup desc,bstep asc) rnum, a.* from board a where bcontent like '%'||?||'%') where rnum between ? and ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, sword);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bno = rs.getInt("bno");
				btitle = rs.getString("btitle");
				bcontent = rs.getString("bcontent");
				bdate = rs.getTimestamp("bdate");
				id = rs.getString("id");
				bgroup = rs.getInt("bgroup");
				bstep = rs.getInt("bstep");
				bindent = rs.getInt("bindent");
				bhit = rs.getInt("bhit");
				bfile = rs.getString("bfile");
				list.add(new BoardDto(bno,btitle,bcontent,bdate,id,bgroup,bstep,bindent,bhit,bfile));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) { e2.printStackTrace();}
		}//
		return list;
	}//n_listSelect

	//게시글 전체개수
	public int nListCount(String category, String sword) {
		try {
			conn = getConnection();
			if(category==null) { //
				query = "select count(*) listCount from board";
				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					listCount = rs.getInt("listCount");
					System.out.println("dao nListCount : "+listCount);
				}
			}else if(category.equals("all")) {
				query = "select count(*) listCount from board where btitle like '%'||?||'%' or bcontent like '%'||?||'%' ";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, sword);
				pstmt.setString(2, sword);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					listCount = rs.getInt("listCount");
					System.out.println("dao nListCount : "+listCount);
				}
			}else if(category.equals("btitle")) {
				query = "select count(*) listCount from board where btitle like '%'||?||'%'";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, sword);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					listCount = rs.getInt("listCount");
					System.out.println("dao nListCount : "+listCount);
				}
			}else {
				query = "select count(*) listCount from board where bcontent like '%'||?||'%'";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, sword);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					listCount = rs.getInt("listCount");
					System.out.println("dao nListCount : "+listCount);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) { e2.printStackTrace();}
		}//
		return listCount;
	}//nListCount

	//게시글 1개 가져오기
	public BoardDto selectOne(int bno2) {
		try {
			conn = getConnection();
			query = "select * from board where bno=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bno2);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// 
				bno = rs.getInt("bno");
				btitle = rs.getString("btitle");
				bcontent = rs.getString("bcontent");
				bdate = rs.getTimestamp("bdate");
				id = rs.getString("id");
				bgroup = rs.getInt("bgroup");
				bstep = rs.getInt("bstep");
				bindent = rs.getInt("bindent");
				bhit = rs.getInt("bhit");
				bfile = rs.getString("bfile");
				bdto = new BoardDto(bno, btitle, bcontent, bdate, id, bgroup, bstep, bindent, bhit, bfile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) { e2.printStackTrace();}
		}//
		return bdto;
	}//selectOne
	
	//이전글 가져오기
	public BoardDto preSelectOne(int bno2) {
		try {
			conn = getConnection();
			query = "select * from ("
					+ "select row_number() over (order by bgroup desc, bstep asc) rnum , a.* from board a )"
					+ "where rnum ="
					+ "(select rnum from ("
					+ "select row_number() over (order by bgroup desc, bstep asc) rnum , a.* from board a )"
					+ "where bno=?)+1";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bno2);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// 
				bno = rs.getInt("bno");
				btitle = rs.getString("btitle");
				bcontent = rs.getString("bcontent");
				bdate = rs.getTimestamp("bdate");
				id = rs.getString("id");
				bgroup = rs.getInt("bgroup");
				bstep = rs.getInt("bstep");
				bindent = rs.getInt("bindent");
				bhit = rs.getInt("bhit");
				bfile = rs.getString("bfile");
				bdto = new BoardDto(bno, btitle, bcontent, bdate, id, bgroup, bstep, bindent, bhit, bfile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) { e2.printStackTrace();}
		}//
		return bdto;
	}//preSelectOne
	
	//다음글 가져오기
	public BoardDto nextSelectOne(int bno2) {
		try {
			conn = getConnection();
			query = "select * from ("
					+ "select row_number() over (order by bgroup desc, bstep asc) rnum , a.* from board a )"
					+ "where rnum ="
					+ "(select rnum from ("
					+ "select row_number() over (order by bgroup desc, bstep asc) rnum , a.* from board a )"
					+ "where bno=?)-1";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bno2);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// 
				bno = rs.getInt("bno");
				btitle = rs.getString("btitle");
				bcontent = rs.getString("bcontent");
				bdate = rs.getTimestamp("bdate");
				id = rs.getString("id");
				bgroup = rs.getInt("bgroup");
				bstep = rs.getInt("bstep");
				bindent = rs.getInt("bindent");
				bhit = rs.getInt("bhit");
				bfile = rs.getString("bfile");
				bdto = new BoardDto(bno, btitle, bcontent, bdate, id, bgroup, bstep, bindent, bhit, bfile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) { e2.printStackTrace();}
		}//
		return bdto;
	}//nextSelectOne
	

	//게시글 1개 저장
	public int insert(BoardDto bdto2) {
		try {
			conn = getConnection();
			query = "insert into board values(board_seq.nextval,?,?,sysdate,?,board_seq.currval,0,0,1,?)";
			pstmt = conn.prepareStatement(query);
			//1,2,
			pstmt.setString(1, bdto2.getBtitle());
			pstmt.setString(2, bdto2.getBcontent());
			pstmt.setString(3, bdto2.getId());
			pstmt.setString(4, bdto2.getBfile());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) { e2.printStackTrace();}
		}//
		return result;
	}//insert

	//답글달기를 위한 step증가
	public void stepUp(int bgroup2, int bstep2) {
		try {
			conn = getConnection();
			query = "update board set bstep=bstep+1 where bgroup=? and bstep>?";
			pstmt = conn.prepareStatement(query);
			//1,2,
			pstmt.setInt(1, bgroup2);
			pstmt.setInt(2, bstep2);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) { e2.printStackTrace();}
		}//
	}//stepUp

	//답글달기 저장
	public int replyInsert(BoardDto bdto2) {
		try {
			conn = getConnection();
			query = "insert into board values(board_seq.nextval,?,?,sysdate,?,?,?,?,1,?)";
			pstmt = conn.prepareStatement(query);
			//1,2
			pstmt.setString(1, bdto2.getBtitle());
			pstmt.setString(2, bdto2.getBcontent());
			pstmt.setString(3, bdto2.getId());
			pstmt.setInt(4, bdto2.getBgroup()); //부모 그대로 사용
			pstmt.setInt(5, bdto2.getBstep()+1); //부모보다 1증가
			pstmt.setInt(6, bdto2.getBindent()+1); //부모보다 1증가
			pstmt.setString(7, bdto2.getBfile());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) { e2.printStackTrace();}
		}//
		return result;
	}//replyInsert

	//게시글 수정 - update
	public int update(BoardDto bdto2) {
		try {
			conn = getConnection();
			query = "update board set btitle=?,bcontent=?,bfile=? where bno=?";
			pstmt = conn.prepareStatement(query);
			//1,2
			pstmt.setString(1, bdto2.getBtitle());
			pstmt.setString(2, bdto2.getBcontent());
			pstmt.setString(3, bdto2.getBfile());
			pstmt.setInt(4, bdto2.getBno()); //
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) { e2.printStackTrace();}
		}//
		return result;
	}//update

	//게시글 삭제
	public int delete(int bno2) {
		try {
			conn = getConnection();
			query = "delete board where bno=?";
			pstmt = conn.prepareStatement(query);
			//1,2
			pstmt.setInt(1, bno2);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) { e2.printStackTrace();}
		}//
		return result;
	}

	//조회수 1증가
	public void bhitUp(int bno2) {
		try {
			conn = getConnection();//데이터베이스연결을 가져옴
			query = "update board set bhit = bhit+1 where bno=?";//게시물의 조회수를 1 증가시키는 sql쿼리문
			pstmt = conn.prepareStatement(query);
			//1,2
			pstmt.setInt(1, bno2);//쿼리문의 매개변수에 게시물 번호를 설정
			pstmt.executeUpdate();//쿼리문을 실행하여 게시물의 조회수를 1 증가시킴
		} catch (Exception e) {
			e.printStackTrace();//예외 발생 시 스택 트레이스 출력
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) { e2.printStackTrace();}
		}
		
	}

	//좋아요부분 - 내가 좋아요 누른 상태 - select
	public int myLikeSelect(String id2, int bno2) {
		int my_like_count = 0;
		try {
			conn = getConnection();//데이터베이스연결을 가져옴
			query = "select count(*) my_like_count from b_likes where bno=? and id=? and like_status=1";
			pstmt = conn.prepareStatement(query);//pstmt 초기화
			pstmt.setInt(1, bno2);//쿼리 매개변수 설정
			pstmt.setString(2, id2);
			rs = pstmt.executeQuery();//쿼리 실행 결과
			while(rs.next()) {//결과 집합에서 한 행씩 반복하면서 변수에 할당
				my_like_count = rs.getInt("my_like_count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {//null값이 아닌 경우 닫기
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) { e2.printStackTrace();}
		}//
		return my_like_count;//메서드 종료 시 my_like_count 값을 반환
	}//

	//좋아요부분 - 전체 좋아요 개수 - select
	public int allLikeSelect(int bno2) {
		int all_like_count = 0;
		try {
			conn = getConnection();
			query = "select count(*) all_like_count from b_likes where bno=? and like_status=1";//좋아요 상태가 1인 개수 
			pstmt = conn.prepareStatement(query);//stmt는 준비된 문을 나타내며, 쿼리를 실행하기 위해 conn.prepareStatement(query)를 사용하여 초기화됩니다
			//1,2
			pstmt.setInt(1, bno2);
			rs = pstmt.executeQuery();//쿼리의 매개변수를 설정
			while(rs.next()) {//rs 변수는 쿼리 실행 결과를 나타내며, executeQuery 메서드를 사용하여 실행합니다.
				all_like_count = rs.getInt("all_like_count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) { e2.printStackTrace();}
		}//
		
		return all_like_count;//메서드 종료 시 all_like_count 값을 반환
	}//

	//좋아요 상태 변경 - update
	public int myLikeUpdate(String id2, int bno2, int like_status) {
		int all_like_count = 0;
		try {
			conn = getConnection();
			
			//사용자가 좋아요를 누른 적이 있는지 확인하기 위해 "b_likes" 테이블에서 bno와 id를 이용하여 레코드 수를 조회합니다.
			query = "select count(*) from b_likes where bno=? and id=?";
			pstmt = conn.prepareStatement(query);
			//1,2
			pstmt.setInt(1, bno2);
			pstmt.setString(2, id2);
			rs = pstmt.executeQuery();
			int count = 0;
			if(rs.next()) {
				count = rs.getInt("count");//조회된 레코드 수를 변수 count에 저장합니다.
			}
			
			if(count==0) {//만약 count가 0이라면 사용자가 해당 게시물에 좋아요를 누른 적이 없는 경우이므로, "b_likes" 테이블에 새로운 레코드를 추가합니다.
				//내가 좋아요 누른 적이 없는 경우 - insert
				query = "insert into b_likes values (b_likes_seq.nextval,?,?,?)";
				pstmt = conn.prepareStatement(query);
				//1,2
				pstmt.setInt(1, bno2);
				pstmt.setString(2, id2);
				pstmt.setInt(3, like_status);
				pstmt.executeUpdate();
			}else {//만약 count가 0이 아니라면 사용자가 이미 좋아요를 누른 적이 있는 경우이므로, 해당 레코드의 like_status 값을 업데이트합니다.
				//내가 좋아요 누른 적이 있는 경우 - update
				query = "update b_likes set like_status=? where bno=? and id=?";
				pstmt = conn.prepareStatement(query);
				//1,2
				pstmt.setInt(1, like_status);
				pstmt.setInt(2, bno2);
				pstmt.setString(3, id2);
				pstmt.executeUpdate();
			}
			//좋아요 전체개수 가져오기
			all_like_count = allLikeSelect(bno2);//좋아요 전체 개수를 업데이트하기 위해 allLikeSelect 메서드를 호출하여 all_like_count를 가져옵니다.
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {//rs, pstmt, conn이 null이 아닌 경우에만 닫습니다.
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) { e2.printStackTrace();}
		}//
		return all_like_count;//메서드 종료 시 all_like_count 값을 반환
	}

	

	
	
	
}