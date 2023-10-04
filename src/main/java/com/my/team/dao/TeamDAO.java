package com.my.team.dao;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.team.dto.TeamDTO;

public interface TeamDAO {
	/**
	 * 서비스에 등록된 전체 팀을 조회한다
	 * @return 팀객체
	 */
	TeamDTO selectAll();
	
	/**
	 * 팀번호에 해당하는 팀정보를 검색한다
	 * @param teamNo 팀번호
	 * @return 팀객체
	 * @throws FindException DB와의 연결실패 또는 제약조건위배일 경우 예외발생한다
	 */
	TeamDTO selectByTeamNo(int teamNo) throws FindException;
	
	/**
	 * 팀이름에 해당하는 팀정보를 검색한다
	 * @param teamName 팀이름
	 * @return 팀객체
	 * @throws FindException DB와의 연결실패 또는 제약조건위배일 경우 예외발생한다
	 */
	TeamDTO selectByTeamName(String teamName) throws FindException;
	
	/**
	 * 해시태그에 해당하는 팀을 검색한다
	 * @param hashtag 해시태그
	 * @return 팀객체
	 * @throws FindException
	 */
	TeamDTO selectByHashtag(String hashtag) throws FindException;
	
	/**
	 * 최신순으로 팀을 조회한다
	 * @return 팀객체
	 */
	TeamDTO selectByNewTeam();
	
	/**
	 * 조회수순으로 팀을 조회한다
	 * @return 팀객체
	 */
	TeamDTO selectByViewCnt();
	
	/**
	 * 스터디날짜에 해당하는 팀을 조회한다
	 * @param startDate 스터디 시작일자
	 * @param endDate 스터디 종료일자
	 * @return 팀객체
	 */
	TeamDTO selectByStudyDate(String startDate, String endDate);
	
	/**
	 * 팀을 추가한다
	 * @param c 팀객체
	 * @throws AddException DB와의 연결실패 또는 제약조건위배일 경우 예외발생한다
	 */
	void create(TeamDTO t) throws AddException;
	
	/**
	 * 팀정보를 수정한다
	 * @param teamNo 팀번호
	 * @throws ModifyException DB와의 연결실패 또는 제약조건위배일 경우 예외발생한다
	 */
	void update(int teamNo) throws ModifyException;
	
	/**
	 * 팀을 삭제한다
	 * @param teamNo 팀번호
	 * @throws RemoveException DB와의 연결실패 또는 제약조건위배일 경우 예외발생한다
	 */
	void delete(int teamNo) throws RemoveException;
	
}
