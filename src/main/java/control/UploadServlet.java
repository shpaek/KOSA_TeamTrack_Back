package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// CORS 문제 해결
		res.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		
		String tempDir = 
		
	} // doPost

} // end class
