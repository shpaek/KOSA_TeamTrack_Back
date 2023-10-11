package com.my.qna.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.qna.dto.QnaBoardCommentDTO;

public interface QnaBoardCommentDAO {

	/**
	 * 댓글을 작성한다
	 * @param teamNo 팀번호
	 * @param dto 게시글 작성 데이터
	 * @throws AddException  DB와의 연결 실패 또는 제약조건에 위반시 예외 발생
	 */
	public void insertComment (Integer teamNo, QnaBoardCommentDTO dto) throws AddException; 
	
	/**
	 * 해당 팀의 qna게시글번호에 해당하는 댓글 전체를 불러온다
	 * @param teamNo 팀번호
	 * @param qnaNo 게시글 번호
	 * @param startRow 시작행
	 * @param endRow 끝행
	 * @return 댓글
	 * @throws FindException DB와의 연결 실패 또는 게시글 없으면 예외 발생
	 */
	public List<QnaBoardCommentDTO> selectCommentByQnaNo(Integer teamNo, Integer qnaNo, int startRow, int endRow ) throws FindException;
	
	/**
	 * 해당 팀의 qna게시글 번호에 해당하는 댓글의 갯수를 획득
	 * @param teamNo 팀번호
	 * @param qnaNo 게시글 번호
	 * @return 게시글 갯수
	 * @throws FindException DB와의 연결 실패 또는 게시글 없으면 예외 발생
	 */
	public Integer selectAllCount(Integer teamNo, Integer qnaNo) throws FindException;
	
	/**
	 * 답변을 채택한다
	 * @param teamNo 팀번호
	 * @param qnaNo 게시글 번호
	 * @param commentNo 게시글의 댓글 번호
	 * @return 채택여부
	 * @throws ModifyException DB와의 연결 실패 또는 댓글이 없거나 commentNo값이 일치하지 않으면 예외 발생
	 */
	public Integer commentPick(Integer teamNo, Integer qnaNo, Integer commentNo) throws ModifyException;
	
} // end class
