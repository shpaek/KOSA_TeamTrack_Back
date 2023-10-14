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

public class TeamNameDupChkController extends TeamController {


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();

		String teamName = request.getParameter("teamName");
		Map<String, Integer> map = new HashMap<>();
		try {
			int teamNo = service.teamNameDupChk(teamName);
			//팀이 있는 경우
			map.put("status", 0);
			map.put("teamNo", teamNo);
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
