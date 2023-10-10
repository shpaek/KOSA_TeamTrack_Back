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
import com.my.task.dto.TaskDTO;

public class ViewTaskController extends TaskController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
//		HttpSession session=request.getSession();
		
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
//		Integer teamNo=Integer.parseInt(request.getParameter("teamNo"));
		Integer taskNo=Integer.parseInt(request.getParameter("taskNo"));
		System.out.println(taskNo);
//		String loginedId=(String)session.getAttribute("loginedId");
		
		Integer teamNo=9999;
//		Integer taskNo=1;
//		String loginedId="nwh2023";
		Map<String, Object> map=new HashMap<>();
		
		try {
			TaskDTO taskinfo=service.findTaskInfo(teamNo, taskNo);
			List<Integer> answer=service.findQuizAnswer(teamNo, taskNo);
			if(taskinfo==null) {
				map.put("status", 0);
				map.put("msg", "과제를 찾을 수 없습니다.");
			} else {
				map.put("status", 1);
				map.put("title", taskinfo.getTitle());
				map.put("nickname", taskinfo.getNickname());
			}
			if(answer==null) {
				map.put("status", 0);
				map.put("msg", "답이 존재하지 않습니다.");
			} else {
				map.put("list", answer);
			}
		} catch (FindException e) {
			// e.printStackTrace();
			map.put("status", 0);
			map.put("msg", e.getMessage());
		} finally {
			String jsonStr=mapper.writeValueAsString(map);
			out.print(jsonStr);
		}
		
		
		return null;
	}

}
