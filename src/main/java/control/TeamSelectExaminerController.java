package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.task.dto.TaskDTO;

public class TeamSelectExaminerController extends TeamController {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");

        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  // 사용하려는 날짜 형식에 따라 변경 가능

        Map<String, Object> statusMap = new HashMap<>();
        Map<String, Object> methodMap = new HashMap<>();
        
        
        int teamNo = Integer.parseInt(request.getParameter("teamNo"));
        String id = "psh2023";
        try {
        	TaskDTO taskDTO = new TaskDTO();
        	
        	Date formatDueDate1 = formatter.parse(request.getParameter("duedate1"));
            Date formatDueDate2 = formatter.parse(request.getParameter("duedate2"));
            Date formatEndDate = formatter.parse(request.getParameter("enddate"));
            
//            taskDTO.setId(request.getParameter("id"));
            taskDTO.setId(id);
            taskDTO.setDuedate1(formatDueDate1);
            taskDTO.setDuedate2(formatDueDate2);
            taskDTO.setEnddate(formatEndDate);
            
            System.out.println(taskDTO.getDuedate1());
            
            // 출제자 선정을 위한 팀원 목록 보여줌
            List<Map<String, Object>> teamInfo = service.selectMemberInfo(teamNo);
            methodMap.put("teamInfo", teamInfo);

            // 출제자 선정
            service.insertExaminer(taskDTO, teamNo);
            
            statusMap.put("status", 1);
            statusMap.put("msg", "출제자 선정 성공");
            
        } catch (FindException e) {
        	e.printStackTrace();
        	statusMap.put("status", 0);
        	statusMap.put("msg", "팀원 목록 조회 실패: " + e.getMessage());
        } catch (ModifyException e) {
            e.printStackTrace();
            statusMap.put("status", 0);
            statusMap.put("msg", "출제자 선정 실패: " + e.getMessage());
        } catch (ParseException e) {
        	e.printStackTrace();
        	statusMap.put("status", 0);
        	statusMap.put("msg", "date 타입 파싱 실패: " + e.getMessage());
		}

        // JSON문자열 응답
        String jsonStr = mapper.writeValueAsString(methodMap);
        out.print(jsonStr);

        return null;
    }
}
