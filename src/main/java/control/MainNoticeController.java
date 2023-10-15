package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.notice.dto.NoticeDTO;
import com.my.team.service.TeamServiceImpl;


public class MainNoticeController extends NoticeController{
	TeamServiceImpl teamService = TeamServiceImpl.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");

		
		HttpSession session = request.getSession();
		String loginedId = (String)session.getAttribute("loginedId");
		//String loginedId = "nwh2023";
		
		
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		Map map = new HashMap<>();
		Integer memStatus = 0;
		
		Integer teamNo = Integer.parseInt(request.getParameter("teamNo"));
		
		try {
			memStatus = teamService.leaderChk(loginedId, teamNo);
			NoticeDTO notice = service.findMainNotice(teamNo);
			
			map.put("memStatus", memStatus);
			map.put("notice", notice);
			String jsonStr = mapper.writeValueAsString(map);
			out.print(jsonStr);
		} catch (FindException e) {
			e.printStackTrace();
		}

		return null;
	}
}
