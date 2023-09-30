package com.my.rank.dao;

import java.lang.module.FindException;
import java.util.Date;
import java.util.List;

import com.my.exception.AddException;
import com.my.rank.dto.RankDTO;

public interface RankDAO {

	/**
	 * 팀별 랭킹 정보를 월별로 조회한다
	 * @param rankDate rank 일자
	 * @return rank객체 리스트 
	 * @throws FindException date에 해당하는 rank 정보가 없거나 DB와 연결이 실패하면 예외발생
	 */
	List<RankDTO> selectByMonth(Date rankDate) throws FindException;

	/**
	 * 팀별 Rank 정보를 저장한다 
	 * @param rankDto rank 객체 
	 * @throws AddException DB와 연결이 실패하거나 제약조건위배일 경우 예외 발생
	 */
	void insert(RankDTO rankDto) throws AddException;
	
	
	
}
