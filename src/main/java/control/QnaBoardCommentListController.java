	package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.qna.dto.QnaBoardCommentDTO;
import com.my.util.PageGroup;

public class QnaBoardCommentListController extends QnaController {

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
		
		// 요청 전달데이터 얻기
		String currentPage = req.getParameter("currentPage");
		Integer teamNo = Integer.parseInt(req.getParameter("teamNo"));
		Integer qnaNo = Integer.parseInt(req.getParameter("qnaNo"));

		System.out.println("comment teamNo ===================> " + teamNo);
		System.out.println("comment qnaNo ===================> " + qnaNo);
		
		int cp = 1;
		
		if(currentPage != null && !currentPage.equals("")) {
			cp = Integer.parseInt(currentPage);
		} // if
		
		try {
			
			PageGroup<QnaBoardCommentDTO> pg = commentService.selectCommentByQnaNo(teamNo, qnaNo, cp);
			
			String jsonStr = mapper.writeValueAsString(pg);
			out.print(jsonStr);
			
			System.out.println("==========> jsonStr 출력" + jsonStr);
			
		} catch (FindException e) {
			e.printStackTrace();	
		} // try-catch
		
		return null;
		
	} // execute

} // end class
