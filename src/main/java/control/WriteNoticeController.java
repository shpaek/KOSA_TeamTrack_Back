package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.notice.dto.NoticeDTO;
import com.my.util.Attach;

public class WriteNoticeController extends NoticeController {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");


		HttpSession session = request.getSession();
		String loginedId = (String)session.getAttribute("loginedId");

		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();

		Map<String, Object> map = new HashMap<>();
		Date regDate = Date.from(Instant.now());

		try {
			Attach attach=new Attach(request);
			Integer teamNo = Integer.parseInt(attach.getParameter("teamNo"));

			String noticeTitle=attach.getParameter("title");
			String noticeContent=attach.getParameter("content");
			Integer mainStatus = 0;
			if(attach.getParameter("status") != null) {
				mainStatus = 1;
			}
			NoticeDTO notice = new NoticeDTO(noticeTitle, regDate, noticeContent, mainStatus);
			service.writeNotice(teamNo, notice);
			try {
				String originFileName=attach.getFile("f1").get(0).getName();
				attach.upload("f1", loginedId+"_notice_"+originFileName);
			} catch(Exception e) {

			}
			map.put("status", 1);
			map.put("msg", "게시글이 업로드되었습니다");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", e.getMessage());
		}

		out.print(mapper.writeValueAsString(map));

		return null;
	}
}
