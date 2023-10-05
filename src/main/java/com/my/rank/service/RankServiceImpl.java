package com.my.rank.service;

import java.sql.Date;

import com.my.exception.FindException;
import com.my.rank.dao.RankDAO;
import com.my.rank.dao.RankDAOImpl;

public class RankServiceImpl implements RankService {
	
	private RankDAO rankDao;
	private static RankServiceImpl service = new RankServiceImpl();
	public RankServiceImpl() {
		rankDao = new RankDAOImpl();
	}
	public static RankServiceImpl getInstance() {
		return service;
	}
	
	
	
	
}
