package com.my.rank.service;

import java.sql.Date;
import java.util.List;

import com.my.exception.FindException;
import com.my.rank.dao.RankDAO;
import com.my.rank.dao.RankDAOImpl;
import com.my.task.dto.MemberTaskDTO;
import com.my.task.dto.TaskDTO;
import com.my.team.dto.AttendanceDTO;

public class RankServiceImpl implements RankService {
	
	private RankDAO rankDao;
	private static RankServiceImpl service = new RankServiceImpl();
	public RankServiceImpl() {
		rankDao = new RankDAOImpl();
	}
	public static RankServiceImpl getInstance() {
		return service;
	}
	
	@Override
	public List<AttendanceDTO> calculateAttendanceRate(Integer teamNo, String attendanceDate, Integer month)
			throws FindException {
		// 출석률 = 출석인증일수 / 월별 총 일수 * 100
		return null;
	}
	
	@Override
	public List<TaskDTO> calculateTaskScore(Integer teamNo, Integer month) throws FindException {
		// 과제점수평균 = 과제 점수 총합/월에 출제된 과제 총 개수
		return null;
	}
	
	@Override
	public List<TaskDTO> calculateReviewScore(Integer teamNo, Integer month) throws FindException {
		// 출제한 과제 평균 리뷰점수 누적합
		return null;
	}
	
//	@Override
//	public List<QnACommentDTO> calculateQnAScore(Integer teamNo, Integer month) throws FindException {
//		// 큐엔에이 채택 점수 누적합
//		return null;
//	}

	@Override
	public List<MemberTaskDTO> calculateTotalScore(Integer teamNo, Integer month) throws FindException {
		// 총 점수 = 출석률*0.1 + 과제점수평균 + 리뷰점수 + 큐엔에이 채택점수
		return null;
	}
	
}
