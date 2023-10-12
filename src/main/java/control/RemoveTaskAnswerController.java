package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.exception.RemoveException;

public class RemoveTaskAnswerController extends TaskController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//Integer teamNo=Integer.parseInt(request.getParameter("teamNo"));
		Integer questionNo=Integer.parseInt(request.getParameter("questionNo"));
		//Integer taskNo=Integer.parseInt(request.getParameter("teamNo"));
		
		Integer teamNo=9999;
		Integer taskNo=11;
		
		try {
			service.removeQuizAnswer(teamNo, questionNo, taskNo);
		} catch (RemoveException e) {
			out.print(e.getMessage());
		}
		
		return null;
	}

}
