package com.ezen.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.member.MemberDAO;
import com.ezen.member.MemberVO;

public class MemberService {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private final String path="/WEB-INF/views/member/";

	public MemberService(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
	}

	public String exec() {
		String cmd=request.getParameter("cmd");
		if(cmd.equals("login")) {
			return LoginService();
		} else if(cmd.equals("logout")) {
			//세션얻기
			HttpSession s=request.getSession();
			//세션 지우기
			s.invalidate();
			//페이지이동-List
			return "book?cmd=list";
		}
		return null;
	}


	private String LoginService() {
		String method=request.getMethod().toUpperCase();
		if(method.equals("GET")) {//get
			//페이지 이동
			return path+"login.jsp";
		} else {//post
			//파라메타 받기
			String id=request.getParameter("userid");
			String pwd=request.getParameter("pwd");
			//dao 객체생성
			MemberDAO dao=MemberDAO.getInstance();
			//메소드 수행 결과 받기 
			MemberVO mvo=dao.login(id, pwd);
			if(mvo!=null) {//로그인 성공
				//세션 저장->request를 통해서 세션객체 얻기
				HttpSession session=request.getSession();
				session.setAttribute("mvo", mvo);
				//페이지에서 필요로 하는 정보를 저장 ??
				//페이지이동 : list 쪽으로 이동
				return "book?cmd=list";
			}else {//로그인 실패
				request.setAttribute("message", "로그인 실패!");
				//페이지 이동
				return path+"login.jsp";
			}
		}
	}

}
