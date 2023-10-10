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
import com.my.qna.dao.QnaBoardDAO;
import com.my.qna.dao.QnaBoardDAOImpl;
import com.my.task.dao.TaskDAO;
import com.my.task.dao.TaskDAOImpl;
import com.my.team.dao.TeamDAO;
import com.my.team.dao.TeamDAOImpl;
import com.my.team.dto.TeamDTO;
import com.my.team.dto.TeamHashtagDTO;
import com.my.util.MainPageGroup;
import com.my.util.PageGroup;

public class TeamServiceImpl implements TeamService {

	private TeamDAO teamDAO;
	private NoticeDAO noticeDAO;
	private TaskDAO taskDAO;
	private QnaBoardDAO qnaDAO;
	
	private static TeamServiceImpl service = new TeamServiceImpl();
	
	private TeamServiceImpl() {
		teamDAO = new TeamDAOImpl();
		noticeDAO = new NoticeDAOImpl();
		taskDAO = new TaskDAOImpl();
		qnaDAO = new QnaBoardDAOImpl();
	}
	
	public static TeamServiceImpl getInstance() {
		return service;
	}
	
// ------------------------------------------------------------------------
	
	// 서현 웅니
	public MainPageGroup<TeamDTO> findAll(int currentPage, String column) throws FindException{
		if(currentPage < 1) {
			currentPage = 1;
		}
		
		int cntPerPage = 9; //한페이지당 보여줄 목록 수 
		                    
		//currentPage        //1  2  3  4
		int startRow;        //1  4  7  10
		int endRow;          //3  6  9  12 
		//TODO
		endRow = currentPage * cntPerPage;
		startRow = ( currentPage -1 ) *cntPerPage + 1;
		//return repository.selectAll(startRow, endRow);
		
		List<TeamDTO> list = teamDAO.selectByCondition(column, startRow, endRow);
		int totalCnt = teamDAO.selectCount();		
		MainPageGroup<TeamDTO> pg = new MainPageGroup<>(list, currentPage, totalCnt);
		return pg;
	}
	
	@Override
	public MainPageGroup<TeamDTO> selectByData(int currentPage, String table, String column, String data) throws FindException{
		if(currentPage < 1) {
			currentPage = 1;
		}
		
		int cntPerPage = 9; //한페이지당 보여줄 목록 수 
		                    
		//currentPage        //1  2  3  4
		int startRow;        //1  4  7  10
		int endRow;          //3  6  9  12 
		//TODO
		endRow = currentPage * cntPerPage;
		startRow = ( currentPage -1 ) *cntPerPage + 1;
		//return repository.selectAll(startRow, endRow);
		
		List<TeamDTO> list = teamDAO.selectByData(table, column, data, startRow, endRow);
		int totalCnt = list.size();		
		MainPageGroup<TeamDTO> pg = new MainPageGroup<>(list, currentPage, totalCnt);
		return pg;
	}
	
	@Override
	public MainPageGroup<TeamDTO> selectByDate(int currentPage, String column, String startDate, String endDate) throws FindException{
		if(currentPage < 1) {
			currentPage = 1;
		}
		
		int cntPerPage = 9; //한페이지당 보여줄 목록 수 
		                    
		//currentPage        //1  2  3  4
		int startRow;        //1  4  7  10
		int endRow;          //3  6  9  12 
		//TODO
		endRow = currentPage * cntPerPage;
		startRow = ( currentPage -1 ) *cntPerPage + 1;
		//return repository.selectAll(startRow, endRow);
		
		List<TeamDTO> list = teamDAO.selectByDate(column, startDate, endDate, startRow, endRow);
		int totalCnt = list.size();		
		MainPageGroup<TeamDTO> pg = new MainPageGroup<>(list, currentPage, totalCnt);
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
	public List<TeamHashtagDTO> selectTeamHashtag(int teamNo) throws FindException {
		return teamDAO.selectTeamHashtag(teamNo);
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
			int qnaCnt = qnaDAO.selectAllCount(teamNo);
			if(noticeCnt == 0 & taskCnt == 0 & qnaCnt == 0) {
				teamDAO.deleteTeam(teamNo);
			}else {
				throw new RemoveException("팀을 삭제할 수 없습니다.");
			}
		} catch (FindException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TeamDTO> selectTopThreeTeams() throws FindException {
		return teamDAO.selectByCondition("viewcnt", 1, 9);
	}

	@Override
	public List<TeamDTO> selectByCondition(String column) throws FindException {
		return teamDAO.selectByCondition(column, 1, 9);
	}

	@Override
	public TeamDTO selectByTeamName(String teamName) throws FindException {
		return teamDAO.selectByTeamName(teamName);			
	}


	
	@Override
	public void updateViewCnt(int teamNo) throws ModifyException {
		teamDAO.updateViewCnt(teamNo);
	}

	

// ------------------------------------------------------------------------
	
	// 셍나
	
	@Override
	public void showTeamInfo(TeamDTO teamDTO) throws FindException {
		// TODO Auto-generated method stub
	}

	@Override
	public void showInfo(NoticeDTO noticeDTO) throws FindException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void joinTeam(TeamDTO teamDTO) throws AddException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void leaveTeam(TeamDTO teamDTO) throws ModifyException {
		// TODO Auto-generated method stub
	}

	@Override
	public void addViewCnt(TeamDTO teamDTO) throws AddException {
		// TODO Auto-generated method stub
	}









} // end class
