package com.my.team.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.notice.dto.NoticeDTO;
import com.my.team.dto.TeamDTO;
import com.my.util.PageGroup;

public interface TeamService {
	
	// 서현 웅니
	/**
	 * 
	 * @param t
	 * @throws AddException
	 */
	void createTeam(HashMap<String, Object> params) throws AddException;
	
	/**
	 * 
	 * @param teamName
	 * @throws FindException
	 */
	void teamNameDupChk(String teamName) throws FindException;
	
	/**
	 * 
	 * @param t
	 * @throws ModifyException
	 */
	void updateTeam(TeamDTO t) throws ModifyException;
	
	/**
	 * 
	 */
	void updateHashtag(HashMap<String, Object> params) throws ModifyException;
	
	void deleteHashtag(int teamNo) throws RemoveException;
	/**
	 * 
	 * @param teamNo
	 * @throws RemoveException
	 */
	void deleteTeam(int teamNo) throws RemoveException;
	
	/**
	 * 
	 * @throws FindException
	 */
	List<TeamDTO> selectTopThreeTeams() throws FindException;
	
	/**
	 * 
	 * @throws FindException
	 */
	List<TeamDTO> selectByCondition(String column) throws FindException;
		
	TeamDTO selectByTeamName(String teamName) throws FindException;
	List<TeamDTO> selectByHashtag(String hashtag) throws FindException;
	TeamDTO selectByTeamNo(int teamNo) throws FindException;
	void updateViewCnt(int teamNo) throws ModifyException;
	
	
	/**
	 * 
	 * @param currentPage
	 * @return
	 * @throws FindException
	 */
	PageGroup<TeamDTO> findAll(int currentPage) throws FindException;

// ------------------------------------------------------------------------
	
	// 워니 침입
	/**
	 * 팀장 여부를 확인한다
	 * @author 나원희
	 * @param id 로그인된 아이디
	 * @param teamNo 팀번호
	 * @return 팀장 여부
	 * @throws FindException DB 연결 실패 시 예외 발생한다
	 */
	Integer leaderChk(String id, Integer teamNo) throws FindException;
	
	/**
	 * 참여중, 활동종료, 승인대기 팀 목록을 조회한다
	 * @author 나원희
	 * @param 현재페이지
	 * @param id 사용자 아이디
	 * @menuStatus 메뉴탭 번호
	 * @return 팀정보
	 * @throws FindException DB 연결 실패 시 예외 발생한다
	 */
	PageGroup<SignupTeamDTO> findMyTeam(int currentPage, String id, int menuStatus) throws FindException;
	
	/**
	 * 승인거절 팀 목록을 조회한다
	 * @author 나원희
	 * @param currentPage 현재페이지
	 * @param id 사용자 아이디
	 * @return 승인거절 팀 목록
	 * @throws FindException DB 연결 실패 시 예외 발생한다
	 */
	PageGroup<SignupTeamDTO> findRejectedTeam(int currentPage, String id) throws FindException;
	
	/**
	 * 승인대기 취소한다
	 * @author 나원희
	 * @param id 사용자 아이디
	 * @param teamNo 팀번호
	 * @throws RemoveException DB 연결 실패 시 예외 발생한다
	 */
	void cancelWaiting(String id, Integer teamNo) throws RemoveException;
	
	/**
	 * 승인거절 확인하여 목록에서 삭제한다
	 * @author 나원희
	 * @param id 사용자 아이디
	 * @param teamNo 팀번호
	 * @throws RemoveException DB 연결 실패 시 예외 발생한다
	 */
	void rejectCheck(String id, Integer teamNo) throws RemoveException;
	
	/**
	 * 팀에서의 나의 활동내역을 조회한다
	 * @author 나원희
	 * @param id 사용자 아이디
	 * @param teamNo 팀번호
	 * @return 해당 팀 정보와 내 활동내역을 조회한다
	 * @throws FindException  DB 연결 실패 시 예외 발생한다
	 * @throws SQLException 
	 */
	Map myActivity(String id, Integer teamNo) throws FindException, SQLException;
	
	
	// ------------------------------------------------------------------------

	// 셍나
	
	/**
	 * 팀 메인 페이지 - 팀 소개 보여주기
	 * @throws FindException
	 */
	void showTeamInfo(TeamDTO teamDTO) throws FindException;
	
	/**
	 * 팀 메인 페이지 - 공지사항 보여주기
	 * @throws FindException
	 */
	void showInfo(NoticeDTO noticeDTO) throws FindException;
	
	/**
	 * 팀 메인 페이지 - 팀에 가입하기
	 * @param teamDTO
	 * @throws AddException
	 */
	void joinTeam(TeamDTO teamDTO) throws AddException;
	
	/**
	 * 팀 메인 페이지 - 팀에서 나가기
	 * @param teamDTO
	 * @throws ModifyException
	 */
	void leaveTeam(TeamDTO teamDTO) throws ModifyException;
	
	/**
	 * 팀 메인 페이지 - 조회수 카운트
	 * @param teamDTO
	 * @throws AddException
	 */
	void addViewCnt(TeamDTO teamDTO) throws AddException;
	
} // end interface
