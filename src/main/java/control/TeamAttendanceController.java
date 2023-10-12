package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.team.dto.AttendanceDTO;

public class TeamAttendanceController extends TeamController {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String method = request.getMethod();

        if ("GET".equalsIgnoreCase(method)) {
            return doGet(request, response);
        } else if ("POST".equalsIgnoreCase(method)) {
            return doPost(request, response);
        } else {
        	return null;
        } // else-if
    } // execute()

//  출석 내역 조회
    private String doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<>();

        try {
//            String id = (String) request.getSession().getAttribute("loginedId");
        	String id = request.getParameter("id");
            Integer teamNo = Integer.parseInt(request.getParameter("teamNo"));

            List<AttendanceDTO> attendanceList = service.selectAttendanceById(teamNo, id);

            map.put("status", 1);
            map.put("msg", "출석 내역 조회 성공");
            map.put("attendanceList", attendanceList);

        } catch (FindException e) {
            e.printStackTrace();
            map.put("status", 0);
            map.put("msg", "출석 내역 조회 실패");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
            map.put("msg", "출석 내역 조회 실패");
        } // try-catch

        String jsonStr = mapper.writeValueAsString(map);
        out.print(jsonStr);

        return null;
    } // 출석 내역 조회()

//  출석하기
    private String doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<>();

        try {
//            String id = (String) request.getSession().getAttribute("loginedId");
        	String id = request.getParameter("id");
            Integer teamNo = Integer.parseInt(request.getParameter("teamNo"));

            service.insertAttendanceById(teamNo, id);

            map.put("status", 1);
            map.put("msg", "출석 성공");

        } catch (AddException e) {
            e.printStackTrace();
            map.put("status", 0);
            map.put("msg", "출석 실패");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
            map.put("msg", "출석 실패");
        } // try-catch

        String jsonStr = mapper.writeValueAsString(map);
        out.print(jsonStr);

        return null;
    } // 출석하기()
    
} // end class
