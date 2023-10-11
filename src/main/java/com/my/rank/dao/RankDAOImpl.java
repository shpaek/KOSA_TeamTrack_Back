package com.my.rank.dao;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.rank.dto.RankDTO;
import com.my.task.dto.MemberTaskDTO;
import com.my.task.dto.TaskDTO;
import com.my.team.dto.AttendanceDTO;
import com.my.team.dto.TeamMemberDTO;

public class RankDAOImpl implements RankDAO {
	
	private SqlSessionFactory sqlSessionFactory;
	
	public RankDAOImpl() {
		String resource = "com/my/sql/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<RankDTO> selectByMonth(Integer teamNo, String rankDate) throws FindException {
		SqlSession session = null;
		List<RankDTO> list = new ArrayList<>(); 
		
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("team_no", teamNo);
			map.put("date", rankDate);
			list = session.selectList("com.my.rank.RankMapper.selectByMonth", map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	@Override
	public List<TeamMemberDTO> selectAll(Integer teamNo) throws FindException {
		SqlSession session = null;
		List<TeamMemberDTO> list = new ArrayList<>(); 
		
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("team_no", teamNo);
			list = session.selectList("com.my.rank.RankMapper.selectById", map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void insert(Integer teamNo, RankDTO rank) throws AddException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AttendanceDTO> selectAttendanceDay(Integer teamNo, String attendanceDate, Integer month)
			throws FindException {
		SqlSession session = null;
		List<AttendanceDTO> list = new ArrayList<>(); 
		
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("team_no", teamNo);
			map.put("date", attendanceDate);
			map.put("month", month);
			list = session.selectList("com.my.rank.RankMapper.selectAttendanceDay", map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<TaskDTO> selectAllTask() throws FindException {
		SqlSession session = null;
		List<TaskDTO> list = new ArrayList<>(); 
		
		try {
			session = sqlSessionFactory.openSession();	
			list = session.selectList("com.my.rank.RankMapper.selectAllTask");
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<TaskDTO> countMonthlyTask(Integer teamNo, Integer month) throws FindException {
		SqlSession session = null;
		List<TaskDTO> list = new ArrayList<>(); 
		
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("team_no", teamNo);
			map.put("month", month);
			list = session.selectList("com.my.rank.RankMapper.countMonthlyTask", map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<MemberTaskDTO> selectTaskScore(Integer teamNo, Integer month) throws FindException {
		SqlSession session = null;
		List<MemberTaskDTO> list = new ArrayList<>(); 
		
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("team_no", teamNo);
			map.put("month", month);
			list = session.selectList("com.my.rank.RankMapper.selectTaskScore", map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<TaskDTO> selectReviewScore(Integer teamNo, Integer month) throws FindException {
		SqlSession session = null;
		List<TaskDTO> list = new ArrayList<>(); 
		
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("team_no", teamNo);
			map.put("month", month);
			list = session.selectList("com.my.rank.RankMapper.selectReviewScore", map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

//	@Override
//	public List<QnACommentDTO> selectQnAScore(Integer teamNo, Integer month) throws FindException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public static void main(String[] args) {
		RankDAOImpl dao = new RankDAOImpl();
		
		//rank list 가져오기 
		try {
			Integer teamNo = 9999;
			String date = "2023-10-01";
			
			List<RankDTO> list = dao.selectByMonth(teamNo, date);
			System.out.println("[월별 랭킹 리스트 출력]");
			System.out.println(list);
			System.out.println("-------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//attendance 조회하기
		try {
			List<AttendanceDTO> list = dao.selectAttendanceDay(9999, "2023-10-23", 10);
			System.out.println("[id별 월별 출석 수]");
			System.out.println(list);
			System.out.println("-------------------------------------");
		} catch (FindException e) {
			e.printStackTrace();
		}
		
		//월별 과제 갯수 조회하기
		try {
			List<TaskDTO> list = dao.countMonthlyTask(9999, 10);
			System.out.println("[월별 과제 갯수]");
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getMonth()+"월 - " + list.get(i).getMonthlyTaskNum() + "개");				
			}
			System.out.println("-------------------------------------");
		} catch (FindException e) {
			e.printStackTrace();
		}
		
		//회원별 과제점수 
		try {
			List<MemberTaskDTO> list = dao.selectTaskScore(9999, 10);
			System.out.println("[회원별 과제 점수]");
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getId() +", "+ list.get(i).getTotalScore() + ", " 
						+ list.get(i).getTaskNum() + ", " + list.get(i).getMonth());				
			}
			System.out.println("-------------------------------------");
		} catch (FindException e) {
			e.printStackTrace();
		}
		
		//id별 리뷰 점수 출력
		try {
			List<TaskDTO> list = dao.selectReviewScore(9999, 10);
			System.out.println("[과제별 리뷰 점수]");
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getId()+", "+list.get(i).getTotalReviewscore()+", "+list.get(i).getMonth());				
			}
			System.out.println("-------------------------------------");
		} catch (FindException e) {
			e.printStackTrace();
		}
		
		/*
		List<TaskDTO> list = dao.selectAllTask();
		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getTaskNo() +", " + list.get(i).getEndDate());				
		}
		*/
		
	}

}
