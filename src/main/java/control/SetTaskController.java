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
import com.my.exception.ModifyException;

public class SetTaskController extends TaskController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
//		HttpSession session=request.getSession();

		//Integer teamNo=Integer.parseInt(request.getParameter("teamNo"));
//		String loginedId=(String)session.getAttribute("loginedId");
		String title=request.getParameter("title");
		String titletest=title.trim();
		
		String answer=request.getParameter("answerList");
		
		Map<String, Object> map=new HashMap<>();
		
		if(titletest.isEmpty()) {
			map.put("status", 0);
			map.put("msg", "타이틀을 올바르게 입력하세요");
			out.print(mapper.writeValueAsString(map));
			return null;
		}
		if(answer.isEmpty()) {
			map.put("status", 0);
			map.put("msg", "답안을 올바르게 입력하세요");
			out.print(mapper.writeValueAsString(map));
			return null;
		}
		System.out.println(answer.isEmpty());
		
		Integer teamNo=9999;
		Integer taskNo=Integer.parseInt(request.getParameter("taskNo"));
		
		
		
		try {
			service.ModifyTask(teamNo, title, taskNo);
			service.AddQuizAnswer(teamNo, taskNo, answer);
			title=title.trim();
			
			map.put("status", 1);
			map.put("msg", "출제에 성공하였습니다");
		} catch (ModifyException e) {
			//out.print(e.getMessage());
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "출제에 실패하였습니다");
		} catch (AddException e) {
			//out.print(e.getMessage());
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "출제에 실패하였습니다");
		}
		
		System.out.println(taskNo+":"+title);
		out.print(mapper.writeValueAsString(map));
		return null;
	}


}
