package com.my.team.service;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.team.dao.TeamDAO;
import com.my.team.dao.TeamDAOImpl;
import com.my.team.dto.TeamDTO;

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
	
	@Override
	public void createTeam(TeamDTO t) throws AddException {
		// TODO Auto-generated method stub

	}
	@Override
	public void teamNameDupChk(String teamName) throws FindException {
		// TODO Auto-generated method stub
		
	}
	
// ------------------------------------------------------------------------
	
	// 셍나

} // end class
