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
	
	/**
	 * 팀 공지 테이블의 게시글 번호에 해당되는 공지 게시글 한개를 반환한다
	 * @param teamNo 팀번호
	 * @param noticeNo 공지게시글 번호
	 * @return 게시글
	 * @throws FindException DB연결 실패할 시 예외 발생한다
	 */
	NoticeDTO findByNoticeNo(Integer teamNo, Integer noticeNo) throws FindException;
}
