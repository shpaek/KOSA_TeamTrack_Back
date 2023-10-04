package com.my.team.dao;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.team.dto.Team;

public class TeamDAOImpl implements TeamDAO {

	@Override
	public Team selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team selectByTeamNo(int teamNo) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team selectByTeamName(String teamName) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team selectByHashtag(String hashtag) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team selectByNewTeam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team selectByViewCnt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team selectByStudyDate(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Team t) throws AddException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int teamNo) throws ModifyException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int teamNo) throws RemoveException {
		// TODO Auto-generated method stub

	}

}
