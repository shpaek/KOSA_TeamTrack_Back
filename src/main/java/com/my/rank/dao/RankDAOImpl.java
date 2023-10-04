package com.my.rank.dao;

import java.io.InputStream;
import java.lang.module.FindException;
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
import com.my.rank.dto.Rank;

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
	public List<Rank> selectByMonth(Integer teamNo, Date rankDate) throws FindException {
		SqlSession session = null;
		List<Rank> list = new ArrayList<>(); 
		
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
	public void insert(Integer teamNo, Rank rank) throws AddException {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public List<AttendanceDTO> selectAttendanceDay(Integer teamNo, Date attendanceDate) throws FindException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void selectAllTask(TaskDTO taskDto) throws FindException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<TaskDTO> countMonthlyTask(Integer teamNo, Date endDate) throws FindException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<MemberScoreDTO> selectTaskScore(Integer teamNo, Date submitDate) throws FindException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<TaskDTO> selectReviewScore(Integer teamNo, Date endDate) throws FindException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<QnACommentDTO> selectQnAScore(Integer teamNo, Date pickedDate) throws FindException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	public static void main(String[] args) {
		RankDAOImpl dao = new RankDAOImpl();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		//rank list 가져오기 
		try {
			Integer teamNo = 9999;
			String date = "2023-10-01";
			Date rankDate = formatter.parse(date);
			
			List<Rank> list = dao.selectByMonth(teamNo, rankDate);
			System.out.println(list);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}


}
