package com.my.rank.service;

import java.sql.Date;
import java.util.List;

import com.my.exception.FindException;

public interface RankService {
	
	/**
	 * 출석률을 계산한다. 출석인증일수 / 월별 총 일수 * 100  출석일이 존재하지 않으면 FindException 발생한다 
	 * @param teamNo
	 * @param attendanceDate
	 * @throws FindException 
	 */
//	public List<AttendanceDTO> calculateAttendanceRate(int teamNo, Date attendanceDate) throws FindException;
//	
//	/**
//	 * 과제 점수를 계산한다. 과제가 없으면 FindException 발생한다 
//	 * @param teamNo
//	 * @param date
//	 * @return
//	 * @throws FindException
//	 */
//	public List<TaskDTO> calculateTaskScore(int teamNo, Date date) throws FindException;
//
//	/**
//	 * 과제 출제 별점 점수를 계산한다. 별점이 없으면 FindException 발생한다 
//	 * @param teamNo
//	 * @param date
//	 * @return
//	 * @throws FindException
//	 */
//	public List<TaskDTO> calculateReviewScore(int teamNo, Date date) throws FindException;
//	
//	/**
//	 * 답글 채택 점수를 계산한다. 채택 점수가 없으면 FindException 발생한다 
//	 * @param teamNo
//	 * @param date
//	 * @return
//	 * @throws FindException
//	 */
//	public List<QnACommentDTO> calculateQnAScore(int teamNo, Date date) throws FindException;
//	
//	/**
//	 * 각 팀 멤버의 총 점수를 계산한다. 멤버의 점수가 없으면 FindException 발생한다 
//	 * @param teamNo
//	 * @param date
//	 * @return
//	 * @throws FindException
//	 */
//	public List<MemberScoreDTO> calculateTotalScore(int teamNo, Date date) throws FindException;
	
	
}
