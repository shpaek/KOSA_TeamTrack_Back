package com.my.qna.dao;

import com.my.exception.AddException;
import com.my.qna.dto.QnaBoardCommentDTO;

public interface QnaBoardCommentDAO {

	public Integer insertComment (QnaBoardCommentDTO dto) throws AddException; 
	
}
