package com.ryan.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ryan.dao.adminDAO;

@WebServlet("*.ad")
public class adminservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public adminservlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String[] uris = uri.split("/");

		// 날짜 값
		Date now = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		sf = new SimpleDateFormat("MM/dd HH:mm");
		sf = new SimpleDateFormat("mm");
		String t = sf.format(now); // t는 현재시간 : 년도/월 시간:분
		String t1 = sf.format(now); // t1는 현재시간 : 분

		// 일일 초기화 조건
		int time1 = 0;
		var curDate = new Date();
		var cultime = curDate.getHours();

		if (uris[2].equals("betbet.ad")) {

			RequestDispatcher dis = request.getRequestDispatcher("./admin/betbet.jsp");
			dis.forward(request, response);
		}
		
		
		if (uris[2].equals("betbet_ok.ad")) {
			String fight1 = request.getParameter("fight1");
			String fight2 = request.getParameter("fight2");
			
			
			String years = request.getParameter("years");
			String months = request.getParameter("months");
			String days = request.getParameter("days");
			String times = request.getParameter("times");
			
			
			String time = years+"/"+months+"/"+days+" "+times+":00";
			
			int dao = adminDAO.insert(fight1,fight2,time);
			
			if(dao == 0) {
				System.out.println("경기등록 실패");
			}else {
				System.out.println("경기등록 성공");
			}
			
			
			
			
			RequestDispatcher dis = request.getRequestDispatcher("./admin/betadmin.jsp");
			dis.forward(request, response);
		}

		if (uris[2].equals("winlose.ad")) {

			RequestDispatcher dis = request.getRequestDispatcher("./admin/winlose.jsp");
			dis.forward(request, response);
		}
		if (uris[2].equals("user_ad.ad")) {

			RequestDispatcher dis = request.getRequestDispatcher("./admin/user_ad.jsp");
			dis.forward(request, response);
		}

	}

	private adminDAO insert(String fight1, String fight2, String time) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
