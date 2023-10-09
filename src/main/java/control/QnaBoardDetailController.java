package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class QnaBoardDetailController extends QnaController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// CORS 문제 해결
		res.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		
		res.setContentType("application/json;charset=utf-8");
		
		// 응답출력스트림 얻기
		PrintWriter out = res.getWriter();
		
		// jackson 라이브러리에서 제공하는 ObjectMapper 클래스 사용하기
		ObjectMapper mapper = new ObjectMapper(); // JSON 문자열 만드는 API
		
		Integer teamNo = Integer.parseInt(req.getParameter("teamNo"));
		Integer qnaNo = Integer.parseInt(req.getParameter("qnaNo"));
		
		return null;
	} // execute

} // end class
