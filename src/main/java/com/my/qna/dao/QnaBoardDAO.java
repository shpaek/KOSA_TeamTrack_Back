package com.my.qna.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.qna.dto.QnaBoardDTO;

public interface QnaBoardDAO {

	// 게시글 생성
	public void create(QnaBoardDTO qnaBoardDTO) throws AddException;
	
	// 게시글 전체 목록 조회 (페이징처리x)
	public List<QnaBoardDTO> selectAll(Integer qna_no) throws FindException;
	
	// 게시글 수정
	public Integer update(Integer qna_no) throws ModifyException;
	
	// 게시글 삭제
	public Integer delete(Integer qna_no) throws RemoveException;
	
} // end class
