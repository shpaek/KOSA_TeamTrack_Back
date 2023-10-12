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
	public List<RankDTO> findByMonth(Integer teamNo, Integer month) throws FindException;
	
	/**
	 * 팀별 모든 랭킹 정보를 조회한다. 랭킹이 없으면 FindException이 발생한다
	 * @param teamNo
	 * @return
	 * @throws FindException
	 */
	public List<RankDTO> findAllRank(Integer teamNo) throws FindException;
	
	/**
	 * 각 팀 멤버의 총 점수를 계산한다. 멤버의 점수가 없으면 FindException 발생한다 
	 * @param teamNo
	 * @param month
	 * @return
	 * @throws FindException
	 */
	public Map<String, Object> calculateTotalScore(Integer teamNo, String attendanceDate, Integer month) throws FindException;
	
}
