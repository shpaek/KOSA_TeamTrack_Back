package com.my.team.service;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.team.dto.TeamDTO;

public interface TeamService {
	void createTeam(TeamDTO t) throws AddException;
	
	void teamNameDupChk(String teamName) throws FindException;

}
