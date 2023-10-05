package com.my.notice.service;

import java.util.List;

import com.my.exception.FindException;
import com.my.notice.dao.NoticeDAO;
import com.my.notice.dao.NoticeDAOImpl;
import com.my.notice.dto.NoticeDTO;
import com.my.util.PageGroup;

public class NoticeServiceImpl implements NoticeService {
	private NoticeDAO notice;
	private static NoticeServiceImpl service = new NoticeServiceImpl();
	public NoticeServiceImpl() {
		notice = new NoticeDAOImpl();
	}
	public static NoticeServiceImpl getInstance() {
		return service;
	}
	
	@Override
	public PageGroup<NoticeDTO> findNoticeAll(int currentPage, Integer teamNo) throws FindException{
		if(currentPage <1) {
			currentPage = 1;
		}
		
		int cntPerPage = 10; //한페이지당 보여줄 목록 수
		
		int startRow = 0;
		int endRow = 0;
		
		startRow = (currentPage -1)*cntPerPage +1;
		endRow = currentPage*cntPerPage;
		
		List<NoticeDTO> noticeList = notice.selectNoticeAll(startRow, endRow, teamNo);
		
		int totalCnt = notice.selectNoticeCount(teamNo);
		
		PageGroup<NoticeDTO> pg = new PageGroup<>(noticeList, currentPage, totalCnt); 
		return pg;
	}
	
	@Override
	public NoticeDTO findByNoticeNo(Integer teamNo, Integer noticeNo) throws FindException{
		return notice.selectByNoticeNo(teamNo, noticeNo);
	}
}
