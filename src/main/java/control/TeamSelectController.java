package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.team.dto.TeamDTO;

public class TeamSelectController extends TeamController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
//		HttpSession session = request.getSession();
		int teamNo = Integer.parseInt(request.getParameter("teamNo"));
		Map<String, Integer> map = new HashMap<>();
		try {
			service.selectByTeamNo(teamNo);
			System.out.println(service.selectByTeamNo(teamNo));
			//팀이 있는 경우
			map.put("status", 0);
		} catch (FindException e) {
			//팀이 없는 경우
			e.printStackTrace();
			map.put("status", 1);
		}
		System.out.println(map);
		out.print(mapper.writeValueAsString(map));
		return null;
	}
}

