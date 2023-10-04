package com.my.task.service;

import com.my.exception.FindException;
import com.my.task.dto.Task;
import com.my.util.PageGroup;

public interface TaskService {

	/**
	 * 
	 * @param teamNo
	 * @param currentPage
	 * @return
	 * @throws FindException
	 */
	PageGroup<Task> findAllTaskList(Integer teamNo, int currentPage) throws FindException;
	
}
