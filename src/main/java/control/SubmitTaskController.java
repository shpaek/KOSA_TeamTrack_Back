package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.AddException;

public class SubmitTaskController extends TaskController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		//HttpSession session=request.getSession();
		
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map=new HashMap<>();
		
		Integer teamNo=9999;
		Integer taskNo=Integer.parseInt(request.getParameter("taskNo"));
//		String id="khb2023";
		String id="nwh2023";
		String answer=request.getParameter("answerlist");
		
		try {
			service.addMemberAnswer(teamNo, taskNo, id, answer);
			//과제 채점 메소드 추가하기
			
			map.put("status", 1);
			map.put("msg", "과제 제출에 성공하였습니다");
		} catch (AddException e) {
			//e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "과제 제출에 실패하였습니다");
		}
		
		return null;
	}

}
