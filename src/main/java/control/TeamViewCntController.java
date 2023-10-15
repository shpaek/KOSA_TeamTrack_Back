package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.exception.ModifyException;

@WebServlet("/teamMain")
public class TeamViewCntController extends TeamController {

	// 셍나
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* 팀 페이지 들어갈 때마다 매번 조회수 증가 */
	    int teamNo = Integer.parseInt(request.getParameter("teamNo"));

	    try {
	        service.updateViewCnt(teamNo);
	    } catch (ModifyException e) {
	        e.printStackTrace();
	    } // try-catch

		return null;

	} // execute()

} // end class