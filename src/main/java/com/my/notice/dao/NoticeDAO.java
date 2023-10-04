package com.my.notice.dao;

import java.util.List;

import com.my.exception.FindException;
import com.my.notice.dto.NoticeDTO;

public interface NoticeDAO {
	List<NoticeDTO> selectNoticeAll(int startRow, int endRow, Integer teamNo) throws FindException;
	
	int selectNoticeCount(Integer teamNo) throws FindException;
}
