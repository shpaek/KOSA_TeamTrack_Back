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
import com.my.notice.dto.NoticeDTO;

public class SetMainNoticeController extends NoticeController {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");

		HttpSession session = request.getSession();
		String loginedId = (String)session.getAttribute("loginedId");

		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();

		Integer teamNo = Integer.parseInt(request.getParameter("teamNo"));
		Integer noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		Integer mainStatus = Integer.parseInt(request.getParameter("mainStatus"));
		Map<String, Object> map = new HashMap<>();

		try {
			if(mainStatus==1) {
				NoticeDTO notice = service.findMainNotice(teamNo);
				if(notice.getNoticeNo()==noticeNo) {
					map.put("status", 0);
					map.put("msg", "이미 메인공지로 등록된 게시글입니다");
				}else {
					map.put("status", 0);
					map.put("msg", "이미 메인공지가 존재합니다\n기존 메인공지를 내린 후 시도해주세요");
				}
				out.print(mapper.writeValueAsString(map));
				return null;
			}
			service.setMainNotice(teamNo, noticeNo, mainStatus);
			map.put("status", 1);
			map.put("msg", "메인공지가 변경되었습니다");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", e.getMessage());
		}

		out.print(mapper.writeValueAsString(map));

		return null;
	}
}
