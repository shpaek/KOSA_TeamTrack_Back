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

public class ReviewTaskController extends TaskController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map=new HashMap<>();
		Integer teamNo=9999;
		Integer taskNo=Integer.parseInt(request.getParameter("taskNo"));
		int reviewScore=Integer.parseInt(request.getParameter("reviewScore"));
		
		try {
			service.setReviewScore(teamNo, taskNo, "nwh2023", reviewScore);
			map.put("status", 1);
			map.put("msg", "평가 완료!");
		} catch (ModifyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "평가 실패");
		}
		
		out.print(mapper.writeValueAsString(map));
		return null;
	}

}
