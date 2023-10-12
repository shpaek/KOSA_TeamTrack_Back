package com.my.qna.service;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.qna.dto.QnaBoardCommentDTO;
import com.my.qna.dto.QnaBoardDTO;
import com.my.util.PageGroup;

public interface QnaBoardCommentService {

	/**
	 * 댓글을 작성한다
	 * @param teamNo 팀번호
	 * @param dto 댓글작성 데이터
	 * @throws AddException 
	 */
	public void insert(Integer teamNo, QnaBoardCommentDTO dto) throws AddException;
	
	public void insertReply (Integer teamNo, QnaBoardCommentDTO dto) throws AddException;
	
	/**
	 * 해당 팀의 qna게시글 번호에 맞는 현재페이지에 있는 댓글을 조회한다
	 * @param teamNo 팀번호
	 * @param qnaNo 게시글 번호
	 * @param currentPage 현재페이지
	 * @return 현재페이지의 댓글
	 * @throws FindException
	 */
//	public PageGroup<QnaBoardCommentDTO> selectCommentByQnaNo(Integer teamNo, Integer qnaNo, int currentPage) throws FindException;
	public List<QnaBoardCommentDTO> selectCommentByQnaNo(Integer teamNo, Integer qnaNo) throws FindException;
	
	public List<QnaBoardCommentDTO> selectCommentReply(Integer teamNo, QnaBoardCommentDTO dto) throws FindException;

	/**
	 * 댓글을 채택한다
	 * @param teamNo 팀번호
	 * @param qnaNo 게시글 번호
	 * @param commentNo 댓글 번호
	 * @return 댓글을 채택해서 바뀐 pickkeddate 반환
	 * @throws ModifyException
	 */
	public Integer commentPick(Integer teamNo, Integer qnaNo, Integer commentNo) throws ModifyException;
	
} // end interface
