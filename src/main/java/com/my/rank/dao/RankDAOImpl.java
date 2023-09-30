package com.my.rank.dao;

import java.lang.module.FindException;
import java.util.Date;
import java.util.List;

import com.my.exception.AddException;
import com.my.rank.dto.RankDTO;

public class RankDAOImpl implements RankDAO {

	@Override
	public List<RankDTO> selectByMonth(Date rankDate) throws FindException {
		return null;
	}

	@Override
	public void insert(RankDTO rankDto) throws AddException {

	}

}
