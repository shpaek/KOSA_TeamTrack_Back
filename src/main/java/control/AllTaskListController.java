package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.task.dto.TaskDTO;
import com.my.util.PageGroup;

public class AllTaskListController extends TaskController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
//		Integer teamNo=Integer.parseInt(request.getParameter("teamNo"));
//		String currentPage = request.getParameter("currentPage");
//		String option=request.getParameter("option");
		boolean desc=true;
//		if(!option.equals("최신순")) desc=false;
		
		Integer teamNo=9999;
		String currentPage="1";
		
		int cp = 1;
		if (currentPage != null && !currentPage.equals("")) {
			cp = Integer.parseInt(currentPage);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			PageGroup<TaskDTO> pg = service.findAllTaskList(teamNo, cp, desc);
			String jsonStr = mapper.writeValueAsString(pg);
			out.print(jsonStr);
		} catch (FindException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
