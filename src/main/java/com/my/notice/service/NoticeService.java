package com.my.notice.service;

import com.my.exception.FindException;
import com.my.notice.dto.NoticeDTO;
import com.my.util.PageGroup;

public interface NoticeService {
	/**
	 * 전체 공지 게시글들을 페이징하여 모두 반환한다
	 * @param currentPage 현재 페이지 번호
	 * @param teamNo 팀번호
	 * @return 페이징된 게시글들
	 * @throws FindException DB 연결 실패할 시 예외 발생한다
	 */
	PageGroup<NoticeDTO> findNoticeAll(int currentPage, Integer teamNo) throws FindException;
}
