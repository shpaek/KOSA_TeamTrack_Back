package com.my.task.service;

import java.util.List;

import com.my.exception.FindException;
import com.my.task.dao.TaskDAO;
import com.my.task.dao.TaskDAOImpl;
import com.my.task.dto.Task;
import com.my.util.PageGroup;

public class TaskServiceImpl implements TaskService {

	private TaskDAO taskDAO;
	public TaskServiceImpl() {
		taskDAO=new TaskDAOImpl();
	}

	public PageGroup<Task> findAllTaskList(Integer teamNo, int currentPage, boolean desc) throws FindException {		
		if(currentPage<1) currentPage=1;
		int cntPerPage=10;

		int startRow;
		int endRow;
		endRow=currentPage*cntPerPage;
		startRow=(currentPage-1)*cntPerPage+1;


		List<Task> list = taskDAO.selectAllTaskList(teamNo, startRow, endRow, desc);
		int totalCnt=taskDAO.selectAllTaskCount(teamNo);
		PageGroup<Task> pg=new PageGroup<>(list, currentPage, totalCnt);
		return pg;
	}

}
