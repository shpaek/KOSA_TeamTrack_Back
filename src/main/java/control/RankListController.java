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
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.rank.dto.RankDTO;
import com.my.team.dto.TeamMemberDTO;
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
		
		//개인 랭킹을 실시간으로 계산하여 반환해준다
		List<Map<String, Object>> ranklist = new ArrayList<>();		
		Map<String, Object> map=new HashMap<>();
		
		try {
			List<RankDTO> list = service.findByMonth(teamNo, month);
			System.out.println(list);
			Map<String, Object> scoremap = service.calculateTotalScore(teamNo, rankDate, month);
			System.out.println(scoremap);
			
			// calculate 점수 -> 데이터 전달하기 
			Map<String, Object> rankmap = new LinkedHashMap();
			List<RankDTO> dtolist = new ArrayList();

			for (RankDTO dto : list) {
				dtolist.add(dto);
				
				// service에서 점수 계산하여 set
				for (String key : scoremap.keySet()) {
					if (key.equals(dto.getId())) {
						dto.setTotalScore((Double)scoremap.get(key));
					}
				}
				Collections.sort(dtolist, new ValueComparator());
			}
			
			// Rank 순위를 TotalScore 기준으로 새롭게 부여
			int currrank = 1;
			for (int i = 0; i < dtolist.size(); i++) {
				if (i>0 && (dtolist.get(i).getTotalScore() < dtolist.get(i-1).getTotalScore())) {
					currrank++; //total score 이전보다 작으면 rank 하나 증가
					dtolist.get(i).setRank(currrank);
				} else {		//이외의 경우 rank 유지
					dtolist.get(i).setRank(currrank);
				}
				rankmap.put(dtolist.get(i).getId(), dtolist.get(i));
			}
			ranklist.add(rankmap);
			System.out.println("ranklist" + ranklist);
			
			// 정보가 없으면 새로 추가해주고, 기존에 있는 멤버에는 업데이트한 정보를 Rank DB에 저장한다
			Integer rank = null;
			Double totalScore = null;
			String id = null;
			Integer rankmonth = null;
			List<TeamMemberDTO> tmlist = service.findMemberId(teamNo, month);
			
			for (RankDTO dto : dtolist) {
				rank = dto.getRank();
				totalScore = dto.getTotalScore();
				id = dto.getId();
				rankmonth = dto.getMonth();
				
				for (TeamMemberDTO tmdto : tmlist) {
					String memberid = tmdto.getId();
					Integer newmonth = tmdto.getMonth();
					
					try {
						if ((!memberid.equals(id)) && (!newmonth.equals(rankmonth))) {
							service.addRankInfo(teamNo, memberid);
							service.modifyRankInfo(teamNo, rankDate, rank, totalScore, id, month);
							System.out.println("정보 추가 성공!!!!!! ");
						} else {
							service.modifyRankInfo(teamNo, rankDate, rank, totalScore, id, month);
							System.out.println("정보 업데이트 성공!!!!!! ");
						}
					} catch (AddException e){
						e.printStackTrace();
						map.put("status", 0);
						map.put("msg", "랭킹 정보 추가에 실패하였습니다");
						String jsonStr = mapper.writeValueAsString(map);
					}
				}
			}
			System.out.println("modify 성공");
			String jsonStr = mapper.writeValueAsString(ranklist);
			out.print(jsonStr);
			
		} catch (FindException e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "랭킹 정보 조회에 실패하였습니다");
			String jsonStr = mapper.writeValueAsString(map);
			out.print(jsonStr);
		} catch (ModifyException e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "랭킹 정보 업데이트에 실패하였습니다");
			String jsonStr = mapper.writeValueAsString(map);
			out.print(jsonStr);
		}
		
		return null;
	}
}
