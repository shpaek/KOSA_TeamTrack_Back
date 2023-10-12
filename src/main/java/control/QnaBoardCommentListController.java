package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.qna.dto.QnaBoardCommentDTO;

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
		
		// 메인에서 실행하는 모든 서비스 메소드들의 결과값을 map에 넣어서 리턴하기
		Map<String, Object> methodMap = new HashMap<>();
		
		// 요청 전달데이터 얻기
//		String currentPage = req.getParameter("currentPage");
		Integer teamNo = Integer.parseInt(req.getParameter("teamNo"));
		Integer qnaNo = Integer.parseInt(req.getParameter("qnaNo"));
		
		// 추가 데이터 reply용
//		Integer commentNo = Integer.parseInt(req.getParameter("commentNo"));
//		Integer commentGroup = Integer.parseInt(req.getParameter("commentGroup"));

		System.out.println("comment teamNo ===================> " + teamNo);
		System.out.println("comment qnaNo ===================> " + qnaNo);
//		System.out.println("Reply commentGroup ===============> " + commentGroup);
		
//		String teamNoStr = req.getParameter("teamNo");
//		if (teamNoStr != null && !teamNoStr.isEmpty()) {
//			try {
//				teamNo = Integer.parseInt(teamNoStr);
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//
//				return null;
//			}
//		}
//		String qnaNoStr = req.getParameter("qnaNo");
//		if (teamNoStr != null && !teamNoStr.isEmpty()) {
//			try {
//				teamNo = Integer.parseInt(teamNoStr);
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//
//				return null;
//			}
//		}
//		String commentNoStr = req.getParameter("commentNo");
//		if (teamNoStr != null && !teamNoStr.isEmpty()) {
//			try {
//				teamNo = Integer.parseInt(teamNoStr);
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//
//				return null;
//			}
//		}
//		String commentGroupStr = req.getParameter("commentGroup");
//		if (teamNoStr != null && !teamNoStr.isEmpty()) {
//			try {
//				teamNo = Integer.parseInt(teamNoStr);
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//
//				return null;
//			}
//		}
		
//	    QnaBoardCommentDTO dto = new QnaBoardCommentDTO();
//
//	    dto.setQnaNo(qnaNo);
//	    dto.setCommentNo(commentNo);
//	    dto.setCommentGroup(commentGroup);
		
//		int cp = 1;
		
//		if(currentPage != null && !currentPage.equals("")) {
//			cp = Integer.parseInt(currentPage);
//		} // if
		
		try {
			
//			PageGroup<QnaBoardCommentDTO> pg = commentService.selectCommentByQnaNo(teamNo, qnaNo, cp);
			List<QnaBoardCommentDTO> list = commentService.selectCommentByQnaNo(teamNo, qnaNo);
//			methodMap.put("pg", pg);
//			
//			List<QnaBoardCommentDTO> selectCommentReply = commentService.selectCommentReply(teamNo, dto);
//			methodMap.put("reply", selectCommentReply);
			
//			String jsonStr = mapper.writeValueAsString(pg);
			
//			String jsonStr = mapper.writeValueAsString(list);
			Map<String,Object> map = new HashMap<>();
			map.put("list", list);
			map.put("status", 1);
			String jsonStr = mapper.writeValueAsString(map);
			out.print(jsonStr);
			
			System.out.println("==========> jsonStr 출력" + jsonStr);
			
		} catch (FindException e) {
			e.printStackTrace();	
			Map<String,Object> map = new HashMap<>();
			map.put("msg", "댓글조회 실패");
			map.put("status", 0);
			String jsonStr = mapper.writeValueAsString(map);
			out.print(jsonStr);
		} // try-catch
		
		return null;
		
	} // execute

} // end class
