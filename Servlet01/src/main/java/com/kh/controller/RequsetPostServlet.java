package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequsetPostServlet
 */
@WebServlet("/test2.do")
public class RequsetPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequsetPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get 호출완료");
		
		//요청시 전달된 값들은 request의 parameter영역에 담겨있음
		
		//POST방식 요청같은 경우
		//데이터를 추출하기 [전!]에 인코딩설정을 해야함
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name"); // "홍길동" | ""
		String gender = request.getParameter("gender"); // "M" | "F" | null
		int age = Integer.parseInt(request.getParameter("age")); // "47" => 47 | "" => NumberFormatException
		String city = request.getParameter("city"); // "서울" | "경기" 등등
		double height = Double.parseDouble(request.getParameter("height")); // "170" => 170.000~
		
		//체크박스와 같이 복수개의 벨류값들을 추출하고자 할때
		String[] foods = request.getParameterValues("food"); // ["한식", "중식" ~] | null
		
		System.out.println("name : " + name);
		System.out.println("gender : " + gender);
		System.out.println("age : " + age);
		System.out.println("city : " + city);
		System.out.println("height : " + height);
		
		if (foods == null) {
			System.out.println("foods : 없음");
		} else {
			System.out.println("foods : " + String.join("/", foods));
			// ["한식","중식"] => "한식/중식"
		}
		
		// 추출한 값들을 가지고 요청처리를 해야한다(db와 상호작용)
		// > service메소드 호출 > dao메소드 호출 > db sql문 실행
		
		//요청처리가 다 되었다는 가정하에 사용자가 보게될 응답 html을 작성
		// 순수 Servlet방식 : Java코드 내에 html을 기술
		
		// JSP(Java Server Page) 방식 : html 내에 Java코드를 쓸 수 있음.
		
		//응답페이지를 만드는 과정을 JSP에게 위임(떠넘긴다)
		//단, 응답화면(jsp)에서 필요로하는 데이터들을 차곡차곡담아서 전달해줘야한다.
		//차곡차곡 담기위한 공간==request의 attribute영역
		// request.setAttribute("키", 벨류);
		
		request.setAttribute("name", name);
		request.setAttribute("age", age);
		request.setAttribute("city", city);
		request.setAttribute("height", height);
		request.setAttribute("gender", gender);
		request.setAttribute("foods", foods);
		
		//응답하고자하는 뷰(jsp) 선택하면서 RequestDispatcher객체생성
		RequestDispatcher view = request.getRequestDispatcher("/views/responsePage.jsp");
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post 호출완료");
		doGet(request, response);
	}

}
