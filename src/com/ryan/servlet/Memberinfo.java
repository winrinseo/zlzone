package com.ryan.servlet;

import com.ryan.dao.*;
import com.ryan.dto.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 가입된 멤버 정보 출력 서블릿
@WebServlet("/info")
public class Memberinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Memberinfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. DBClass 파일에 있는 selectinfo() 메소드에
		// 반환된 값(return list)를 mlist 변수에 ArrayList배열로 삽입
		ArrayList<MemberDTO> mlist = MemberDAO.selectinfo();
		
		// 2. 반환된 값이 들어간 mlist를 
		// MemberDTO m변수로 Getter 메소드들을 호출하며  
		// 값이 없어질때까지 반복
		for(MemberDTO m : mlist) {
			
			// DTO에서 받아온 GET 메소드 정보를 시스템에 출력
			// 변수.메소드();
			System.out.println(m.getId());
			System.out.println(m.getPw());
			System.out.println(m.getName());
			System.out.println(m.getEmail());
			System.out.println(m.getPhone());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
