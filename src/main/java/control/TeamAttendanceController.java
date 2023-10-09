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
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.team.dto.AttendanceDTO;

public class TeamAttendanceController extends TeamController {

	// 팀 출석부용 컨트롤러
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");

		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();

		Map<String, Object> map = new HashMap<>();

		try {
			String method = request.getMethod();

			// 출석하기
			if ("POST".equals(method)) {

				String id = (String) request.getSession().getAttribute("loginedId");
				Integer teamNo = Integer.parseInt(request.getParameter("teamNo"));

				service.insertAttendanceById(teamNo, id);

				map.put("status", 1);
				map.put("msg", "출석 성공");
				
			} else if ("GET".equals(method)) {
				// 출석 내역 조회하기
				String id = (String) request.getSession().getAttribute("loginedId");
				Integer teamNo = Integer.parseInt(request.getParameter("teamNo"));

				List<AttendanceDTO> attendanceList = service.selectAttendanceById(teamNo, id);

				map.put("status", 1);
				map.put("msg", "출석 내역 조회 성공");
			} // else-if
			
		} catch (FindException e) {
			e.printStackTrace();
			
			map.put("status", 0);
			map.put("msg", "출석 실패");
		} catch (AddException e) {
			e.printStackTrace();
			
			map.put("status", 0);
			map.put("msg", "출석 실패");
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("status", 0);
			map.put("msg", "출석 실패");
		} // try-catch

		// JSON문자열 응답
		String jsonStr = mapper.writeValueAsString(map);
		out.print(jsonStr);
		
		return null;

	} // execute()

} // end class
