package com.my.qna.service;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.qna.dao.QnaBoardCommentDAO;
import com.my.qna.dao.QnaBoardCommentDAOImpl;
import com.my.qna.dto.QnaBoardCommentDTO;
import com.my.util.PageGroup;

public class QnaBoardCommentServiceImpl implements QnaBoardCommentService {
	
	private QnaBoardCommentDAO qnaBoardCommentDAO;

	private static QnaBoardCommentServiceImpl  service = new QnaBoardCommentServiceImpl();

	private QnaBoardCommentServiceImpl() {
		qnaBoardCommentDAO = new QnaBoardCommentDAOImpl();
	}

	public static QnaBoardCommentServiceImpl getInstance() {
		return service;
	}

	@Override
	public void insert(Integer teamNo, QnaBoardCommentDTO qnaBoardCommentDTO) throws AddException {
		
		qnaBoardCommentDAO.insertComment(teamNo, qnaBoardCommentDTO);
		
	}

	@Override
	public PageGroup<QnaBoardCommentDTO> selectCommentByQnaNo(Integer teamNo, Integer qnaNo, int currentPage) throws FindException {
		
		if(currentPage <1) {
			currentPage = 1;
		}

		int cntPerPage = 10; //한페이지당 보여줄 목록 수

		int startRow = 0;
		int endRow = 0;

		startRow = (currentPage -1)*cntPerPage +1;
		endRow = currentPage*cntPerPage;
		
		System.out.println("service teamNO ============> " + teamNo);

		List<QnaBoardCommentDTO> list = qnaBoardCommentDAO.selectCommentByQnaNo(teamNo, qnaNo, startRow, endRow);

		int totalCnt = qnaBoardCommentDAO.selectAllCount(teamNo, qnaNo);
		
//	    for (QnaBoardDTO dto : list) {
//	        System.out.println("qna_no: " + dto.getQna_no());
//	    }
		
		PageGroup<QnaBoardCommentDTO> pg = new PageGroup<>(list, currentPage, totalCnt); 
		
		return pg;
		
	} // selectCommentByQnaNo
	
	@Override
	public Integer commentPick(Integer teamNo, Integer qnaNo, Integer commentNo) throws ModifyException {

		int result = qnaBoardCommentDAO.commentPick(teamNo, qnaNo, commentNo);
		
		return result;
	
	} // commentPick
	
	
	
	
	
	// ================= selectCommentByQnaNo 메서드 테스트 ===================
	

	public static void main(String[] args) {

//	    QnaBoardCommentDAO dao = new QnaBoardCommentDAOImpl();
//		
//		// 팀 번호와 QnA 번호를 지정합니다.
//	    Integer teamNo = 9999;
//	    Integer qnaNo = 64;
//	    int currentPage = 1;
//
//        try {
//
//            PageGroup<QnaBoardCommentDTO> result = service.selectCommentByQnaNo(teamNo, qnaNo, currentPage);
//
//            // 페이지 정보 출력
//            System.out.println("=======> Total Pages: " + result.getTotalPage());
//            System.out.println("=======> Current Page: " + result.getCurrentPage());
//            System.out.println("=======> Total Count: " + result.getTotalCnt());
//
//        } catch (FindException e) {
//            e.printStackTrace();
//        } 

		
		// =================== commentPick 메서드 테스트 =============================
		
	    QnaBoardCommentService service = QnaBoardCommentServiceImpl.getInstance();

	    Integer teamNo = 64;
	    Integer qnaNo = 112;
	    Integer commentNo = 4;

	    try {
	        Integer result = service.commentPick(teamNo, qnaNo, commentNo);

	        if (result > 0) {
	            System.out.println("댓글 채택 성공!");
	        } else {
	            System.out.println("댓글 채택 실패!");
	        }
	    } catch (ModifyException e) {
	        e.printStackTrace();
	    }
		
		
	} // main(test)

} // end class
