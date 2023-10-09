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
import com.my.exception.FindException;
import com.my.notice.dto.NoticeDTO;
import com.my.util.Attach;

public class EditNoticeController extends NoticeController{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		HttpSession session = request.getSession();
		String loginedId = (String)session.getAttribute("loginedId");
		
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> map = new HashMap<>();; 
		
		try {
			Attach attach=new Attach(request);
			Integer teamNo = Integer.parseInt(attach.getParameter("teamNo"));
			Integer noticeNo = Integer.parseInt(attach.getParameter("noticeNo"));
			String noticeTitle = attach.getParameter("title");
			String noticeContent=attach.getParameter("content");
			Integer mainStatus = 0;
			if(attach.getParameter("status") != null) {
				mainStatus = 1;
			}
			NoticeDTO notice = new NoticeDTO(noticeNo, noticeTitle, noticeContent, mainStatus);
			service.modifyNotice(teamNo, notice);
			try {
				String originFileName=attach.getFile("f1").get(0).getName();
				attach.upload("f1", loginedId+"_notice_"+originFileName);
			} catch(Exception e) {
			
			}
			map.put("status", 1);
			map.put("msg", "게시글이 수정되었습니다");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", e.getMessage());
		}
		
		out.print(mapper.writeValueAsString(map));

		return null;
	}
}
