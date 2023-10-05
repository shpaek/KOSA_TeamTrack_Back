package com.my.team.service;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
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
	
	@Override
	public void createTeam(TeamDTO t) throws AddException {
		// TODO Auto-generated method stub

	}
	@Override
	public void teamNameDupChk(String teamName) throws FindException {
		// TODO Auto-generated method stub
		
	}
	
	public PageGroup<TeamDTO> findAll(int currentPage) throws FindException{
		if(currentPage < 1) {
			currentPage = 1;
		}
		
		int cntPerPage = 3; //한페이지당 보여줄 목록 수 
		                    
		//currentPage        //1  2  3  4
		int startRow;        //1  4  7  10
		int endRow;          //3  6  9  12 
		//TODO
		endRow = currentPage * cntPerPage;
		startRow = ( currentPage -1 ) *cntPerPage + 1;
		//return repository.selectAll(startRow, endRow);
		
		List<TeamDTO> list = teamDAO.selectAll(startRow, endRow);		
		int totalCnt = teamDAO.selectCount();		
		PageGroup<TeamDTO> pg = new PageGroup<>(list, currentPage, totalCnt);
		return pg;
	}
	
// ------------------------------------------------------------------------
	
	// 셍나

} // end class
