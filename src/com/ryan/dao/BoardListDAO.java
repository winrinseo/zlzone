package com.ryan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.ryan.dto.BoardDTO;
import com.ryan.dto.MemberDTO;
import com.ryan.dto.viewDTO;

public class BoardListDAO {
	
	// 게시판 부르기
	// 전체 갯수 select count(*) from Board
		public static int getCount() {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			int cnt = 0 ; // 갯수 반환
			
			
			try {
				Class.forName("com.mysql.jdbc.Driver");

				String url = "jdbc:mysql://localhost/zlzone?serverTimezone=Asia/Seoul";
				conn = DriverManager.getConnection(url, "root", "root");
				
				
				String sql = "select count(*) from board";

				stmt = conn.prepareStatement(sql);
				
				rs = stmt.executeQuery();
				if (rs.next()) {
					cnt = rs.getInt(1);  // 첫번째 값 가져오기				
					
				}
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패");
			} catch (SQLException e) {
				System.out.println("에러 " + e);
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
					if (stmt != null && !stmt.isClosed()) {
						stmt.close();
					}
					if (rs != null && !rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return cnt;
		}
		
		// 페이징 select * from member limit 0,10
		
		public static ArrayList<BoardDTO> selectinfo(int start , int exit) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			// 한번에 담기위해 MemberDTO 쓴다
			// 클래스명도 타입취급한다
			ArrayList<BoardDTO> list = new ArrayList<>();
			
			
			
			try {
				Class.forName("com.mysql.jdbc.Driver");

				String url = "jdbc:mysql://localhost/zlzone?serverTimezone=Asia/Seoul";
				conn = DriverManager.getConnection(url, "root", "root");
				
				
				String sql = " select * from board order by num desc limit ?,? ";

				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, start);
				stmt.setInt(2, exit);
				
				rs = stmt.executeQuery();

				while (rs.next()) {
					int num = rs.getInt("num");
					String name = rs.getString("name");
					String title = rs.getString("title");
					String board = rs.getString("board");
					String time = rs.getString("time");
					int level = rs.getInt("level");
				
					
					// 값을 다 받기위해 while문 안에 씀
					BoardDTO dto = new BoardDTO();
					
					
					// MemberDTO 파일 안에 있는 setId(String id) 메소드에 매개변수인 id 변수에
					// SELECT * FROM member 으로 나온 한 데이터중 ( id,pw,name,email,phone)
					// id 데이터를 삽입한다   

					// ex) id = "test"
					// public void setId(String id) {
					// this.id = id;    →   this.id = test
					// }
					
					// 그러면 MemberDTO 파일 안에 있는 Private String id 의 값은 test 이다
					// 외부에서 dto.getId(); 하면 test값이 출력된다
					// 나머지도 같은 방식
					
					dto.setNum(num);
					dto.setName(name);
					dto.setTitle(title);
					dto.setBoard(board);
					dto.setTime(time);
					dto.setLevel(level);
					
					
					
					//  그후 list에 배열 dto값 삽입
					//  ex)   id: test  /  pw: 1234 / name: 홍길동  / email: aa@aa.com / phone 010-0000-000
					//  [1] : setId(id) , setPw(pw) , setName(name) , setEmail(email) , setPhone(phone) 
					//  위 ex에 있는 값이 들어감
					
					//  while 반복문이므로 위 코드랑 같이 작동되면서 계속 쌓아짐
					//  [2] , [3] , [4]  ..... 
					list.add(dto);
					
					
				}
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패");
			} catch (SQLException e) {
				System.out.println("에러 " + e);
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
					if (stmt != null && !stmt.isClosed()) {
						stmt.close();
					}
					if (rs != null && !rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// 쌓아진 list 값이  selectinfo()에 들어가며 외부로 보냄
			// 또한 배열 이므로
			// public static void selectinfo() 를
			// public static ArrayList<MemberDTO> selectinfo() 로 바꾼다
			return list;
		}
		
		
		public static viewDTO boardView(int num) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			// 한번에 담기위해 MemberDTO 쓴다
			// 클래스명도 타입취급한다
			ArrayList<BoardDTO> list = new ArrayList<>();
			viewDTO dto = new viewDTO();
			
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				String url = "jdbc:mysql://localhost/zlzone?serverTimezone=Asia/Seoul";
				conn = DriverManager.getConnection(url, "root", "root");
				
				
				String sql = "select * from board where num = ?";
				
				
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, num);
				
				
				rs = stmt.executeQuery();
				
				//System.out.println(rs);

				while (rs.next()) {
					int num1 = rs.getInt("num");
					//String id = rs.getString("id");
					String name = rs.getString("name");
					String title = rs.getString("title");
					String board = rs.getString("board");
					String time = rs.getString("time");
				
					System.out.println(name);
					System.out.println(title);
					System.out.println(board);
					
					// 값을 다 받기위해 while문 안에 씀
					
					
					
					// MemberDTO 파일 안에 있는 setId(String id) 메소드에 매개변수인 id 변수에
					// SELECT * FROM member 으로 나온 한 데이터중 ( id,pw,name,email,phone)
					// id 데이터를 삽입한다   

					// ex) id = "test"
					// public void setId(String id) {
					// this.id = id;    →   this.id = test
					// }
					
					// 그러면 MemberDTO 파일 안에 있는 Private String id 의 값은 test 이다
					// 외부에서 dto.getId(); 하면 test값이 출력된다
					// 나머지도 같은 방식
					
					dto.setNum(num);
					dto.setName(name);
					dto.setTitle(title);
					dto.setBoard(board);
					dto.setTime(time);
					
					
					
					
					
					//  그후 list에 배열 dto값 삽입
					//  ex)   id: test  /  pw: 1234 / name: 홍길동  / email: aa@aa.com / phone 010-0000-000
					//  [1] : setId(id) , setPw(pw) , setName(name) , setEmail(email) , setPhone(phone) 
					//  위 ex에 있는 값이 들어감
					
					//  while 반복문이므로 위 코드랑 같이 작동되면서 계속 쌓아짐
					//  [2] , [3] , [4]  ..... 
					
					
					
				}
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패");
			} catch (SQLException e) {
				System.out.println("에러 " + e);
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
					if (stmt != null && !stmt.isClosed()) {
						stmt.close();
					}
					if (rs != null && !rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			// 쌓아진 list 값이  selectinfo()에 들어가며 외부로 보냄
			// 또한 배열 이므로
			// public static void selectinfo() 를
			// public static ArrayList<MemberDTO> selectinfo() 로 바꾼다
			return dto;
			
			
		}
		

		
		public static viewDTO BEdit(int num , String title, String board) {

			Connection conn = null;
			PreparedStatement stmt = null;
			
			
			
			viewDTO result = null;
			//System.out.println(ppointlimit);
			//System.out.println(ppoint);
//			DecimalFormat df = new DecimalFormat("00");
//			Calendar currentCalendar = Calendar.getInstance();
//			String last = df.format(currentCalendar.get(Calendar.DATE));
			
			try {
				// 1. 드라이버 로딩
				Class.forName("com.mysql.jdbc.Driver");

				// 2. 연결하기
				String url = "jdbc:mysql://localhost/zlzone?serverTimezone=Asia/Seoul";
				conn = DriverManager.getConnection(url, "root", "root");
				
				
				
				String sql = "Update board set title = ? , board = ? where num = ?";
				
				// 3. 쿼리 수행을 위한 Statement 객체 생성
				stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, title);
				stmt.setString(2, board);
				stmt.setInt(3, num);
				result = new viewDTO();
				
			
				
				stmt.executeUpdate();
				
				
				
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패");
			} catch (SQLException e) {
				System.out.println("에러 " + e);
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
					if (stmt != null && !stmt.isClosed()) {
						stmt.close();
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		
		
		public static viewDTO BDel(int num) {

			Connection conn = null;
			PreparedStatement stmt = null;
			PreparedStatement stmt1 = null;
			
			
			
			viewDTO result = null;
			//System.out.println(ppointlimit);
			//System.out.println(ppoint);
//			DecimalFormat df = new DecimalFormat("00");
//			Calendar currentCalendar = Calendar.getInstance();
//			String last = df.format(currentCalendar.get(Calendar.DATE));
			
			try {
				// 1. 드라이버 로딩
				Class.forName("com.mysql.cj.jdbc.Driver");

				// 2. 연결하기
				String url = "jdbc:mysql://localhost/zlzone?serverTimezone=Asia/Seoul";
				conn = DriverManager.getConnection(url, "root", "root");
				
				
				
				String sql = "delete from board where num = ?";
				String sql1 = "delete from board_comment where board_num = ?";
				stmt = conn.prepareStatement(sql);
				stmt1 = conn.prepareStatement(sql1);
				stmt.setInt(1, num);
				stmt1.setInt(1, num);
				//해당 게시글의 댓글도 삭제하는 기능
				
				
				
		
				
				
				
				
				
				
				result = new viewDTO();
				
			
				
				stmt.executeUpdate();
				stmt1.executeUpdate();
				
				
				
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패");
			} catch (SQLException e) {
				System.out.println("에러 " + e);
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
					if (stmt != null && !stmt.isClosed()) {
						stmt.close();
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		
		
		public static int boardComment(String board_num, String id, String name, String body , int level) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			Date now = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
			sf = new SimpleDateFormat("MM/dd HH:mm");
			String t = sf.format(now);     // t는  현재시간 : 년도/월   시간:분
			
			String num = null;



		
			
			
			
			// 외부로 보낼 결과값 ( 0이면 가입 실패 , 0아니면 가입 성공)
			int count = 0; 
			
			
			try {
				// 1. 드라이버 로딩
				Class.forName("com.mysql.cj.jdbc.Driver");

				// 2. 연결하기
				String url = "jdbc:mysql://localhost/zlzone?serverTimezone=Asia/Seoul";
				conn = DriverManager.getConnection(url, "root", "root");

				// 3. SQL 쿼리 준비
				// 추가하려는 데이터의 값은 전달된 인자를 통해 동적으로 할당되는 값이다.
				// 즉 어떤 값이 전달될지 모르므로 Select 할 때와 달리
				// stmt = conn.createStatement(); 를 작성하지 않고
				// pstmt = conn.prepareStatement(sql); 로 작성하여 데이터를 추가할 것임을 알립니다.
				// 물론 sql 쿼리 내에서 + 연산자로 한 줄로 작성할 수 있지만 가독성이 너무 떨어지게 되므로
				// 이 방법을 권합니다.
				String sql = "INSERT INTO board_comment VALUES (?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);

				// 4. 데이터 binding
				// 위 sql 문에서  ?로 된 모양에 데이터 순서대로 들어감
				pstmt.setString(1, board_num);
				pstmt.setString(2, num);
				pstmt.setString(3, name);
				pstmt.setString(4, body);
				pstmt.setInt(5, level);
				pstmt.setString(6, t);
				
				count = 1;
				
				
				
				
				

				// 5. 쿼리 실행 및 결과 처리
				// SELECT와 달리 INSERT는 반환되는 데이터들이 없으므로
				// ResultSet 객체가 필요 없고, 바로 pstmt.executeUpdate()메서드를 호출하면 됩니다.
				// INSERT, UPDATE, DELETE 쿼리는 이와 같이 메서드를 호출하며
				// SELECT에서는 stmt.executeQuery(sql); 메서드를 사용했었습니다.
				// @return     int - 몇 개의 row가 영향을 미쳤는지를 반환
				
				// 실행결과를 count 변수에 삽입 
				count = pstmt.executeUpdate();
				if (count == 0) {
					System.out.println("데이터 입력 실패");
				} else {
					System.out.println("데이터 입력 성공");
				}
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패");
			} catch (SQLException e) {
				System.out.println("에러 " + e);
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
					if (pstmt != null && !pstmt.isClosed()) {
						pstmt.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// 외부로 count 값을 보냄  (Servlet or JSP) 
			return count;
		}
		
		//댓글보기
		public static ArrayList<viewDTO> commentView(int board_num) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			// 한번에 담기위해 MemberDTO 쓴다
			// 클래스명도 타입취급한다
			ArrayList<viewDTO> list = new ArrayList<>();
			viewDTO dto = null;
			
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				String url = "jdbc:mysql://localhost/zlzone?serverTimezone=Asia/Seoul";
				conn = DriverManager.getConnection(url, "root", "root");
				
				
				String sql = "select * from board_comment where board_num = ? ORDER BY num ASC";
				
				
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, board_num);
				
				
				rs = stmt.executeQuery();
				
				//System.out.println(rs);

				while (rs.next()) {
					int num1 = rs.getInt("num");
					//String id = rs.getString("id");
					String name = rs.getString("name");
					String board = rs.getString("body");
					int level = rs.getInt("level");
					String time = rs.getString("time");
					viewDTO dto1 = new viewDTO();
				
//					System.out.println(name);
//					System.out.println(title);
//					System.out.println(board);
					
					// 값을 다 받기위해 while문 안에 씀
					
					
					
					// MemberDTO 파일 안에 있는 setId(String id) 메소드에 매개변수인 id 변수에
					// SELECT * FROM member 으로 나온 한 데이터중 ( id,pw,name,email,phone)
					// id 데이터를 삽입한다   

					// ex) id = "test"
					// public void setId(String id) {
					// this.id = id;    →   this.id = test
					// }
					
					// 그러면 MemberDTO 파일 안에 있는 Private String id 의 값은 test 이다
					// 외부에서 dto.getId(); 하면 test값이 출력된다
					// 나머지도 같은 방식
					
					dto1.setNum(num1);
					dto1.setName(name);
					dto1.setBoard(board);
					dto1.setTime(time);
					dto1.setLevel(level);
					list.add(dto1);
					
					
					
					
					
					//  그후 list에 배열 dto값 삽입
					//  ex)   id: test  /  pw: 1234 / name: 홍길동  / email: aa@aa.com / phone 010-0000-000
					//  [1] : setId(id) , setPw(pw) , setName(name) , setEmail(email) , setPhone(phone) 
					//  위 ex에 있는 값이 들어감
					
					//  while 반복문이므로 위 코드랑 같이 작동되면서 계속 쌓아짐
					//  [2] , [3] , [4]  ..... 
					
					
					
				}
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패");
			} catch (SQLException e) {
				System.out.println("에러 " + e);
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
					if (stmt != null && !stmt.isClosed()) {
						stmt.close();
					}
					if (rs != null && !rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
			}
			return list;
			// 쌓아진 list 값이  selectinfo()에 들어가며 외부로 보냄
			// 또한 배열 이므로
			// public static void selectinfo() 를
			// public static ArrayList<MemberDTO> selectinfo() 로 바꾼다
		}
		
		public static int commentCount(int num) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			int cnt = 0 ; // 갯수 반환
			
			
			try {
				Class.forName("com.mysql.jdbc.Driver");

				String url = "jdbc:mysql://localhost/zlzone?serverTimezone=Asia/Seoul";
				conn = DriverManager.getConnection(url, "root", "root");
				
				
				String sql = "select count(*) from board_comment where board_num = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, num);
				//System.out.println("확인: "+num);
				
				
				rs = stmt.executeQuery();
				if (rs.next()) {
					cnt = rs.getInt(1);  // 첫번째 값 가져오기				
					
				}
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패");
			} catch (SQLException e) {
				System.out.println("에러 " + e);
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
					if (stmt != null && !stmt.isClosed()) {
						stmt.close();
					}
					if (rs != null && !rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return cnt;
		}
		
		
		
	
		
		
		
		public static ArrayList<viewDTO> selectComment(int num, int start , int exit) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			// 한번에 담기위해 MemberDTO 쓴다
			// 클래스명도 타입취급한다
			ArrayList<viewDTO> list = new ArrayList<>();
			
			
			
			try {
				Class.forName("com.mysql.jdbc.Driver");

				String url = "jdbc:mysql://localhost/zlzone?serverTimezone=Asia/Seoul";
				conn = DriverManager.getConnection(url, "root", "root");
				
				
				String sql = " select * from board_comment where board_num = ? order by num desc limit ?,? ";

				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, num);
				stmt.setInt(2, start);
				stmt.setInt(3, exit);
				
				rs = stmt.executeQuery();

				while (rs.next()) {
					String comment_num = rs.getString("num");
					String name = rs.getString("name");
					//String title = rs.getString("title");
					String board = rs.getString("body");
					String time = rs.getString("time");
					int level = rs.getInt("level");
				
					
					// 값을 다 받기위해 while문 안에 씀
					viewDTO dto = new viewDTO();
					
					
					// MemberDTO 파일 안에 있는 setId(String id) 메소드에 매개변수인 id 변수에
					// SELECT * FROM member 으로 나온 한 데이터중 ( id,pw,name,email,phone)
					// id 데이터를 삽입한다   

					// ex) id = "test"
					// public void setId(String id) {
					// this.id = id;    →   this.id = test
					// }
					
					// 그러면 MemberDTO 파일 안에 있는 Private String id 의 값은 test 이다
					// 외부에서 dto.getId(); 하면 test값이 출력된다
					// 나머지도 같은 방식
					
					dto.setCommentnum(comment_num);
					dto.setName(name);
					//dto.setTitle(title);
					dto.setBoard(board);
					dto.setTime(time);
					dto.setLevel(level);
					
					
					
					//  그후 list에 배열 dto값 삽입
					//  ex)   id: test  /  pw: 1234 / name: 홍길동  / email: aa@aa.com / phone 010-0000-000
					//  [1] : setId(id) , setPw(pw) , setName(name) , setEmail(email) , setPhone(phone) 
					//  위 ex에 있는 값이 들어감
					
					//  while 반복문이므로 위 코드랑 같이 작동되면서 계속 쌓아짐
					//  [2] , [3] , [4]  ..... 
					list.add(dto);
					
					
				}
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패");
			} catch (SQLException e) {
				System.out.println("에러 " + e);
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
					if (stmt != null && !stmt.isClosed()) {
						stmt.close();
					}
					if (rs != null && !rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// 쌓아진 list 값이  selectinfo()에 들어가며 외부로 보냄
			// 또한 배열 이므로
			// public static void selectinfo() 를
			// public static ArrayList<MemberDTO> selectinfo() 로 바꾼다
			return list;
		}
		
		public static viewDTO commentDel(int num) {

			Connection conn = null;
			PreparedStatement stmt = null;
			
			viewDTO dto = null;
			
			
			//System.out.println(ppointlimit);
			//System.out.println(ppoint);
//			DecimalFormat df = new DecimalFormat("00");
//			Calendar currentCalendar = Calendar.getInstance();
//			String last = df.format(currentCalendar.get(Calendar.DATE));
			
			try {
				// 1. 드라이버 로딩
				Class.forName("com.mysql.cj.jdbc.Driver");

				// 2. 연결하기
				String url = "jdbc:mysql://localhost/zlzone?serverTimezone=Asia/Seoul";
				conn = DriverManager.getConnection(url, "root", "root");
				
				
				
				String sql = "delete from board_comment where num = ?";
				
				// 3. 쿼리 수행을 위한 Statement 객체 생성
				stmt = conn.prepareStatement(sql);
				
				
				stmt.setInt(1, num);
				
				
			
				
				stmt.executeUpdate();
				
				
				
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패");
			} catch (SQLException e) {
				System.out.println("에러 " + e);
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
					if (stmt != null && !stmt.isClosed()) {
						stmt.close();
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return dto;
			
		}

		
		
}

