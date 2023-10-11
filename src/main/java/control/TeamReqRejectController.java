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

public class TeamReqRejectController extends TeamController {

    // 가입 요청 거절용 컨트롤러
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");

        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> paramsMap = new HashMap<>();
        Map<String, Object> statusMap = new HashMap<>();  // 응답을 위한 map

        try {
            Integer teamNo = Integer.parseInt(request.getParameter("teamNo"));
            String id = request.getParameter("id");
            
            paramsMap.put("teamNo", teamNo);
            paramsMap.put("id", id);

            service.updateRequestInfoReject(paramsMap);

            statusMap.put("status", 1);
            statusMap.put("msg", "가입 요청 거절 성공");

        } catch (ModifyException e) {
            e.printStackTrace();

            statusMap.put("status", 0);
            statusMap.put("msg", "가입 요청 거절 실패");
        } catch (Exception e) {
            e.printStackTrace();

            statusMap.put("status", 0);
            statusMap.put("msg", "가입 요청 거절 중 에러 발생");
        }

        // JSON문자열 응답
        String jsonStr = mapper.writeValueAsString(statusMap);
        out.print(jsonStr);

        return null;
    } 
}
