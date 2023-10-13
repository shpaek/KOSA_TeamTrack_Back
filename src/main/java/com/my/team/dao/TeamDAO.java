package com.my.team.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.team.dto.TeamDTO;
import com.my.team.dto.TeamMemberDTO;
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
	 * @throws FindException DB와의 연결실패 또는 제약조건위배일 경우 예외발생한다
	 */
	List<TeamDTO> selectByHashtag(String hashtag, int startRow, int endRow) throws FindException;

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

	// void updateViewCnt(int teamNo) throws ModifyException;
	
	// ------------------------------------------------------------------------
	
	//워니 침입
	/**
	 * 팀장 아이디를 조회한다
	 * @author 나원희
	 * @param teamNo 팀번호
	 * @return 팀장 아이디
	 * @throws FindException DB와의 연결 실패 시 예외 발생한다
	 */
	String selectLeaderId(Integer teamNo) throws FindException;
	
	/**
	 * 참여중인 팀 목록을 조회한다
	 * @author 나원희
	 * @param startRow 시작행
	 * @param endRow 마지막행
	 * @param id 사용자 아이디
	 * @return 참여중인 팀
	 * @throws FindException DB와의 연결 실패 시 예외 발생한다
	 */
	List<SignupTeamDTO> selectMyTeam(int startRow, int endRow, String id) throws FindException;
	
	/**
	 * 참여중인 팀 개수 조회한다
	 * @author 나원희
	 * @param id 사용자 아이디
	 * @return 참여중인 팀 개수 
	 * @throws FindException DB와의 연결 실패 시 예외 발생한다
	 */
	int selectMyTeamCount(String id) throws FindException;
	
	/**
	 * 활동종료된 팀 목록 조회한다
	 * @author 나원희
	 * @param startRow 시작행
	 * @param endRow 마지막행
	 * @param id 사용자 아이디
	 * @return 활동종료된 팀
	 * @throws FindException DB와의 연결 실패 시 예외 발생한다
	 */
	List<SignupTeamDTO> selectEndTeam(int startRow, int endRow, String id) throws FindException;
	
	/**
	 * 활동종료된 팀 개수 조회한다
	 * @author 나원희
	 * @param id 사용자 아이디
	 * @return 활동종료된 팀 개수
	 * @throws FindException DB와의 연결 실패 시 예외 발생한다
	 */
	int selectEndTeamCount(String id) throws FindException;
	
	/**
	 * 승인대기, 승인거절 팀 목록 조회한다
	 * @author 나원희
	 * @param startRow 시작행
	 * @param endRow 마지막행
	 * @param id 사용자 아이디
	 * @param status 팀 승인여부
	 * @return 승인대기중인 팀
	 * @throws FindException DB와의 연결 실패 시 예외 발생한다
	 */
	List<SignupTeamDTO> selectWaitingTeam(int startRow, int endRow, String id, Integer status) throws FindException;
	
	/**
	 * 승인대기, 승인거절 팀 개수 조회한다
	 * @author 나원희
	 * @param id 사용자 아이디
	 * @param status 팀 승인여부
	 * @return 승인대기중 팀 개수 
	 * @throws FindException DB와의 연결 실패 시 예외 발생한다
	 */
	int selectWaitingTeamCount(String id, Integer status) throws FindException;
	
	/**
	 * 승인대기 팀을 삭제한다
	 * @author 나원희
	 * @param id 사용자 아이디
	 * @param teamNo 팀번호
	 * @throws RemoveException DB와의 연결 실패 시 예외 발생한다
	 */
	void deleteSignupTeamByTeamNo(String id, Integer teamNo) throws RemoveException;
	
	/**
	 * 해당 팀에서의 멤버 정보를 조회한다
	 * @author 나원희
	 * @param id 사용자 아이디
	 * @param teamNo 팀번호
	 * @return 팀에서의 멤버 정보
	 * @throws FindException DB와의 연결 실패 시 예외 발생한다
	 */
	TeamMemberDTO selectTeamMember(String id, Integer teamNo) throws FindException;
	
	
	
	// ------------------------------------------------------------------------

	// 셍나
	
	
} // end interface
