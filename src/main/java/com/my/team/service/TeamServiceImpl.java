package com.my.team.service;

import java.util.HashMap;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.notice.dao.NoticeDAO;
import com.my.notice.dao.NoticeDAOImpl;
import com.my.notice.dto.NoticeDTO;
import com.my.task.dao.TaskDAO;
import com.my.task.dao.TaskDAOImpl;
import com.my.team.dao.TeamDAO;
import com.my.team.dao.TeamDAOImpl;
import com.my.team.dto.AttendanceDTO;
import com.my.team.dto.SignupTeamDTO;
import com.my.team.dto.TeamDTO;
import com.my.util.PageGroup;

public class TeamServiceImpl implements TeamService {

	private TeamDAO teamDAO;
	private NoticeDAO noticeDAO;
	private TaskDAO taskDAO;

	private static TeamServiceImpl service = new TeamServiceImpl();

	private TeamServiceImpl() {
		teamDAO = new TeamDAOImpl();
		noticeDAO = new NoticeDAOImpl();
		taskDAO = new TaskDAOImpl();
	}

	public static TeamServiceImpl getInstance() {
		return service;
	}

	// ------------------------------------------------------------------------

	// 서현 웅니
	@Override
	public PageGroup<TeamDTO> findAll(int currentPage) throws FindException{
		if(currentPage < 1) {
			currentPage = 1;
		}

		int cntPerPage = 10; //한페이지당 보여줄 목록 수

		//currentPage        //1  2  3  4
		int startRow;        //1  4  7  10
		int endRow;          //3  6  9  12
		//TODO
		endRow = currentPage * cntPerPage;
		startRow = ( currentPage -1 ) *cntPerPage + 1;
		//return repository.selectAll(startRow, endRow);

		List<TeamDTO> list = teamDAO.selectByCondition("createdate", startRow, endRow);
		int totalCnt = teamDAO.selectCount();
		PageGroup<TeamDTO> pg = new PageGroup<>(list, currentPage, totalCnt);
		return pg;
	}

	@Override
	public TeamDTO selectByTeamNo(int teamNo) throws FindException {
		return teamDAO.selectByTeamNo(teamNo);
	}

	@Override
	public void createTeam(HashMap<String, Object> params) throws AddException {
		teamDAO.createTeam(params);
	}
	@Override
	public void teamNameDupChk(String teamName) throws FindException {
		teamDAO.selectByTeamName(teamName);
	}

	@Override
	public void updateTeam(TeamDTO t) throws ModifyException {
		teamDAO.updateTeam(t);
	}

	@Override
	public void updateHashtag(HashMap<String, Object> params) throws ModifyException{
		teamDAO.updateHashtag(params);
	}

	@Override
	public void deleteHashtag(int teamNo) throws RemoveException{
		teamDAO.deleteHashtag(teamNo);
	}

	@Override
	public void deleteTeam(int teamNo) throws RemoveException {
		try {
			int noticeCnt = noticeDAO.selectNoticeCount(teamNo);
			int taskCnt = taskDAO.selectAllTaskCount(teamNo);
			if(noticeCnt == 0 & taskCnt ==0) {
				teamDAO.deleteTeam(teamNo);
			}
		} catch (FindException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TeamDTO> selectTopThreeTeams() throws FindException {
		return teamDAO.selectByCondition("view_cnt", 1, 3);
	}

	@Override
	public List<TeamDTO> selectByCondition(String column) throws FindException {
		return teamDAO.selectByCondition(column, 1, 10);
	}

	@Override
	public TeamDTO selectByTeamName(String teamName) throws FindException {
		return teamDAO.selectByTeamName(teamName);
	}

	@Override
	public List<TeamDTO> selectByHashtag(String hashtag) throws FindException {
		return teamDAO.selectByHashtag(hashtag, 1, 10);
	}

	/*
	@Override
	public void updateViewCnt(int teamNo) throws ModifyException {
		teamDAO.updateViewCnt(teamNo);
	}
	 */


	// ------------------------------------------------------------------------

	// 셍나

	// 팀 메인 페이지 - 팀 소개글 보여주기
	@Override
	public String selectTeamInfoByTeamNo(int teamNo) throws FindException {
		return teamDAO.selectTeamInfoByTeamNo(teamNo);
	}

	// 팀 메인 페이지 - 팀 자기소개 보여주기
	@Override
	public List<NoticeDTO> selectNoticeListByTeamNo(int teamNo) throws FindException {
		return teamDAO.selectNoticeListByTeamNo(teamNo);
	}

	// 팀 메인 페이지 - 팀 가입하기
	@Override
	public void joinTeam(SignupTeamDTO signupTeamDTO) throws AddException {
		teamDAO.insertSignUpTeam(signupTeamDTO);
	}

	// 팀 메인 페이지 - 팀 나가기 트랜잭션
	@Override
	public void leaveTeam(String id) throws Exception {
		teamDAO.leaveTeam(id);
	}

	// 팀 메인 페이지 - 팀원 닉네임 보여주기
	@Override
	public List<String> selectNicknameByTeamNo(int teamNo) throws FindException {
		return teamDAO.selectNicknameByTeamNo(teamNo);
	}

	// 팀 메인 페이지 - 조회수 측정
	@Override
	public void updateViewCnt(int teamNo) throws ModifyException {
		teamDAO.updateViewCnt(teamNo);
	}

	//	---------------------------

	// 팀 출석부 페이지 - 출석하기
	@Override
	public void insertAttendanceById(Integer teamNo, String id) throws AddException {
		teamDAO.insertAttendanceById(teamNo, id);
	}

	// 팀 출석부 페이지 - 출석 내역 조회하기
	@Override
	public List<AttendanceDTO> selectAttendanceById(Integer teamNo, String id) throws FindException {
		return teamDAO.selectAttendanceById(teamNo, id);
	}

} // end class
