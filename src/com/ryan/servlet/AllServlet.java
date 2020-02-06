package com.ryan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ryan.dao.BettingDAO;
import com.ryan.dao.BoardListDAO;
import com.ryan.dao.BoardinsertDAO;
import com.ryan.dao.ChargeDAO;
import com.ryan.dao.JoinCheckDAO;
import com.ryan.dao.MemberDAO;
import com.ryan.dto.MemberDTO;
import com.ryan.dto.MyBetDTO;
import com.ryan.dto.viewDTO;

@WebServlet("*.ap")
public class AllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AllServlet() {
        super();
    }
    // 접속 아이피
    public static String getClientIp(HttpServletRequest req) {
        String ip = req.getHeader("X-Forwarded-For");
        if (ip == null) ip = req.getRemoteAddr();
        return ip;
    }
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String [] uris = uri.split("/");
		
		// 날짜 값
		Date now = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		sf = new SimpleDateFormat("MM/dd HH:mm");
		sf = new SimpleDateFormat("mm");
		String t = sf.format(now);     // t는  현재시간 : 년도/월   시간:분
		String t1 = sf.format(now);   //  t1는 현재시간 : 분
		
		
		// 일일 초기화 조건
		int time1 = 0;
		var curDate = new Date();
		var cultime = curDate.getHours();
		//System.out.println(cultime);
		if(cultime == 0) {
			//String uid = request.getParameter("id");
			
			int d = ChargeDAO.Dayday();
			
			if(d != 1) {
				System.out.println("성공");
			}else{
				System.out.println("실패");
			}
			
		}
		
		
		if(cultime == 0) {
			//String uid = request.getParameter("id");
			
			int d = ChargeDAO.DayChance();
			
			if(d != 1) {
				System.out.println("성공");
			}else{
				System.out.println("실패");
			}
			
		}
		
		
		
		
		// 아이디 중복검사
		if(uris[2].equals("idCheck.ap")) {
			// 2. 회원가입.jsp 에서 받아온 아이디,비번 정보
			String id = request.getParameter("userId");  
			
			response.getWriter().println(JoinCheckDAO.select(id));	
		}
		
		// 이메일 중복검사
		if(uris[2].equals("emCheck.ap")) {
			// 2. 회원가입.jsp 에서 받아온 이메일 정보
			String email = request.getParameter("Email");  
			
			response.getWriter().println(JoinCheckDAO.select2(email));
		}
		
		// 전화번호 중복검사
		if(uris[2].equals("PCheck.ap")) {
			// 2. 회원가입.jsp 에서 받아온 전화번호 정보
			String phone = request.getParameter("Phone");  
			
			response.getWriter().println(JoinCheckDAO.select2(phone));		
		}
		
		
		// 회원가입
		if(uris[2].equals("mb_ok.ap")) {
			
			 PrintWriter out = response.getWriter();
			 // 1. 클라이언트(브라우저)에서 받음 (POST)
			 String id = request.getParameter("id");
			 String pw = request.getParameter("pw");
			 String name = request.getParameter("name");
			 String email = request.getParameter("email");
			 String phone = request.getParameter("phone");
			 
			 
			
			 
			 // 2. 클라이언트에서 받은 데이터를
			 // insert(String id, String pw, String name,String email,String phone) 
			 // 메소드에 삽입
			 // insert 메소드 작동 결과 값을 result 변수에 삽입 (1 or 0)
			 int result = MemberDAO.insert(id, pw, name, email, phone);
			 
			 
			if(result == 0 ) {
				// 에러 페이지 이동
				//response.sendRedirect("joinerr.jsp");
				out.println("<Script> alert('중복된 아이디입니다'); history.back(); </script>");
			}
			else {
				// 메인 or 로그인페이지 이동
				response.sendRedirect("./html/mb_ok.jsp");
				
			}	
		}
		
		// 로그인
		if(uris[2].equals("lg_ok.ap")) {
			// 1. 세션 사용하기
			HttpSession session = request.getSession();

			// 2. 로그인.jsp 에서 받아온 아이디,비번 정보
			String id = request.getParameter("id");  
			String pw = request.getParameter("pw");

			// 3. script 문 사용하기위해 씀
			PrintWriter out = response.getWriter();
			
			// 날짜 수정
			MemberDAO a = new MemberDAO();
			a.update(id);
			
			
			// 4. 디비에 물어봄 id,pw있는지??
			// MemberDAO 파일속 select(String tid, String tpw) 메소드에 tid,tpw 값을 넣으면서 작동한다
			// ex) tid = id   , tpw = pw   
			// MemberDTO dto변수로 Getter 메소드들을 호출함  
			MemberDTO dto = MemberDAO.select(id, pw);
		

			
			// 5. DBClass에서 받아온 dto 값 확인
			// null or not null
			if (dto == null) {
				
				// 로그인 실패
				// 스크립트 발동
				out.println("<Script>alert('정보가 맞지않습니다.'); history.back();</script>");

			} else {
				
				// 6. 세션 설정
				// session.setAttribute("변수", "값");
				// 세션: name 변수에는 DB속 Name값이 들어감
				session.setAttribute("id", dto.getId());
				session.setAttribute("name", dto.getName());
				session.setAttribute("level", dto.getLevel());
				session.setAttribute("point", dto.getPoint());
				session.setAttribute("charge", dto.getCharge());
				session.setAttribute("pointlimit", dto.getPointlimit());
				session.setAttribute("adm", dto.getAdm());
				
				
				// 페이지에 출력
				response.getWriter().append("로그인 성공");
				// 페이지 이동
				response.sendRedirect("./info.ap");
			}	
		}
		
		
		// 회원정보 출력
		if(uris[2].equals("info.ap")) {
			// 한글 깨짐 방지
			response.setContentType("text/html;charset=utf-8");
	
	// 세션값 받아오기 (object인 값을 강제로 문자열로 변환)
	
	PrintWriter out = response.getWriter();
	HttpSession Session= request.getSession();
	String name = (String) Session.getAttribute("name");
	if (name == null) {	 
		out.println("<script> alert('로그인 하세요'); history.back();</script>");
		
	} else {
		String ip = getClientIp(request);
		// 로그인 됨
		System.out.println("접속 아이디: " + name);
		System.out.println("접속 아이피: " + ip);
	}
	
	
	
	
	// 1. DBClass 파일에 있는 selectinfo() 메소드에
	// 반환된 값(return list)를 mlist 변수에 ArrayList배열로 삽입
	ArrayList<MemberDTO> mlist = MemberDAO.selectinfo();
						// 키 ,  object(ArrayList<MemberDTO>)
	request.setAttribute("mlist", mlist);
	
	RequestDispatcher dis = request.getRequestDispatcher("./html/home.jsp");
	dis.forward(request, response);
	
	
	
	
	
	
	// 2. 반환된 값이 들어간 mlist를 
			// MemberDTO m변수로 Getter 메소드들을 호출하며  
			// 값이 없어질때까지 반복
			//for(MemberDTO m : mlist) {

				// DTO에서 받아온 GET 메소드 정보를 시스템에 출력
				// 변수.메소드();
				//System.out.println(m.getId());
				//System.out.println(m.getPw());
				//System.out.println(m.getName());
				//System.out.println(m.getEmail());
				//System.out.println(m.getPhone());
			//}
		
		}

		
		
		
		
		
		
		
		
		
		
		if(uris[2].equals("hcharge.ap")) {
			// 1. 세션 사용하기
			HttpSession session = request.getSession();
			
			// 2. 로그인.jsp 에서 받아온 아이디,비번 정보
			 String id = (String) session.getAttribute("id"); 
			
			String i = request.getParameter("point");
			int point = Integer.parseInt(i);
			//System.out.print(point+1);
			PrintWriter out = response.getWriter();
			
			
			int pointlimit = ((Integer)(session.getAttribute("pointlimit"))).intValue();
			int charge = ((Integer)(session.getAttribute("charge"))).intValue();
			
			//MemberDAO.Charge(id, point, pointlimit, charge);

			// 3. script 문 사용하기위해 씀
			//PrintWriter out = response.getWriter();
			//System.out.println( point);
			//System.out.println(id + "/" + point + "/" +pointlimit + "/" +charge);
//			if(pointlimit <= point) {
//				 PrintWriter out = response.getWriter();
//				 out.println("<Script>alert('한도초과입니다.'); history.back();</script>");
//					}
			MemberDTO dto2 = ChargeDAO.Charge(id,point,pointlimit,charge);
			
			
			
		//MemberDAO dto = new MemberDAO();
			MemberDTO dto = ChargeDAO.refresh(id); //포인트 갱신용
			//System.out.println(dto2.getPointlimit());
			//dto.refresh(id);
			if(dto2 == null){
				System.out.println("포인트 적립 실패 확인!");
				out.println("<script> alert('포인트 한도가 초과되었습니다.'); history.back();</script>");
				
			
			
			}else {
					if(dto != null) {
						session.setAttribute("point", dto.getPoint());
						session.removeAttribute("pointlimit");
						
						session.setAttribute("pointlimit", dto2.getPointlimit());
						session.setAttribute("charge", dto.getCharge());
						response.sendRedirect("home.ap");
					  }
				
			
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		
		// 충전하기 
		if(uris[2].equals("hcharge.ap")) {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		 String id = (String) session.getAttribute("id"); 
		
		String i = request.getParameter("point");
		int point = Integer.parseInt(i);
		
		
		int level = ((Integer)(session.getAttribute("level"))).intValue();
		int pointlimit = ((Integer)(session.getAttribute("pointlimit"))).intValue();
		int charge = ((Integer)(session.getAttribute("charge"))).intValue();

		
		if( level == 1) {
		if ( point > 10000) {
			out.println("<script> alert('초과'); history.back();</script>");
		}
		if(charge > 10000) {
			out.println("<script> alert('초과'); history.back();</script>");
		}
		else if (charge < 10001 &&  level == 1) {
		MemberDAO.Charge1(id,point);

		MemberDTO dto = MemberDAO.refresh(id);
		
		if(dto.getCharge() < 10001) {
			
			MemberDAO.Charge2(id, point);
			int b = 10000;
			int c = b - dto.getCharge();
			MemberDAO.Charge3(id, c);
			MemberDTO dto1 = MemberDAO.refresh(id);
			session.setAttribute("point", dto1.getPoint());
			session.setAttribute("pointlimit", dto1.getPointlimit());
			session.setAttribute("charge", dto1.getCharge());
			
			response.sendRedirect("home.ap");
		}
		else {
			
			MemberDAO.Charge4(id, point);
			out.println("<script> alert('초과'); history.back();</script>");
		}
		}
		
		}
		
		
		if( level == 2) {
			if ( point > 100000) {
				out.println("<script> alert('초과'); history.back();</script>");
			}
			if(charge > 100000) {
				out.println("<script> alert('초과'); history.back();</script>");
			}
			else if (charge < 100001 &&  level == 2) {
			MemberDAO.Charge1(id,point);

			MemberDTO dto = MemberDAO.refresh(id);
			
			if(dto.getCharge() < 100001) {
				
				MemberDAO.Charge2(id, point);
				int b = 100000;
				int c = b - dto.getCharge();
				MemberDAO.Charge3(id, c);
				MemberDTO dto1 = MemberDAO.refresh(id);
				session.setAttribute("point", dto1.getPoint());
				session.setAttribute("pointlimit", dto1.getPointlimit());
				session.setAttribute("charge", dto1.getCharge());
				
				response.sendRedirect("home.ap");
			}
			else {
				
				MemberDAO.Charge4(id, point);
				out.println("<script> alert('초과'); history.back();</script>");
			}
			}
			
			}
		
		
		
		
		
		if( level == 3) {
			if ( point > 500000) {
				out.println("<script> alert('초과'); history.back();</script>");
			}
			if(charge > 500000) {
				out.println("<script> alert('초과'); history.back();</script>");
			}
			else if (charge < 500001 &&  level == 3) {
			MemberDAO.Charge1(id,point);

			MemberDTO dto = MemberDAO.refresh(id);
			
			if(dto.getCharge() < 500001) {
				
				MemberDAO.Charge2(id, point);
				int b = 500000;
				int c = b - dto.getCharge();
				MemberDAO.Charge3(id, c);
				MemberDTO dto1 = MemberDAO.refresh(id);
				session.setAttribute("point", dto1.getPoint());
				session.setAttribute("pointlimit", dto1.getPointlimit());
				session.setAttribute("charge", dto1.getCharge());
				
				response.sendRedirect("home.ap");
			}
			else {
				
				MemberDAO.Charge4(id, point);
				out.println("<script> alert('초과'); history.back();</script>");
			}
			}
			
			}

		
		
		if( level == 4) {
			if ( point > 1000000) {
				out.println("<script> alert('초과'); history.back();</script>");
			}
			if(charge > 1000000) {
				out.println("<script> alert('초과'); history.back();</script>");
			}
			else if (charge < 1000001 &&  level == 4) {
			MemberDAO.Charge1(id,point);

			MemberDTO dto = MemberDAO.refresh(id);
			
			if(dto.getCharge() < 1000001) {
				
				MemberDAO.Charge2(id, point);
				int b = 1000000;
				int c = b - dto.getCharge();
				MemberDAO.Charge3(id, c);
				MemberDTO dto1 = MemberDAO.refresh(id);
				session.setAttribute("point", dto1.getPoint());
				session.setAttribute("pointlimit", dto1.getPointlimit());
				session.setAttribute("charge", dto1.getCharge());
				
				response.sendRedirect("home.ap");
			}
			else {
				
				MemberDAO.Charge4(id, point);
				out.println("<script> alert('초과'); history.back();</script>");
			}
			}
			
			}
		
		
		
		
		
		
		
		
		
		
		

		
		
		}
		*/
		
		
		
		
		
		
		// 배팅
		if(uris[2].equals("betting.ap")) {
			HttpSession Session= request.getSession();
			PrintWriter out = response.getWriter();
			
			
			
			 String id = (String) Session.getAttribute("id"); 
			 
			 
			 MemberDTO dto = ChargeDAO.refresh(id);
			 
			 String num = request.getParameter("num");
			 int num1 = Integer.parseInt(num);
			 
			 String i = request.getParameter("bet");
			 int bet = Integer.parseInt(i);
			 String beti = request.getParameter("beti");
			 int beti1 = Integer.parseInt(beti);
			 int a = BettingDAO.betCheck(id, num1);
			 if (dto.getPoint() < bet) {
				 out.println("<script> alert('포인트가 부족합니다.'); history.back();</script>");
			 }else if(a == 0) {
				 out.println("<script> alert('중복된 경기입니다'); history.back();</script>");
			 }
			 else {
				 BettingDAO.Charge1(id,bet);
				 out.println("<script> alert('배팅되었습니다.'); ");
			 
			 String time = request.getParameter("time");
			 String home = request.getParameter("home");
			 String homeb = request.getParameter("homeb");
			
			 
			 BettingDAO.bet(id,num1,time,home,homeb,bet,beti1);
			 //BettingDAO.selectMyBet(id);
			 MemberDTO dto1 = ChargeDAO.refresh(id);
			 Session.setAttribute("point", dto1.getPoint());
			 
			 response.sendRedirect("mybet.ap");
			 
		}
		}
		
		
		if(uris[2].equals("mybet.ap")) {
			HttpSession Session= request.getSession();
			//PrintWriter out = response.getWriter();
			
			
			
			 String id = (String) Session.getAttribute("id"); 
			 
			 
			// MemberDTO dto = ChargeDAO.refresh(id);
			 
			
			
			
			 ArrayList<MyBetDTO> list = BettingDAO.selectMyBet(id);
			 MemberDTO dto1 = ChargeDAO.refresh(id);
			 Session.setAttribute("point", dto1.getPoint());
			 RequestDispatcher dis = request.getRequestDispatcher("./html/mybet.jsp");
				
			dis.forward(request, response);
			 
		}
		
		
		
		
		
				//게시판 글쓰기
				if(uris[2].equals("insert.ap")) {
					HttpSession session = request.getSession();
					
					String id = (String) session.getAttribute("id");
					//작성자
					String name = (String) session.getAttribute("name");
					
					int level = ((Integer)(session.getAttribute("level"))).intValue();
					//타이틀
					String title = request.getParameter("title");
					//내용
					String board = request.getParameter("board");
					//System.out.println(id +" "+name+" "+" "+title+" "+board );
					
					BoardinsertDAO s = new BoardinsertDAO();
					int a = s.boardWrite(id, name, title , board, level);
					response.sendRedirect("board.ap");
					
					if(a == 1) {
					System.out.println("성공");
					}
					
					
					//int dto = MemberDAO.boardWrite(id,name);
					
					
				}
				
				
				
				
				
				
				
				
				
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// 홈페이지 메뉴판 이동
		if(uris[2].equals("home.ap")) {
			
		RequestDispatcher dis = request.getRequestDispatcher("./html/home2.jsp");
		dis.forward(request, response);
			
		}
		// 크로스
		if(uris[2].equals("cross.ap")) {
		//response.sendRedirect("./html/cross.jsp");
		RequestDispatcher dis = request.getRequestDispatcher("./html/cross.jsp");
		dis.forward(request, response);
		}
		// 경기결과
		if(uris[2].equals("result.ap")) {
		//response.sendRedirect("./html/result.jsp");
		RequestDispatcher dis = request.getRequestDispatcher("./html/result.jsp");
		dis.forward(request, response);
		}
		// 자유게시판
		if(uris[2].equals("board.ap")) {
		//response.sendRedirect("./html/board.jsp");
		RequestDispatcher dis = request.getRequestDispatcher("./html/board.jsp");
		dis.forward(request, response);
		}
		// 충전하기
		if(uris[2].equals("charge.ap")) {
		//response.sendRedirect("./htmlcharge.jsp");
			
			
		RequestDispatcher dis = request.getRequestDispatcher("./html/charge.jsp");
		dis.forward(request, response);
		}
		// 배팅내역
		
		// 글쓰기
		if(uris[2].equals("write.ap")) {
		//response.sendRedirect("./htmlcharge.jsp");
		RequestDispatcher dis = request.getRequestDispatcher("./html/write.jsp");
		dis.forward(request, response);
		}
		// 글보기
		if(uris[2].equals("view.ap")) {
		//response.sendRedirect("./htmlcharge.jsp");
			RequestDispatcher dis = request.getRequestDispatcher("./html/view.jsp");
			dis.forward(request, response);
			String num = request.getParameter("num");
			
			int n = Integer.parseInt(num);
			//viewDTO dto = BoardListDAO.boardView(n);
			//System.out.println(num);
			//BoardListDAO l = new BoardListDAO();
			//BoardListDAO.boardView(n);
		
		
		
		
		
		}
		// 글수정
		if(uris[2].equals("edit.ap")) {
		//response.sendRedirect("./htmlcharge.jsp");
		RequestDispatcher dis = request.getRequestDispatcher("./html/edit.jsp");
		dis.forward(request, response);
		}
		
		if(uris[2].equals("edit_ok.ap")) {
			//response.sendRedirect("./htmlcharge.jsp");
			String num = request.getParameter("num");
			int n = Integer.parseInt(num);
			String title = request.getParameter("title");
			String board = request.getParameter("board");
			viewDTO dto = BoardListDAO.BEdit(n,title,board);
			
			
			RequestDispatcher dis = request.getRequestDispatcher("./html/view.jsp");
			
			dis.forward(request, response);
			}
		
		
		
		//글삭제
		if(uris[2].equals("del.ap")) {
			//response.sendRedirect("./htmlcharge.jsp");
			String num = request.getParameter("num");
			int n = Integer.parseInt(num);
			
			viewDTO dto = BoardListDAO.BDel(n);
			
			
			RequestDispatcher dis = request.getRequestDispatcher("./html/board.jsp");
			
			dis.forward(request, response);
			}
		
		
		//댓글입력
		if(uris[2].equals("comment.ap")) {
			//response.sendRedirect("./htmlcharge.jsp");
			//int board_num, String id, String name, String body , int level
			HttpSession session = request.getSession();
			PrintWriter out = response.getWriter();
			String board_num = request.getParameter("board_num");
			System.out.println(board_num);
			//int board_num = Integer.parseInt(board_num1);
			String id = (String) session.getAttribute("id");
			String name = (String) session.getAttribute("name");
			
			int level = ((Integer)(session.getAttribute("level"))).intValue();
			String body = request.getParameter("body");
			BoardListDAO a = new BoardListDAO();
			
			int b = a.boardComment(board_num, id, name, body, level);
			
			if(b == 1) {
				System.out.println("댓글입력성공");
			}else {
				System.out.println("댓글입력실패");
			}
			
			
			out.println("<script>alert('댓글입력완료');history.back();</script>");
			out.println("<script>window.location.reload();</script>");
			
//			response.sendRedirect("view.jsp");
			
//			RequestDispatcher dis = request.getRequestDispatcher("./html/view.jsp?"+ board_num);
//			dis.forward(request, response);
			}
		
		if(uris[2].equals("commentdel.ap")) {
			//response.sendRedirect("./htmlcharge.jsp");
			String num = request.getParameter("num");
			int n = Integer.parseInt(num);
			System.out.println(num);
			
			viewDTO dto = BoardListDAO.commentDel(n);
			PrintWriter out = response.getWriter();
			out.println("<script>alert('댓글삭제완료');history.back();</script>");
			out.println("<script>window.location.reload();</script>");
			
//			RequestDispatcher dis = request.getRequestDispatcher("./html/view.jsp");
//			
//			dis.forward(request, response);
			}
		
		if(uris[2].equals("betadmin.ap")) {
			//response.sendRedirect("./htmlcharge.jsp");
			RequestDispatcher dis = request.getRequestDispatcher("./admin/betadmin.jsp");
			dis.forward(request, response);
			}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
