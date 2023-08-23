package com.ezen.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.book.BookDAO;

@WebServlet("/bDelete")
public class BookDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BookDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//encoding x
		//파라메타 받기
		int bno=Integer.parseInt(request.getParameter("bno"));
		//dao 객체생성
		BookDAO dao=BookDAO.getInstance();
		//dao 메소드 구현
		int result=dao.deleteBook(bno);
		if(result==1) {//삭제 완료
			RequestDispatcher rd=request.getRequestDispatcher("bList");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
