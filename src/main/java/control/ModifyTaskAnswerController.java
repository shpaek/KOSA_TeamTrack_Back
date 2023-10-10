package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.exception.ModifyException;

public class ModifyTaskAnswerController extends TaskController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		//Integer teamNo=Integer.parseInt(request.getParameter("teamNo"));
		Integer questionNo=Integer.parseInt(request.getParameter("questionNo"));
		//Integer taskNo=Integer.parseInt(request.getParameter("teamNo"));
		int answer=Integer.parseInt(request.getParameter("answer"));

		Integer teamNo=9999;
		Integer taskNo=11;

		try {
			service.ModifyQuizAnswer(teamNo, questionNo, taskNo, answer);
		} catch (ModifyException e) {
			out.print(e.getMessage());
		}

		return null;
	}

}
