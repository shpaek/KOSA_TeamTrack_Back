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
import com.my.team.dto.TeamDTO;

public class TeamFilterController extends TeamController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=UTF-8");

		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();

		String column = request.getParameter("column");

		Map<String, Object> map = new HashMap<>();
		String gubun = request.getParameter("gubun");
		if(gubun == "top3") {
			try {
				List<TeamDTO> list = service.selectTopThreeTeams();
				map.put("status", 1);
				map.put("list", list);
			}catch (FindException e) {
				e.printStackTrace();
				map.put("status", 0);
				map.put("msg", e.getMessage());
			}
		}else {
			try {
				List<TeamDTO> list = service.selectByCondition(column);
				map.put("status", 1);
				map.put("list", list);
			}catch (FindException e) {
				e.printStackTrace();
				map.put("status", 0);
				map.put("msg", e.getMessage());
			}
		}

		out.print(mapper.writeValueAsString(map));
		return null;
	}

}
