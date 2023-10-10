package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ChkTaskIdController extends TaskController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		HttpSession session=request.getSession();

		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		Integer teamNo=Integer.parseInt(request.getParameter("teamNo"));
		String loginedId=(String)session.getAttribute("loginedId");
		
		return null;
	}

}
