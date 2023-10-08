package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.notice.dto.NoticeDTO;

public class MainNoticeController extends NoticeController{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
		
		PrintWriter out = response.getWriter();
		
		Integer teamNo = Integer.parseInt(request.getParameter("teamNo"));
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			NoticeDTO notice = service.findMainNotice(teamNo);
			String jsonStr = mapper.writeValueAsString(notice);
			out.print(jsonStr);
		} catch (FindException e) {
			e.printStackTrace();
		}

		return null;
	}
}
