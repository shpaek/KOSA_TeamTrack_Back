package com.my.qna.service;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.qna.dao.QnaBoardDAO;
import com.my.qna.dao.QnaBoardDAOImpl;
import com.my.qna.dto.QnaBoardDTO;
import com.my.task.dto.TaskDTO;
import com.my.util.PageGroup;

public class QnaBoardServiceImpl implements QnaBoardService {

	private QnaBoardDAO qnaBoardDAO;
	
	private static QnaBoardServiceImpl  service = new QnaBoardServiceImpl();
	
	private QnaBoardServiceImpl() {
		qnaBoardDAO = new QnaBoardDAOImpl();
	}
	
	public static QnaBoardService getInstance() {
		return service;
	}

	@Override
	public void create(QnaBoardDTO qnaBoardDTO) throws AddException {
		
		qnaBoardDAO.create(qnaBoardDTO);

	} // create

	@Override
	public PageGroup<QnaBoardDTO> selectAll(Integer teamNo, int currentPage) throws FindException {
		
		if(currentPage <1) {
			currentPage = 1;
		}
		
		int cntPerPage = 10; //한페이지당 보여줄 목록 수
		
		int startRow = 0;
		int endRow = 0;
		
		startRow = (currentPage -1)*cntPerPage +1;
		endRow = currentPage*cntPerPage;
		
		List<QnaBoardDTO> list = qnaBoardDAO.selectAll(startRow, endRow, teamNo);
		
		int totalCnt = qnaBoardDAO.selectAllCount(teamNo);
		
		PageGroup<QnaBoardDTO> pg = new PageGroup<>(list, currentPage, totalCnt); 
		return pg;

	} // selectAll

	@Override
	public Integer update(QnaBoardDTO qnaBoardDTO) throws ModifyException {

		qnaBoardDAO.update(qnaBoardDTO);
		
		return null;
	} // update

	@Override
	public Integer delete(Integer teamNo, Integer qnaNo) throws ModifyException {

		qnaBoardDAO.delete(teamNo, qnaNo);

		return null;
	} // delete

} // end class
