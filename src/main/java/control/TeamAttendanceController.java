package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.team.dto.AttendanceDTO;

public class TeamAttendanceController extends TeamController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");

		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();

		Map statusMap = new HashMap<>();
		Map methodMap = new HashMap<>();
		Map<String, Object> paramsMap = new HashMap<>();
		Map resultMap = new HashMap<>();

	    int teamNo = Integer.parseInt(request.getParameter("teamNo"));
	    String id = request.getParameter("id");
	    String action = request.getParameter("action");
	    
	    paramsMap.put("teamNo", teamNo);
	    paramsMap.put("id", id);
	    
	    try {
	    	if ("attendChk".equals(action)) {
	    	    String existingDate = service.selectAttendanceDate(paramsMap);
	    	    
	    	    System.out.println("됨ㅇㅇ");
	    	    if (existingDate != null) {
	    	    	statusMap.put("status", 2);
	    	    	System.out.println("1번");
	    	    	statusMap.put("msg", "이미 오늘 출석했습니다.");
	    	    } else {
	    	    	statusMap.put("status", 1);
	    	    	System.out.println("2번");
	    	    	statusMap.put("msg", "오늘 출석 가능합니다.");
	    	    } // else-if
	    	    
	    	} else if ("attend".equals(action)) {
	    		String existingDate = service.selectAttendanceDate(paramsMap);
	    		
	    		if (existingDate != null) {
	    	    	statusMap.put("status", 2);
	    	    	System.out.println("3번");
	    	    	statusMap.put("msg", "이미 오늘 출석했습니다.");
	    	    } else {
	    	        service.insertAttendanceById(teamNo, id);
	    	        statusMap.put("status", 1);
	    	        System.out.println("4번");
	    	        statusMap.put("msg", "팀 출석 성공");
	    	    } // else-if
	    		
	    	} // else-if

	    	// 출석내역확인
	    	List<AttendanceDTO> attendanceList = service.selectAttendanceById(teamNo, id);
	    	methodMap.put("attendanceList", attendanceList);

	    } catch (Exception e) {
	    	e.printStackTrace();
	    	statusMap.put("status", 0);
	    	System.out.println("5번");
	    	statusMap.put("msg", "팀 출석 실패");
	    }
	    
//	    Object status = statusMap.get("status");
	    resultMap.put("statusMap", statusMap);
	    resultMap.put("method", methodMap);
	    
		String jsonStr = mapper.writeValueAsString(resultMap);
		out.print(jsonStr);

		return null;
	} 
}
