package com.my.team.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.team.dto.TeamDTO;
import com.my.team.dto.TeamHashtagDTO;

public interface TeamDAO {

	// 서현 웅니
	
	/**
	 * 
	 * @return
	 * @throws FindException DB와의 연결실패 또는 제약조건위배일 경우 예외발생한다
	 */
	int selectCount() throws FindException;
	
	/**
	 * 팀번호에 해당하는 팀정보를 검색한다
	 * @param teamNo 팀번호
	 * @return 팀객체
	 * @throws FindException DB와의 연결실패 또는 제약조건위배일 경우 예외발생한다
	 */
	TeamDTO selectByTeamNo(int teamNo) throws FindException;

	
	/**
	 * 팀이름에 해당하는 팀번호를 검색한다
	 * @param teamName 팀이름
	 * @return 팀객체
	 * @throws FindException DB와의 연결실패 또는 제약조건위배일 경우 예외발생한다
	 */
	int selectByTeamName(String teamName) throws FindException;
	
	List<TeamDTO> selectHashtag(String hashtag, int startRow, int endRow) throws FindException;
	
	
	/**
	 * 해시태그에 해당하는 팀을 검색한다
	 * @param hashtag 해시태그
	 * @return 팀객체
	 * @throws FindException DB와의 연결실패 또는 제약조건위배일 경우 예외발생한다
	 */
	List<TeamDTO> selectByData(String table, String column, String data, int startRow, int endRow) throws FindException;
	
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param startRow
	 * @param endRow
	 * @return
	 * @throws FindException
	 */
	List<TeamDTO> selectByDate(String column, String startDate, String endDate, int startRow, int endRow) throws FindException;

	/**
	 * 조건에 맞는 팀을 조회한다
	 * @return 팀객체
	 * @throws FindException DB와의 연결실패 또는 제약조건위배일 경우 예외발생한다
	 */
	List<TeamDTO> selectByCondition(String column, int startRow, int endRow) throws FindException;

	/**
	 * 팀을 추가한다
	 * @param c 팀객체
	 * @throws AddException DB와의 연결실패 또는 제약조건위배일 경우 예외발생한다
	 */
	void createTeam(Map<String, Object> params) throws AddException;

	/**
	 * 팀정보를 수정한다
	 * @param teamNo 팀번호
	 * @throws ModifyException DB와의 연결실패 또는 제약조건위배일 경우 예외발생한다
	 */
	void updateTeam(TeamDTO team) throws ModifyException;

	/**
	 * 
	 * @param teamNo
	 * @return
	 * @throws FindException
	 */
	List<TeamHashtagDTO> selectTeamHashtag(int teamNo) throws FindException;
	
	/**
	 * 
	 * @param hashtag
	 * @throws RemoveException
	 */
	void deleteHashtag(int teamNo) throws RemoveException;
	
	/**
	 * 
	 * @param hashtag
	 * @throws ModifyException
	 */
	void updateHashtag(Map<String, Object> params) throws ModifyException;
	
	/**
	 * 팀을 삭제한다
	 * @param teamNo 팀번호
	 * @throws RemoveException DB와의 연결실패 또는 제약조건위배일 경우 예외발생한다
	 */
	void deleteTeam(int teamNo) throws RemoveException;

	void updateViewCnt(int teamNo) throws ModifyException;
	// ------------------------------------------------------------------------

	// 셍나
	
	
} // end interface
