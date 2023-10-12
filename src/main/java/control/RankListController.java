package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.rank.dto.RankDTO;
import com.my.util.ValueComparator;

public class RankListController extends RankController {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500"); //http://127.0.0.1:5500
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		//응답 출력 스트림 얻기
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		//팀번호
		Integer teamNo = Integer.parseInt(request.getParameter("teamNo"));
		//현재날짜
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date nowdate = Date.from(Instant.now());
		String rankDate = formatter.format(nowdate);
		//현재날짜의 월
		LocalDate now = LocalDate.now();
		Integer month = now.getMonthValue(); 
		
		List ranklist = new ArrayList<>();		
		Map<String, Object> ranks = new LinkedHashMap<>();
		try {
			List<RankDTO> list = service.findByMonth(teamNo, month);
			System.out.println(list);
			Map<String, Object> scoremap = service.calculateTotalScore(teamNo, rankDate, month);
			System.out.println(scoremap);
			
			//calculate 점수 -> 데이터 전달하기 
			Map<String, Object> rankmap = new LinkedHashMap();
			List<RankDTO> dtolist = new ArrayList();

			for (RankDTO dto : list) {
				dtolist.add(dto);
				
				for (String key : scoremap.keySet()) {
					if (key.equals(dto.getId())) {
						dto.setTotalScore((Double)scoremap.get(key));
					}
				}
				Collections.sort(dtolist, new ValueComparator());
				System.out.println(dtolist);
				System.out.println(dtolist.size());
			}
			
			for (int i = 0; i < dtolist.size(); i++) {
				int currrank = 1;
				if (i>0 && dtolist.get(i).getTotalScore() <= dtolist.get(i-1).getTotalScore()) {
//					dtolist.get(i).setRank(0);
//					dtolist.get(i).setRank(i+1);
					currrank++;
				}
				dtolist.get(i).setRank(currrank);
				rankmap.put(dtolist.get(i).getId(), dtolist.get(i));
			}
			
			ranklist.add(rankmap);
			System.out.println("ranklist" + ranklist);	
			
		} catch (FindException e) {
				e.printStackTrace();
		}
		
		String jsonStr = mapper.writeValueAsString(ranklist);
		out.print(jsonStr);
		return null;
	}
}
