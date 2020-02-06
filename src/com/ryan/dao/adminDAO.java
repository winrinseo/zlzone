package com.ryan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ryan.dto.MemberDTO;
import com.ryan.dto.bettingDTO;

public class adminDAO {
	public static int insert(String fight1, String fight2, String time) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String num = null;
		int team_count1 = 1;
		int team_count2 = 1;
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
			String sql = "INSERT INTO bet_fight VALUES (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			// 4. 데이터 binding
			// 위 sql 문에서  ?로 된 모양에 데이터 순서대로 들어감
			pstmt.setString(1, num);
			pstmt.setString(2, fight1);
			pstmt.setString(3, fight2);
			pstmt.setInt(4, team_count1); //배팅한 인원
			pstmt.setInt(5, team_count2);
			pstmt.setString(6, time);
			

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
	
	public static ArrayList<bettingDTO> selectBet() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// 한번에 담기위해 MemberDTO 쓴다
		// 클래스명도 타입취급한다
		ArrayList<bettingDTO> list = new ArrayList<>();
		
		
		
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://localhost/zlzone?serverTimezone=Asia/Seoul";
			conn = DriverManager.getConnection(url, "root", "root");
			
			
			String sql = "SELECT * FROM bet_fight";

			// 3. 쿼리 수행을 위한 Statement 객체 생성
			stmt = conn.prepareStatement(sql);
			
			
			// 4. 쿼리 수행
			// 레코드들은 ResultSet 객체에 추가된다.
			rs = stmt.executeQuery();

			// 5. 실행결과 출력하기
			// SQL 데이터 나온 값을  없어질 때 까지 반복
			while (rs.next()) {
				
				// 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
				// 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
				String team1 = rs.getString("1team");
				String team2 = rs.getString("2team");
				String time = rs.getString("time");
				int teamcount1 = rs.getInt("1team_count");
				int teamcount2 = rs.getInt("2team_count");
				int num = rs.getInt("num");
				
				double all = teamcount1 + teamcount2;
				double team1_odds = teamcount1/all;
				double team2_odds = teamcount2/all;
				double team1_allo = 1/team1_odds;
				double team2_allo = 1/team2_odds;
				
				String allo1 = String.format("%.2f",team1_allo);
				String allo2 = String.format("%.2f",team2_allo);
				//System.out.println(allo1);
				// 값을 다 받기위해 while문 안에 씀
				bettingDTO dto = new bettingDTO();
				
				
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
				dto.setTeam1(team1);
				dto.setTeam2(team2);
				dto.setTeam1_count(teamcount1);
				dto.setTeam2_count(teamcount2);
				dto.setAllo1(allo1);
				dto.setAllo2(allo2);
				dto.setTime(time);
				
				
				
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

}
