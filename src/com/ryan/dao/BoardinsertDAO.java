package com.ryan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardinsertDAO {
	//게시판 글쓰기
	
	public static int boardWrite(String id, String name , String title , String board , int level) {
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
			String sql = "INSERT INTO board VALUES (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			// 4. 데이터 binding
			// 위 sql 문에서  ?로 된 모양에 데이터 순서대로 들어감
			pstmt.setString(1, num);
			pstmt.setString(2, name);
			pstmt.setString(3, title);
			pstmt.setString(4, board);
			pstmt.setString(5, t);
			pstmt.setInt(6, level);
			
			
			
			
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
}
