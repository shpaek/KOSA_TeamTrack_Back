package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
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

        Map<String, Object> map = new HashMap<>();
        
        try {
        	TaskDTO taskDTO = new TaskDTO();
        	
        	Date formatDueDate1 = formatter.parse(request.getParameter("DUEDATE1"));
            Date formatDueDate2 = formatter.parse(request.getParameter("DUEDATE2"));
            Date formatEndDate = formatter.parse(request.getParameter("ENDDATE"));
            
            taskDTO.setTaskNo(Integer.parseInt(request.getParameter("teamNo")));
            taskDTO.setId(request.getParameter("id"));
            taskDTO.setDueDate1(formatDueDate1);
            taskDTO.setDueDate2(formatDueDate2);
            taskDTO.setEnddate(formatEndDate);

            service.insertExaminer(taskDTO);
            
            map.put("status", 1);
            map.put("msg", "출제자 선정 성공");
        } catch (ModifyException e) {
            e.printStackTrace();
            map.put("status", 0);
            map.put("msg", "출제자 선정 실패: " + e.getMessage());
        } catch (ParseException e) {
        	e.printStackTrace();
        	map.put("status", 0);
        	map.put("msg", "date 타입 파싱 실패: " + e.getMessage());
		}

        // JSON문자열 응답
        String jsonStr = mapper.writeValueAsString(map);
        out.print(jsonStr);

        return null;
    }
}
