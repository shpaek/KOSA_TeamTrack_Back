package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.customer.service.CustomerService;
import com.my.customer.service.CustomerServiceImpl;
import com.my.notice.service.NoticeService;
import com.my.notice.service.NoticeServiceImpl;

public abstract class NoticeController implements Controller {

	protected NoticeService service;
	
	public NoticeController(){
		service = NoticeServiceImpl.getInstance();
	}
}
