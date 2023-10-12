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

public class TeamReqApproveController extends TeamController {

    // 가입 요청 승인용 컨트롤러
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

            Map<String, Object> map = new HashMap<>();
            
            map.put("teamNo", teamNo);
            map.put("id", id);

            service.approveRequest(map);

            responseMap.put("status", 1);
            responseMap.put("msg", "가입 요청 승인 성공");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            responseMap.put("status", 0);
            responseMap.put("msg", "잘못된 팀 번호 형식");
        } catch (ModifyException e) {
            e.printStackTrace();
            responseMap.put("status", 0);
            responseMap.put("msg", "가입 요청 승인 실패");
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("status", 0);
            responseMap.put("msg", "가입 요청 승인 실패");
        } // try-catch

        // JSON 문자열 응답
        String jsonStr = mapper.writeValueAsString(responseMap);
        out.print(jsonStr);

        return null;

    } // execute()

} // end class
