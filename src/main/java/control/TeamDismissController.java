package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.ModifyException;

public class TeamDismissController extends TeamController {

    // 팀원 방출용
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");

        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> responseMap = new HashMap<>();

        try {
            Integer teamNo = Integer.parseInt(request.getParameter("teamNo"));
            String id = request.getParameter("id");

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("teamNo", teamNo);
            paramMap.put("id", id);

            // 트랜잭션 메소드
            service.updateTeamMemberStatusDismiss(paramMap);

            responseMap.put("status", 1);
            responseMap.put("msg", "팀원 방출 성공");

        } catch (ModifyException e) {
            e.printStackTrace();
            responseMap.put("status", 0);
            responseMap.put("msg", "팀원 방출 실패: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("status", 0);
            responseMap.put("msg", "팀원 방출 실패");
        }

        // JSON 문자열 응답
        String jsonStr = mapper.writeValueAsString(responseMap);
        out.print(jsonStr);

        return null;

    } // execute()

} // end class
