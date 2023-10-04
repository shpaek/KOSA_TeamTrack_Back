package com.my.team.service;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.team.dto.TeamDTO;

public interface TeamService {
	
	// 서현 웅니
	
	void createTeam(TeamDTO t) throws AddException;
	
	void teamNameDupChk(String teamName) throws FindException;

// ------------------------------------------------------------------------
	
	// 셍나
	
	/**
	 * 팀 메인 페이지 - 공지사항 보여주기
	 * @throws FindException
	 */
	void showInfo() throws FindException;
	
	/**
	 * 팀 메인 페이지 - 팀 소개 보여주기
	 * @throws FindException
	 */
	void showTeamInfo(TeamDTO teamDTO) throws FindException;
	
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
	
} // end interface
