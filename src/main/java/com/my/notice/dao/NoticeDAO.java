package com.my.notice.dao;

import java.util.List;

import com.my.exception.FindException;
import com.my.notice.dto.NoticeDTO;

public interface NoticeDAO {
	/**
	 * 상품을 오름차순 정렬된 공지 게시글들 중 시작행에서부터 끝행까지 상품들을 조회한다
	 * @param startRow 시작행
	 * @param endRow 마지막행
	 * @param teamNo 팀번호
	 * @return 게시글들
	 * @throws FindException DB와의 연결 실패 시 예외 발생
	 */
	List<NoticeDTO> selectNoticeAll(int startRow, int endRow, Integer teamNo) throws FindException;
	
	/**
	 * 전체 게시글 수를 조회한다
	 * @param teamNo 팀번호
	 * @return 전체 게시글 수
	 * @throws FindException 게시글이 없거나 DB와의 연결이 실패하면 예외 발생한다
	 */
	int selectNoticeCount(Integer teamNo) throws FindException;
	
	NoticeDTO selectByNoticeNo(Integer teamNo, Integer noticeNo) throws FindException;
}
