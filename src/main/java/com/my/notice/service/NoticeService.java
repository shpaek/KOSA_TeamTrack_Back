package com.my.notice.service;

import com.my.exception.FindException;
import com.my.notice.dto.Notice;
import com.my.util.PageGroup;

public interface NoticeService {
	PageGroup<Notice> findNoticeAll(int currentPage, Integer teamNo) throws FindException;
}
