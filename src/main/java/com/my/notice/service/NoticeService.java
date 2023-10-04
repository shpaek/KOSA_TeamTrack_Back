package com.my.notice.service;

import com.my.exception.FindException;
import com.my.notice.dto.NoticeDTO;
import com.my.util.PageGroup;

public interface NoticeService {
	PageGroup<NoticeDTO> findNoticeAll(int currentPage, Integer teamNo) throws FindException;
}
