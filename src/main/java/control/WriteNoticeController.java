package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.notice.dto.NoticeDTO;
import com.my.util.Attach;

public class WriteNoticeController extends NoticeController {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
		
		HttpSession session = request.getSession();
		String loginedId = (String)session.getAttribute("loginedId");
		
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<>();
		
		Integer teamNo = Integer.parseInt(request.getParameter("teamNo"));
		Date regDate = Date.from(Instant.now());
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Attach attach=new Attach(request);
			String noticeTitle=attach.getParameter("noticeTitle");
			String noticeContent=attach.getParameter("noticeContent");
			Integer mainStatus = Integer.parseInt(attach.getParameter("mainStatus"));
			NoticeDTO notice = new NoticeDTO(noticeTitle, regDate, noticeContent, mainStatus);
			service.writeNotice(teamNo, notice);
			try {
				String originFileName=attach.getFile("f1").get(0).getName();
				attach.upload("f1", loginedId+"_notice_"+originFileName);
			} catch(Exception e) {
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", e.getMessage());
		}

		return null;
	}
}
