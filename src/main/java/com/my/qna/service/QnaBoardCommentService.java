package com.my.qna.service;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.qna.dto.QnaBoardCommentDTO;
import com.my.qna.dto.QnaBoardDTO;
import com.my.util.PageGroup;

public interface QnaBoardCommentService {

	public void insert(Integer teamNo, QnaBoardCommentDTO qnaBoardCommentDTO) throws AddException;
	
	public PageGroup<QnaBoardCommentDTO> selectCommentByQnaNo(Integer teamNo, Integer qnaNo, int currentPage) throws FindException;

} // end interface
