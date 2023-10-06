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
import com.my.team.dto.TeamDTO;

public class TeamUpdateController extends TeamController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		int teamNo = (int) session.getAttribute("teamNo");
		
		String teamName = request.getParameter("teamName");
		String studyType = request.getParameter("studyType");
		String onOffLine = request.getParameter("onOffLine");
		int maxMember = Integer.parseInt(request.getParameter("maxMember"));
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		String briefInfo = request.getParameter("briefInfo");
		String teamInfo = request.getParameter("teamInfo");
		
		String hashtag1 = request.getParameter("hashtag1");
		String hashtag2 = request.getParameter("hashtag2");
		String hashtag3 = request.getParameter("hashtag3");
		String hashtag4 = request.getParameter("hashtag4");
		String hashtag5 = request.getParameter("hashtag5");
		
		TeamDTO t = new TeamDTO();
		
		t.setTeamNo(teamNo);
		t.setTeamName(teamName);
		t.setStudyType(studyType);
		t.setOnOffLine(onOffLine);
		t.setMaxMember(maxMember);
		t.setStartDate(startdate);
		t.setEndDate(enddate);
		t.setBriefInfo(briefInfo);
		t.setTeamInfo(teamInfo);
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();

		try {
			service.updateTeam(t);
			service.updateHashtag(hashtag1);
			service.updateHashtag(hashtag2);
			service.updateHashtag(hashtag3);
			service.updateHashtag(hashtag4);
			service.updateHashtag(hashtag5);
			map.put("status", 1);
			map.put("msg", "팀수정 성공");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "팀수정 실패");			
		}
		String jsonStr = mapper.writeValueAsString(map);
		out.print(jsonStr);
		return null;
	}


}
