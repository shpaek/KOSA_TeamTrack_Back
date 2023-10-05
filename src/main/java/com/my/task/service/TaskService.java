package com.my.task.service;

import java.util.List;

import com.my.exception.FindException;
import com.my.task.dto.MemberTaskDTO;
import com.my.task.dto.TaskDTO;
import com.my.util.PageGroup;

public interface TaskService {
	
	/**
	 * 메인 과제 리스트를 불러온다.
	 * @param teamNo 팀 번호
	 * @param desc true인 경우 최신순, false인 경우 오래된순 출력
	 * @return 과제 리스트
	 * @throws FindException
	 */
	List<TaskDTO> findMainTaskList(Integer teamNo, boolean desc) throws FindException;
	
	/**
	 * 전체 과제 리스트를 페이징 처리하여 불러온다.
	 * @param teamNo 팀 번호
	 * @param currentPage 현재 페이지
	 * @param desc true인 경우 최신순, false인 경우 오래된순 출력
	 * @return 과제 리스트
	 * @throws FindException
	 */
	PageGroup<TaskDTO> findAllTaskList(Integer teamNo, int currentPage, boolean desc) throws FindException;
	
	/**
	 * 완료한 과제 리스트를 페이징 처리하여 불러온다.
	 * @param teamNo 팀 번호
	 * @param id 아이디
	 * @param currentPage 현재 페이지
	 * @param desc true인 경우 최신순, false인 경우 오래된순 출력
	 * @return 과제 리스트
	 * @throws FindException
	 */
	PageGroup<MemberTaskDTO> findCompleteTaskList(Integer teamNo, String id, int currentPage, boolean desc) throws FindException;
	
	/**
	 * 출제한 과제 리스트를 페이징 처리하여 불러온다.
	 * @param teamNo 팀 번호
	 * @param id 아이디
	 * @param currentPage 현재 페이지
	 * @param desc true인 경우 최신순, false인 경우 오래된순 출력
	 * @return 과제 리스트
	 * @throws FindException
	 */
	PageGroup<TaskDTO> findMyTaskList(Integer teamNo, String id, int currentPage, boolean desc) throws FindException;
}
