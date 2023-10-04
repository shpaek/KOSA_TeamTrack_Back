package com.my.team.dao;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.team.dto.Team;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TeamDAOImpl implements TeamDAO {
	
	private SqlSessionFactory sqlSessionFactory;

	public TeamDAOImpl() {
		
		String resource = "com/my/sql/mybatis-config.xml";
		InputStream inputStream;
		
		try {

			inputStream = Resources.getResourceAsStream(resource);

			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} // try-catch

	} // constructor
	
//	---------------------------------------------------------------------------------

	// 서현웅니

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

//	---------------------------------------------------------------------------------

	// 셍나


} // end class
