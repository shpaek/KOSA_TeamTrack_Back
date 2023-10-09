package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.rank.dto.RankDTO;

public class RankListController extends RankController {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.13:5500"); //http://127.0.0.1:5500
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		//응답 출력 스트림 얻기
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		Integer teamNo = Integer.parseInt(request.getParameter("teamNo"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date nowdate = Date.from(Instant.now());
		String rankDate = formatter.format(nowdate);
		//수정//
		//Integer month = 0;
		
		//List ranklist = new ArrayList<>();		
		Map<String, Object> ranks = new HashMap<>();
		try {
			List<RankDTO> list = service.findByMonth(teamNo, rankDate);
			ranks.put("list", list);
		} catch (FindException e) {
				e.printStackTrace();
		}
		
		String jsonStr = mapper.writeValueAsString(ranks);
		out.print(jsonStr);
		return null;
	}
}
