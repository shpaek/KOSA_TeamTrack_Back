package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.AddException;
import com.my.team.dto.SignupTeamDTO;

public class TeamJoinController extends TeamController {

//	팀 가입용 컨트롤러
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");

		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> map = new HashMap<>();
		
		try {

			SignupTeamDTO signupTeamDTO = new SignupTeamDTO();

            signupTeamDTO.setTeamNo(Integer.parseInt(request.getParameter("teamNo")));
            signupTeamDTO.setId(request.getParameter("id"));
            signupTeamDTO.setStatus(Integer.parseInt(request.getParameter("status")));
            signupTeamDTO.setIntroduction(request.getParameter("introduction"));

			service.insertSignUpTeam(signupTeamDTO);

			map.put("status", 1);
			map.put("msg", "팀 가입 요청 성공");

		} catch (AddException e) {
			e.printStackTrace();
			
			map.put("status", 0);
			map.put("msg", "팀 가입요청 실패");
		} // try-catch

		// JSON문자열 응답
		String jsonStr = mapper.writeValueAsString(map);
		out.print(jsonStr);

		return null;

	} // execute()

} // end class
