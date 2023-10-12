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

/* 서현 웅니
@Override
public String execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("mvo") == null) {
			System.out.println("비인증");
		}
		int teamNo = Integer.parseInt(request.getParameter("teamNo"));

		Cookie oldCookie = null;
		Calendar expireDay = Calendar.getInstance();
		expireDay.add(Calendar.DAY_OF_MONTH, 1);
		expireDay.set(Calendar.HOUR_OF_DAY, 0);
		expireDay.set(Calendar.MINUTE, 0);
		expireDay.set(Calendar.SECOND, 0);
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("boardView")) {
					oldCookie = cookie;
				}
			}
		}
		if (oldCookie != null) {
			if (!oldCookie.getValue().contains(String.valueOf(teamNo) + "/")) {
				try {
					service.updateViewCnt(teamNo);
					oldCookie.setValue(oldCookie.getValue() + teamNo + "/");
					oldCookie.setPath("/");
					oldCookie.setMaxAge(
							((int) (expireDay.getTimeInMillis() - System.currentTimeMillis())) / 1000 + 9 * 60 * 60);
					response.addCookie(oldCookie);
				} catch (ModifyException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				service.updateViewCnt(teamNo);
				Cookie newCookie = new Cookie("boardView", String.valueOf(teamNo) + "/");
				newCookie.setPath("/");
				newCookie
				.setMaxAge(((int) (expireDay.getTimeInMillis() - System.currentTimeMillis())) / 1000 + 9 * 60 * 60);
				response.addCookie(newCookie);
			} catch (ModifyException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
 */
