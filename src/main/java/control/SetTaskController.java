package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.exception.ModifyException;

public class SetTaskController extends TaskController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//Integer teamNo=Integer.parseInt(request.getParameter("teamNo"));
		String title=request.getParameter("title");
		String enddate=request.getParameter("enddate");
		//Integer taskNo=Integer.parseInt(request.getParameter("teamNo"));
		
		Integer teamNo=9999;
		Integer taskNo=11;
		
		try {
			service.ModifyTask(teamNo, title, enddate, taskNo);
		} catch (ModifyException e) {
			out.print(e.getMessage());
		}
		
		return null;
	}

}
