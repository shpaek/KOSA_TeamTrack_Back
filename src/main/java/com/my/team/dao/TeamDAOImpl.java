package com.my.team.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.team.dto.TeamDTO;
import com.my.team.dto.TeamHashtagDTO;

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
	public int selectCount() throws FindException{

		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession(); //Connection
			int count = session.selectOne("com.my.team.TeamMapper.selectCount");
			return count;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}		
	}

	@Override
	public int selectCountOfSelectHashtag(String hashtag) throws FindException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession(); //Connection
			int count = session.selectOne("com.my.team.TeamMapper.selectCountOfSelectHashtag", hashtag);
			return count;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public int selectCountOfSelectDate(String column, String startDate, String endDate) throws FindException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession(); //Connection
			Map<String, Object> map = new HashMap<>();
			map.put("column", column);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			int count = session.selectOne("com.my.team.TeamMapper.selectCountOfSelectDate", map);
			return count;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public int selectCountOfSelectData(String table, String column, String data) throws FindException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession(); //Connection
			Map<String, Object> map = new HashMap<>();
			map.put("table", table);
			map.put("column", column);
			map.put("data", data);
			int count = session.selectOne("com.my.team.TeamMapper.selectCountOfSelectData", map);
			return count;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public TeamDTO selectByTeamNo(int teamNo) throws FindException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession(); //Connection
			TeamDTO team = session.selectOne("com.my.team.TeamMapper.selectByTeamNo", teamNo);
			if(team != null) {
				return team;
			}else {
				throw new FindException("해당하는 팀이 없습니다");
			}
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}		
	}

	@Override
	public int selectByTeamName(String teamName) throws FindException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession(); //Connection
			int teamNo = session.selectOne("com.my.team.TeamMapper.selectByTeamName", teamName);
			if(teamNo != 0) {
				return teamNo;
			}else {
				throw new FindException("해당하는 팀이 없습니다");
			}
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	
	@Override
	public List<TeamDTO> selectByData(String table, String column, String data, int startRow, int endRow) throws FindException {
		SqlSession session = null;
		List<TeamDTO> list = new ArrayList<>();

		try {
			session = sqlSessionFactory.openSession(); //Connection
			Map<String, Object> map = new HashMap<>();
			map.put("table", table);
			map.put("column", column);
			map.put("data", data);
			map.put("start", startRow);
			map.put("end", endRow);
			list = session.selectList("com.my.team.TeamMapper.selectByData", map);
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<TeamDTO> selectByCondition(String column, int startRow, int endRow) throws FindException {
		SqlSession session = null;
		List<TeamDTO> list = new ArrayList<>();

		try {
			session = sqlSessionFactory.openSession(); //Connection
			Map<String, Object> map = new HashMap<>();
			map.put("column", column);
			map.put("start", startRow);
			map.put("end", endRow);
			list = session.selectList("com.my.team.TeamMapper.selectByCondition", map);
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	public List<TeamDTO> selectByDate(String column, String startDate, String endDate, int startRow, int endRow) throws FindException{
		SqlSession session = null;
		List<TeamDTO> list = new ArrayList<>();

		try {
			session = sqlSessionFactory.openSession(); //Connection
			Map<String, Object> map = new HashMap<>();
			map.put("column", column);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			map.put("start", startRow);
			map.put("end", endRow);
			list = session.selectList("com.my.team.TeamMapper.selectByDate", map);
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}		

	
	
	@Override
	public void createTeam(Map<String, Object> params) throws AddException {

		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();
			session.selectOne("com.my.team.TeamMapper.createTeam", params);
			session.commit();
		} catch(Exception e){
			session.rollback();
			throw new AddException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void updateTeam(TeamDTO team) throws ModifyException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.update("com.my.team.TeamMapper.updateTeam", team);
			session.commit();
		} catch(Exception e){
			session.rollback();
			throw new ModifyException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void deleteTeam(int teamNo) throws RemoveException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();
			session.delete("com.my.team.TeamMapper.deleteTeam", teamNo);
			session.commit();
		} catch(Exception e){
			session.rollback();
			throw new RemoveException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}

	}

	@Override
	public List<TeamHashtagDTO> selectTeamHashtag(int teamNo) throws FindException {
		SqlSession session = null;
		List<TeamHashtagDTO> list = new ArrayList<>();

		try {
			session = sqlSessionFactory.openSession(); //Connection
			list = session.selectList("com.my.team.TeamMapper.selectTeamHashtag", teamNo);
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
		
	
	
	@Override
	public void deleteHashtag(int teamNo) throws RemoveException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.delete("com.my.team.TeamMapper.deleteHashtag", teamNo);
			session.commit();
		} catch(Exception e){
			session.rollback();
			throw new RemoveException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void updateHashtag(Map<String, Object> params) throws ModifyException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession(); //Connection
			session.update("com.my.team.TeamMapper.insertHashtag", params);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void updateViewCnt(int teamNo) throws ModifyException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession(); //Connection
			session.update("com.my.team.TeamMapper.updateViewCnt", teamNo);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<TeamDTO> selectHashtag(String hashtag, int startRow, int endRow) throws FindException {
		SqlSession session = null;
		List<TeamDTO> list = new ArrayList<>();

		try {
			session = sqlSessionFactory.openSession(); //Connection
			Map<String, Object> map = new HashMap<>();
			map.put("data", hashtag);
			map.put("start", startRow);
			map.put("end", endRow);
			list = session.selectList("com.my.team.TeamMapper.selectHashtag", map);
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}






		

	//	---------------------------------------------------------------------------------

	// 셍나


} // end class
