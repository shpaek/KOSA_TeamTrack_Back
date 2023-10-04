package com.my.notice.dao;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.exception.FindException;
import com.my.notice.dto.Notice;

public class NoticeDAOImpl implements NoticeDAO{
private SqlSessionFactory sqlSessionFactory;
	
	public NoticeDAOImpl() {
		String resource = "com/my/sql/mybatis-config.xml";
		InputStream inputStream;
		try{
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Notice> selectNoticeAll(int startRow, int endRow, Integer teamNo) throws FindException{
		SqlSession session = null;
		List<Notice> noticeList = new ArrayList<>();
		String tableName = "NOTICEBOARD_"+ String.valueOf(teamNo);
		
		try{
			session = sqlSessionFactory.openSession();
			Map map = new HashMap<>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("tableName", tableName);
			noticeList = session.selectList("com.my.notice.NoticeMapper.selectNoticeAll", map);
			return noticeList;
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}
	
	@Override
	public int selectNoticeCount(Integer teamNo) throws FindException{
		SqlSession session = null;
		String tableName = "NOTICEBOARD_"+ String.valueOf(teamNo);
		try {
			session = sqlSessionFactory.openSession();
			int count = session.selectOne("com.my.notice.NoticeMapper.selectNoticeCount", tableName);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session!=null) {
				session.close();
			}
		}		
	}
}
