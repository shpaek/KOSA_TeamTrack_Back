package com.my.rank.dao;



import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.rank.dto.RankDTO;
import com.my.task.dto.MemberTaskDTO;
import com.my.task.dto.TaskDTO;
import com.my.team.dto.AttendanceDTO;
import com.my.team.dto.TeamMemberDTO;

public interface RankDAO {

	/**
	 * 팀별 랭킹 정보를 월별로 조회한다
	 * @param teamNo 팀 번호
	 * @param rankDate rank 일자
	 * @return rank객체 리스트
	 * @throws FindException date에 해당하는 rank 정보가 없거나 DB와 연결이 실패하면 예외발생
	 */
	 List<RankDTO> selectByMonth(Integer teamNo, String rankDate) throws FindException;

	/**
	 * 팀별 팀 멤버들의 id를 조회한다
	 * @param teamNo 팀 번호
	 * @return
	 * @throws FindException 팀 번호에 해당하는 멤버 아이디가 없거나 DB와 연결이 실패하면 예외발생
	 */
	 List<TeamMemberDTO> selectAll(Integer teamNo) throws FindException;

	/**
	 * 팀별 Rank 정보를 저장한다
	 * @param teamNo 팀 번호
	 * @param rankDto rank 객체
	 * @throws AddException DB와 연결이 실패하거나 제약조건위배일 경우 예외 발생
	 */
	 void insert(Integer teamNo, RankDTO rankDto) throws AddException;


	//랭킹 점수 계산
	/**
	 * 출석률 계산을 위해 id별, 월별 출석일수와 월 총 일수를 추출한다
	 * @param teamNo 팀 번호
	 * @param attendanceDate 출석일자
	 * @param month 과제 제출 마감 일자의 월
	 * @return AttendanceDTO 객체
	 * @throws FindException DB와 연결이 실패하는 경우 예외 발생
	 */
	 List<AttendanceDTO> selectAttendanceDay(Integer teamNo, String attendanceDate, Integer month) throws FindException;

	/**
	 * 각 Task의 마감기한 확인을 위해 상세 정보를 조회한다
	 * @throws FindException DB와 연결이 실패하는 경우 예외 발생
	 */
	 List<TaskDTO> selectAllTask() throws FindException;

	/**
	 * 과제 점수를 계산하기 위해 월별 총 과제 갯수를 조회한다
	 * @param teamNo 팀 번호
	 * @param month 과제 제출 마감 일자의 월
	 * @return TaskDTO 객체
	 * @throws FindException DB와 연결이 실패하거나 조회한 월에 과제가 없는 경우 예외 발생
	 */
	 List<TaskDTO> countMonthlyTask(Integer teamNo, Integer month) throws FindException;

	/**
	 * 과제 점수를 계산하기 위해 id별 월별 과제점수 총합을 조회한다
	 * @param teamNo 팀 번호
	 * @param month id별 과제 제출 마감 일자의 월
	 * @return MemberScoreDTO 객체
	 * @throws FindException DB와 연결이 실패하거나 조회한 월에 과제 점수가 없는 경우 예외 발생
	 */
	 List<MemberTaskDTO> selectTaskScore(Integer teamNo, Integer month) throws FindException;

	/**
	 * 출제 점수를 계산하기 위해 과제별 리뷰 점수 평균을 조회한다
	 * @param teamNo 팀 번호
	 * @param month 과제 제출 마감 일자의 월
	 * @return TaskDTO 객체
	 * @throws FindException DB와 연결이 실패하거나 조회한 과제의 별점 평균 점수가 없는 경우 예외 발생
	 */
	 List<TaskDTO> selectReviewScore(Integer teamNo, Integer month) throws FindException;

//	/**
//	 * QnA 답변 채택 점수를 계산하기 위해 월별 picked 채택 횟수를 조회한다
//	 * @param teamNo 팀 번호
//	 * @param pickedDate 답변 채택된 일자
//	 * @return QnACommentDTO 객체
//	 * @throws FindException DB와 연결이 실패하거나 조회한 월에 picked된 댓글이 없는 경우 예외 발생
//	 */
//	List<QnACommentDTO> selectQnAScore(Integer teamNo, Integer month) throws FindException;

}
