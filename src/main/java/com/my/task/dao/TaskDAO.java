package com.my.task.dao;

import java.util.List;

import com.my.exception.FindException;
import com.my.task.dto.MemberTaskDTO;
import com.my.task.dto.TaskDTO;

public interface TaskDAO {
	
	/**
	 * 과제생성순으로 정렬된 메인 과제들을 검색한다.
	 * @param teamNo 팀 번호
	 * @param desc true인 경우 최신순, false인 경우 오래된순 출력
	 * @return 과제 리스트
	 * @throws FindException
	 */
	List<TaskDTO> selectMainTaskList(Integer teamNo, boolean desc) throws FindException;

	/**
	 * 과제생성순으로 정렬된 전체 과제들 중 시작행부터 끝행까지의 과제들을 검색한다.
	 * @param teamNo 팀 번호
	 * @param startRow 시작행
	 * @param endRow 끝행
	 * @param desc true인 경우 최신순, false인 경우 오래된순 출력
	 * @return 과제 리스트
	 * @throws FindException
	 */
	List<TaskDTO> selectAllTaskList(Integer teamNo, int startRow, int endRow, boolean desc) throws FindException;
	
	/**
	 * 전체 과제 수를 검색한다.
	 * @param teamNo 팀 번호
	 * @return 전체 과제 수
	 * @throws FindException
	 */
	int selectAllTaskCount(Integer teamNo) throws FindException;
	
	
	/**
	 * 과제생성순으로 정렬된 완료한 과제들 중 시작행부터 끝행까지의 과제들을 검색한다.
	 * @param teamNo 팀 번호
	 * @param id 아이디
	 * @param startRow 시작행
	 * @param endRow 끝행
	 * @param desc true인 경우 최신순, false인 경우 오래된순 출력
	 * @return 과제 리스트
	 * @throws FindException
	 */
	List<MemberTaskDTO> selectCompleteTaskList(Integer teamNo, String id, int startRow, int endRow, boolean desc) throws FindException;
	
	/**
	 * 완료한 과제 수를 검색한다.
	 * @param teamNo 팀 번호
	 * @param id 아이디
	 * @return 푼 과제 수
	 * @throws FindException
	 */
	int selectCompleteTaskCount(Integer teamNo, String id) throws FindException;
	
	/**
	 * 과제생성순으로 정렬된 출제한 과제들 중 시작행부터 끝행까지의 과제들을 검색한다.
	 * @param teamNo 팀 번호
	 * @param id 아이디
	 * @param startRow 시작행
	 * @param endRow 끝행
	 * @param desc true인 경우 최신순, false인 경우 오래된순 출력
	 * @return 과제 리스트
	 * @throws FindException
	 */
	List<TaskDTO> selectMyTaskList(Integer teamNo, String id, int startRow, int endRow, boolean desc) throws FindException;
	
	/**
	 * 출제한 과제 수를 검색한다.
	 * @param teamNo 팀 번호
	 * @param id 아이디
	 * @return 과제 리스트
	 * @throws FindException
	 */
	int selectMyTaskCount(Integer teamNo, String id) throws FindException;
	
	/**
	 * 과제 번호에 해당하는 과제를 불러온다.
	 * @param teamNo 팀 번호
	 * @param taskNo 과제 번호
	 * @return 과제
	 * @throws FindException
	 */
	TaskDTO selectTaskInfo(Integer teamNo, Integer taskNo) throws FindException;
	
	/**
	 * 과제 번호에 해당하는 과제의 답안을 불러온다.
	 * @param teamNo 팀 번호
	 * @param taskNo 과제 번호
	 * @return 답 리스트
	 * @throws FindException
	 */
	List<Integer> selectQuizAnswer(Integer teamNo, Integer taskNo) throws FindException;
	
	/**
	 * 과제 번호에 해당하는 과제의 팀원 답안을 불러온다.
	 * @param teamNo 팀 번호
	 * @param taskNo 과제 번호
	 * @param id 아이디
	 * @return 답 리스트
	 * @throws FindException
	 */
	List<Integer> selectMemberAnswer(Integer teamNo, Integer taskNo, String id) throws FindException;
	
	/**
	 * 팀원이 제출한 과제의 점수를 불러온다.
	 * @param teamNo 팀 번호
	 * @param taskNo 과제 번호
	 * @param id 아이디
	 * @return 팀원의 과제 정보
	 * @throws FindException
	 */
	int selectMemberScore(Integer teamNo, Integer taskNo, String id) throws FindException;
	
//	
//	
//	
//	
//	
//	/**
//	 * 팀의 메인 과제 리스트를 가져온다.
//	 * @param teamNo 팀 번호
//	 * @return 팀의 메인 과제 리스트
//	 * @throws FindException 
//	 */
//	List<Task> selectMainTaskList(Integer teamNo) throws FindException;
//	
//	/**
//	 * 선택한 과제의 상세정보를 가져온다.
//	 * @param taskNo 과제 번호
//	 * @param id 아이디
//	 * @return 과제 상세정보
//	 * @throws FindException
//	 */
//	MemberTask selectTask(Integer taskNo, String id) throws FindException;
//	
//	/**
//	 * 과제의 정보를 생성한다.
//	 * @param task 과제
//	 * @throws AddException
//	 */
//	void insertTaskInfo(Task task) throws AddException;
//	
//	/**
//	 * 과제의 답안을 생성한다.
//	 * @param taskNo 과제 번호
//	 * @param answerList 답안 리스트
//	 * @throws AddException
//	 */
//	void insertTaskAnswer(Integer taskNo, List<Integer> answerList) throws AddException;
//	
//	/**
//	 * 회원의 과제 제출 정보를 생성한다.
//	 * @param memberTask 과제
//	 * @throws AddException
//	 */
//	void insertMemberTaskInfo(MemberTask memberTask) throws AddException;
//	
//	/**
//	 * 회원의 과제 제출 답안을 생성한다.
//	 * @param taskNo 과제 번호
//	 * @param id 아이디
//	 * @param memberAnswerList 회원의 답안 리스트
//	 * @throws AddException
//	 */
//	void insertMemberTaskAnswer(Integer taskNo, String id, List<Integer> memberAnswerList) throws AddException;
//	
//	/**
//	 * 과제의 정보를 수정한다.
//	 * @param task 과제
//	 * @throws ModifyException
//	 */
//	void updateTaskInfo(Task task) throws ModifyException;
//	
//	/**
//	 * 과제의 답안을 수정한다.
//	 * @param answerList 답안 리스트
//	 * @throws ModifyException
//	 */
//	void updateTaskAnswer(List<Integer> answerList) throws ModifyException;
//	
//	/**
//	 * 회원의 제출된 과제를 삭제한다.
//	 * @param taskNo 과제 번호
//	 * @param id 아이디
//	 * @throws RemoveException
//	 */
//	void deleteMemberTask(Integer taskNo, String id) throws RemoveException;
//	
//	/**
//	 * 회원의 제출된 과제 답안을 삭제한다.
//	 * @param taskNo 과제 번호
//	 * @param id 아이디
//	 * @throws RemoveException
//	 */
//	void deleteMemberAnswer(Integer taskNo, String id) throws RemoveException;
//	
}
