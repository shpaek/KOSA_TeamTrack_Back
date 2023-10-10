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

public class TeamMainController extends TeamController {

//	팀 메인용 컨트롤러
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");

		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> map = new HashMap<>();

		// 메인에서 실행하는 모든 서비스 메소드들의 결과값을 map에 넣어서 리턴하기
		
		String jsonStr = mapper.writeValueAsString(map);
		out.print(jsonStr);

		return null;
	} // execute()

} // end class
