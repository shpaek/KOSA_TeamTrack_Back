package com.my.qna.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.qna.dto.QnaBoardDTO;

public interface QnaBoardDAO {

	public void create(QnaBoardDTO qnaBoardDTO) throws AddException;
	
	public List<QnaBoardDTO> selectAll(Integer qna_no) throws FindException;
	
	public Integer update(Integer qna_no) throws ModifyException;
	
	public Integer delete(Integer qna_no) throws RemoveException;
	
} // end class
