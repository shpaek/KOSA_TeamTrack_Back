package com.my.team.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.notice.dto.NoticeDTO;
import com.my.task.dto.TaskDTO;
import com.my.team.dto.AttendanceDTO;
import com.my.team.dto.SignupTeamDTO;
import com.my.team.dto.TeamDTO;
import com.my.team.dto.TeamHashtagDTO;
import com.my.util.MainPageGroup;
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
	int teamNameDupChk(String teamName) throws FindException;
	
	/**
	 *
	 * @param t
	 * @throws ModifyException
	 */
	void updateTeam(TeamDTO t) throws ModifyException;
	List<TeamHashtagDTO> selectTeamHashtag(int teamNo) throws FindException;
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
		
	

	TeamDTO selectByTeamNo(int teamNo) throws FindException;
	// void updateViewCnt(int teamNo) throws ModifyException;


	/**
	 *
	 * @param currentPage
	 * @return
	 * @throws FindException
	 */
	MainPageGroup<TeamDTO> findAll(int currentPage, String column) throws FindException;
	MainPageGroup<TeamDTO> selectByData(int currentPage, String table, String column, String data) throws FindException;
	MainPageGroup<TeamDTO> selectHashtag(int currentPage, String hashtag) throws FindException;
	MainPageGroup<TeamDTO> selectByDate(int currentPage, String column, String startDate, String endDate) throws FindException;

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
	
	
	// ------------------------------------------------------------------------

	// 셍나
	/**
	 * 팀 메인 페이지 - 팀 소개글 보여주기
	 * @param teamNo
	 * @return TeamDTO
	 * @throws FindException
	 */
	String selectTeamInfoByTeamNo(int teamNo) throws FindException;

	/**
	 * 팀 메인 페이지 - 공지사항 보여주기
	 * @param teamNo
	 * @return List<NoticeDTO>
	 * @throws FindException
	 */
	List<NoticeDTO> selectNoticeListByTeamNo(int teamNo) throws FindException;

	/**
	 * 팀 메인 페이지 - 팀에 가입하기
	 * @param SignupTeamDTO
	 * @throws AddException
	 */
	void insertSignUpTeam(SignupTeamDTO signupTeamDTO) throws AddException;

	/**
	 * 팀 메인 페이지 - 팀에서 나가기 #1 (팀원 테이블에서 상태값 변경)
	 * @param teamNo
	 * @param id
	 * @throws ModifyException
	 */
	void updateTeamMemberStatusResign(Integer teamNo, String id) throws ModifyException;

	/**
	 * 팀 메인 페이지 - 팀에서 나가기 #2 (가입한 팀 테이블에서 삭제)
	 * @param id
	 * @throws RemoveException
	 */
	void deleteSignupTeam(String id) throws RemoveException;

	/**
	 * 팀 메인 페이지 - 팀 나가기(트랜잭션)
	 * @param id 회원 아이디
	 * @throws Exception
	 */
	void leaveTeam(Integer teamNo, String id) throws Exception;

	/**
	 * 팀 메인 페이지 - 팀 멤버 출력
	 * @param teamNo
	 * @throws FindException
	 */
	List<String> selectNicknameByTeamNo(int teamNo) throws FindException;

	/**
	 * 팀 메인 페이지 - 조회수 카운트
	 * @param teamDTO
	 * @throws AddException
	 */
	void updateViewCnt(int teamNo) throws ModifyException;
	
	/**
	 * 팀 메인 페이지 - 조회수 출력
	 * @param teamNo
	 * @return int(조회수)
	 * @throws FindException
	 */
	int selectViewCnt(int teamNo) throws FindException;

//	---------------------------

	/**
	 * 팀 출석부 페이지 - 출석하기
	 * @param teamNo
	 * @param id
	 * @throws AddException
	 */
	void insertAttendanceById(Integer teamNo, String id) throws AddException;

	/**
	 * 팀 출석부 페이지 - 조회하기
	 * @param teamNo
	 * @param id
	 * @return
	 * @throws FindException
	 */
	List<AttendanceDTO> selectAttendanceById(Integer teamNo, String id) throws FindException;
	
//	---------------------------
	
	/**
	 * 팀 관리 페이지(현재 팀원 관리) - 현재 팀원들 정보 확인 (아이디, 닉네임, 자기소개)
	 * @param teamNo
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> selectMemberInfo(Integer teamNo) throws FindException;
	
	/**
	 * 팀 관리 페이지(현재 팀원 관리) - 팀원 방출
	 * @param map
	 * @throws Exception
	 */
	void updateTeamMemberStatusDismiss(Map<String, Object> map) throws ModifyException;
	
	/**
	 * 팀 관리 페이지(가입 요청 관리) - 팀 가입 요청 확인
	 * @param teamNo
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> selectRequestInfo(Integer teamNo) throws FindException;
	
	/**
	 * 팀 관리 페이지(가입 요청 관리) - 팀 가입 요청 승인1
	 * @param map
	 * @throws ModifyException
	 */
	void updateRequestInfoApprove(Map<String, Object> map) throws ModifyException;
	
	/**
	 * 팀 관리 페이지(가입 요청 관리) - 팀 가입 요청 승인2
	 * @param map
	 * @throws AddException
	 */
	void insertRequestInfoApprove(Map<String, Object> map) throws AddException;
	
	/**
	 * 팀 관리 페이지(가입 요청 관리) - 트랜잭션
	 * @param teamNo
	 * @param id
	 * @throws Exception
	 */
	void approveRequest(Map<String, Object> map) throws Exception;
	
	/**
	 * 팀 관리 페이지(가입 요청 관리) - 팀 가입 요청 거절
	 * @param map
	 * @throws Exception
	 */
	void updateRequestInfoReject(Map<String, Object> map) throws ModifyException;
	
	/**
	 * 팀 관리 페이지(출제자 선정) - 출제자 선정
	 * @param taskDTO
	 * @param teamNo
	 * @throws ModifyException
	 */
	void insertExaminer(TaskDTO taskDTO, Integer teamNo) throws ModifyException;

} // end interface