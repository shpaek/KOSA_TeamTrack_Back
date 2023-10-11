package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.ModifyException;

public class SetTaskController extends TaskController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
//		HttpSession session=request.getSession();

		//Integer teamNo=Integer.parseInt(request.getParameter("teamNo"));
//		String loginedId=(String)session.getAttribute("loginedId");
		String title=request.getParameter("title");
		String loginedId="cyk2023";
		String answer=request.getParameter("answerList");
		
		Integer teamNo=9999;
		
		Map<String, Object> map=new HashMap<>();
		map.put("status", 0);
		
		try {
			service.ModifyTask(teamNo, title, loginedId);
			service.AddQuizAnswer(teamNo, loginedId, answer);
		} catch (ModifyException e) {
			out.print(e.getMessage());
		}
		out.print("msg :뭔가되긴함");
		
		out.print(mapper.writeValueAsString(map));
		return null;
	}

}
