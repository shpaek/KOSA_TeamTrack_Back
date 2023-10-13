package com.my.task.service;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
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
	
//	TaskDTO findTaskInfo() throws FindException;
//	List<Integer> findMemberAnswer() throws FindException;
	
	/**
	 * 과제 출제 : 과제 정보를 업데이트한다.
	 * @param teamNo 팀 번호
	 * @param title 과제 제목
	 * @param enddate 과제 마감일
	 * @param taskNo 과제 번호
	 * @throws ModifyException
	 */
	void ModifyTask(Integer teamNo, String title, String enddate, Integer taskNo) throws ModifyException;
	
	/**
	 * 과제 출제 : 과제 답안을 생성한다.
	 * @param teamNo 팀 번호
	 * @param questionNo 문제 번호
	 * @param taskNo 과제 번호
	 * @param answer 답
	 * @throws AddException
	 */
	void AddQuizAnswer(Integer teamNo, Integer questionNo, Integer taskNo, int answer) throws AddException;
	
	/**
	 * 과제 출제 : 과제 답안을 수정한다.
	 * @param teamNo 팀 번호
	 * @param questionNo 문제 번호
	 * @param taskNo 과제 번호
	 * @param answer 답
	 * @throws ModifyException
	 */
	void addMemberAnswer(Integer teamNo, Integer taskNo, String id, String answer) throws AddException;
	
	/**
	 * 팀원 과제 정보를 생성한다.
	 * @param teamNo 팀 번호
	 * @param taskNo 과제 번호
	 * @param id 아이디
	 * @param hwscore 점수
	 * @param reviewScore 평점
	 * @throws AddException
	 */
	void addMemberScore(Integer teamNo, Integer taskNo, String id, int hwscore, int reviewScore) throws AddException;
	
	/**
	 * 팀원의 과제를 채점한다.
	 * @param teamNo 팀 번호
	 * @param taskNo 과제 번호
	 * @param id 아이디
	 * @return 점수
	 * @throws FindException
	 */
	int chkHwscore(Integer teamNo, Integer taskNo, String id) throws FindException;

	/**
	 * 평점을 부여한다.
	 * @param teamNo 팀 번호
	 * @param taskNo 과제 번호
	 * @param id 아이디
	 * @param reviewScore 평점
	 * @throws ModifyException
	 */
	void setReviewScore(Integer teamNo, Integer taskNo, String id, int reviewScore) throws ModifyException;
}
