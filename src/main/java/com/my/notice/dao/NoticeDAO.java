package com.my.notice.dao;

import java.util.List;

import com.my.exception.FindException;
import com.my.notice.dto.Notice;

public interface NoticeDAO {
	List<Notice> selectNoticeAll(int startRow, int endRow, Integer teamNo) throws FindException;
	
	int selectNoticeCount(Integer teamNo) throws FindException;
}
