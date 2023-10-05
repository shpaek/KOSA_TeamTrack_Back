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
	public List<TeamDTO> selectAll(int startRow, int endRow) throws FindException {
		SqlSession session = null;
		List<TeamDTO> list = new ArrayList<>();	
		try {
			session = sqlSessionFactory.openSession();
			//session.selectOne();
			Map<String, Integer> map = new HashMap<>();
			map.put("start", startRow);
			map.put("end", endRow);
			list = session.selectList("com.my.team.TeamMapper.selectAll", map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

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
	public TeamDTO selectByTeamNo(int teamNo) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamDTO selectByTeamName(String teamName) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamDTO> selectByHashtag(String hashtag) throws FindException {
		SqlSession session = null;

		try {
			List<TeamDTO> list = new ArrayList<>();
			session = sqlSessionFactory.openSession(); //Connection
			list = session.selectList("com.my.team.TeamMapper.selectByHashtag");
			if(list != null) {
				return list;
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
	public List<TeamDTO> selectByNewTeam() throws FindException {
		SqlSession session = null;

		try {
			List<TeamDTO> list = new ArrayList<>();
			session = sqlSessionFactory.openSession(); //Connection
			list = session.selectList("com.my.team.TeamMapper.selectByNewTeam");
			if(list != null) {
				return list;
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
	public List<TeamDTO> selectByViewCnt(int startRow, int endRow) throws FindException {
		SqlSession session = null;
		List<TeamDTO> list = new ArrayList<>();	
		try {
			session = sqlSessionFactory.openSession();
			//session.selectOne();
			Map<String, Integer> map = new HashMap<>();
			map.put("start", startRow);
			map.put("end", endRow);
			list = session.selectList("com.my.product.ProductMapper.selectByViewCnt", map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<TeamDTO> selectByStudyDate(String startDate, String endDate) throws FindException {
		SqlSession session = null;

		try {
			List<TeamDTO> list = new ArrayList<>();
			session = sqlSessionFactory.openSession(); //Connection
			list = session.selectList("com.my.team.TeamMapper.selectByStudyDate");
			if(list != null) {
				return list;
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
	public void create(TeamDTO t) throws AddException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int teamNo) throws ModifyException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.update("com.my.team.TeamMapper.update", teamNo);
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
	public void delete(int teamNo) throws RemoveException {
		// TODO Auto-generated method stub

	}

	//	---------------------------------------------------------------------------------

	// 셍나
	

} // end class
