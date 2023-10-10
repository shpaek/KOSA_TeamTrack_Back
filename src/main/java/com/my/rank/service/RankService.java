package com.my.rank.service;

import java.util.List;
import java.util.Map;

import com.my.exception.FindException;
import com.my.rank.dto.RankDTO;
import com.my.rank.dto.RankDetailDTO;
import com.my.task.dto.MemberTaskDTO;
import com.my.task.dto.TaskDTO;
import com.my.team.dto.AttendanceDTO;

public interface RankService {

	/**
	 * 월별 팀 내의 개인 랭킹을 조회한다. 랭킹이 없으면 FindException이 발생한다
	 * @param teamNo
	 * @param rankDate
	 * @return
	 * @throws FindException
	 */
	// public List<RankDTO> findByMonth(Integer teamNo, String rankDate) throws FindException;

	/**
	 * 각 팀 멤버의 총 점수를 계산한다. 멤버의 점수가 없으면 FindException 발생한다
	 * @param teamNo
	 * @param month
	 * @return
	 * @throws FindException
	 */
	public Map<String, Object> calculateTotalScore(Integer teamNo, String attendanceDate, Integer month) throws FindException;
	
//	/**
//	 * 출석률을 계산한다. 출석인증일수 / 월별 총 일수 * 100  출석일이 존재하지 않으면 FindException 발생한다
//	 * @param teamNo
//	 * @param attendanceDate
//	 * @param month
//	 * @throws FindException
//	 */
//	public List<AttendanceDTO> calculateAttendanceRate(Integer teamNo, String attendanceDate, Integer month) throws FindException;
//
//	/**
//	 * 과제 점수를 계산한다. 과제가 없으면 FindException 발생한다
//	 * @param teamNo
//	 * @param month
//	 * @return
//	 * @throws FindException
//	 */
//	public List<TaskDTO> calculateTaskScore(Integer teamNo, Integer month) throws FindException;
//
//	/**
//	 * 과제 출제 별점 점수를 계산한다. 별점이 없으면 FindException 발생한다
//	 * @param teamNo
//	 * @param month
//	 * @return
//	 * @throws FindException
//	 */
//	public List<TaskDTO> calculateReviewScore(Integer teamNo, Integer month) throws FindException;
//
//	/**
//	 * 답글 채택 점수를 계산한다. 채택 점수가 없으면 FindException 발생한다
//	 * @param teamNo
//	 * @param month
//	 * @return
//	 * @throws FindException
//	 */
//	public List<QnACommentDTO> calculateQnAScore(Integer teamNo, Integer month) throws FindException;

}
