package com.my.rank.dao;

import java.lang.module.FindException;
import java.util.Date;
import java.util.List;

import com.my.exception.AddException;
import com.my.rank.dto.RankDTO;

public class RankDAOImpl implements RankDAO {


	@Override
	public List<RankDTO> selectByMonth(int teamNo, Date rankDate) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(int teamNo, RankDTO rankDto) throws AddException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AttendanceDTO> selectAttendanceDay(int teamNo, Date attendanceDate) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void selectAllTask(TaskDTO taskDto) throws FindException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TaskDTO> countMonthlyTask(int teamNo, Date endDate) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberScoreDTO> selectTaskScore(int teamNo, Date submitDate) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaskDTO> selectReviewScore(int teamNo, Date endDate) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QnACommentDTO> selectQnAScore(int teamNo, Date pickedDate) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

}
