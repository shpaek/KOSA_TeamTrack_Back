package com.my.qna.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.qna.dto.QnaBoardDTO;

public class QnaBoardDAOImpl implements QnaBoardDAO {

	@Override
	public void create(QnaBoardDTO qnaBoardDTO) throws AddException {

	} // create

	@Override
	public List<QnaBoardDTO> selectAll(Integer qna_no) throws FindException {

		return null;
	} // selectAll

	@Override
	public Integer update(Integer qna_no) throws ModifyException {

		return null;
	} // update

	@Override
	public Integer delete(Integer qna_no) throws RemoveException {

		return null;
	} // delete

} // end class
