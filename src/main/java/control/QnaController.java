package control;

import com.my.qna.service.QnaBoardService;
import com.my.qna.service.QnaBoardServiceImpl;

public abstract class QnaController implements Controller {
	
	protected QnaBoardService service;

	public QnaController() {
		service = QnaBoardServiceImpl.getInstance();
	} //constructor
	
} // end class
