package com.my.team.service;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.notice.dto.NoticeDTO;
import com.my.team.dao.TeamDAO;
import com.my.team.dao.TeamDAOImpl;
import com.my.team.dto.TeamDTO;
import com.my.util.PageGroup;

public class TeamServiceImpl implements TeamService {

	private TeamDAO teamDAO;
	
	private static TeamServiceImpl service = new TeamServiceImpl();
	
	private TeamServiceImpl() {
		teamDAO = new TeamDAOImpl();
	}
	
	public static TeamServiceImpl getInstance() {
		return service;
	}
	
// ------------------------------------------------------------------------
	
	// 서현 웅니
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
	public void createTeam(TeamDTO t) throws AddException {
		teamDAO.createTeam(t);
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
	public void updateHashtag(String hashtag) throws ModifyException, RemoveException{
		if(hashtag != null) {
			teamDAO.deleteHashtag(hashtag);
			teamDAO.updateHashtag(hashtag);
		}
	}

	@Override
	public void deleteTeam(int teamNo) throws RemoveException {
		teamDAO.deleteTeam(teamNo);		
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
