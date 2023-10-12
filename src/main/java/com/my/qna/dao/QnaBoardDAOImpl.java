package com.my.qna.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.qna.dto.QnaBoardDTO;

public class QnaBoardDAOImpl implements QnaBoardDAO {
	
	// Mybatis에서 db와 연결하고 sql문을 실행 할 SqlSessionFactory 인터페이스 선언
	private SqlSessionFactory sqlSessionFactory;

	public QnaBoardDAOImpl() {
		
		// Mybatis 설정파일 로드
		String resource = "com/my/sql/mybatis-config.xml";
		InputStream inputStream;
		
		try {
			
			// 리소스 경로에 파일 읽어들이는 클래스(Resources)
			inputStream = Resources.getResourceAsStream(resource);
			
			// sqlSessionFactory를 멤버변수로 만듦
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} // try-catch
		
	} // constructor
	

	@Override
	public void create(QnaBoardDTO qnaBoardDTO) throws AddException {
		
		SqlSession session = null;
		
		try {
			
			session = sqlSessionFactory.openSession();
			session.insert("com.my.customer.QnaBoardMapper.create", qnaBoardDTO);
			
			session.commit();
			
		} catch(Exception e) {
			session.rollback();
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			if(session!=null) {
				session.close();
			}
		} // try-catch-finally

	} // create

	@Override
	public List<QnaBoardDTO> selectAll(Integer teamNo, int startRow, int endRow) throws FindException {

		SqlSession session = null;
		
		List<QnaBoardDTO> qnaList = new ArrayList<>();
		
		String tableName = "QNABOARD_"+ String.valueOf(teamNo);
		
		try{
			session = sqlSessionFactory.openSession();
			Map map = new HashMap<>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("tableName", tableName);
			qnaList = session.selectList("com.my.notice.QnaBoardMapper.selectAll", map);
			return qnaList;
			
		}catch(Exception e) {
			
			e.printStackTrace();
			throw new FindException(e.getMessage());
			
		}finally {
			if(session!=null) {
				session.close();
			}
		} // try-catch-finally

	} // selectAll

	@Override
	public Integer selectAllCount(Integer teamNo) throws FindException {

		SqlSession session = null;
		
		try {
			session=sqlSessionFactory.openSession();
			String tableName="task_"+teamNo;
			int count=session.selectOne("com.my.qna.QnaBoardMapper.selectAllCount", tableName);
			return count;
		} catch(Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			if(session!=null) {
				session.close();				
			}
		} // try-catch-finally

	}


	@Override
	public Integer update(QnaBoardDTO qnaBoardDTO) throws ModifyException {
		
		SqlSession session = null;
		
		try {
			
			session = sqlSessionFactory.openSession();
			session.update("com.my.customer.QnaBoardMapper.update", qnaBoardDTO);
			
			session.commit();
			
		} catch(Exception e) {
			session.rollback();
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		} finally {
			if(session!=null) {
				session.close();
			}
		} // try-catch-finally

		return null;
	} // update

	@Override
	public Integer delete(Integer teamNo, Integer qnaNo) throws ModifyException {

		SqlSession session = null;
		
		Map<String, Object> map = new HashMap<>();

		try {
			
			String tableName="QNABOARD_" + teamNo;
			
			map.put("tableName", tableName);
			map.put("qnaNo", qnaNo);

			session = sqlSessionFactory.openSession();
			int result = session.delete("com.my.qna.QnaBoardMapper.delete", map);
			
			session.commit();

			return result; // 삭제된 행 수 반환
			
		} catch(Exception e) {
			session.rollback();
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		} finally {
			if(session!=null) {
				session.close();
			}
		} // try-catch-finally
	} // delete
	
	// ===================  메서드 테스트 =======================
	public static void main(String[] args) {
		
		// ================== create 메서드 =====================
		
//		QnaBoardDAOImpl impl = new QnaBoardDAOImpl();
//		
//		QnaBoardDTO dto = new QnaBoardDTO();
//
//		dto.setId("test01");
//		dto.setTitle("tset입니다8");
//		dto.setContent("test입니다8");
//		
//		try {
//			impl.create(dto);
//			System.out.println("게시물 등록 완료");
//		} catch (AddException e) {
//			 System.out.println("게시물 등록 실패");
//			e.printStackTrace();
//		}
		
		// ================== update 메서드 =====================
		
//		QnaBoardDAOImpl impl = new QnaBoardDAOImpl();
//		
//		QnaBoardDTO dto = new QnaBoardDTO();
//		
//		dto.setTitle("수정");
//		dto.setContent("수정");
//		dto.setQna_no(31);
//		
//		try {
//			impl.update(dto);
//			System.out.println("게시물 수정 성공");
//		} catch (ModifyException e) {
//			System.out.println("게시물 수정 실패");
//			e.printStackTrace();
//		} // try-catch
		
		// ================== delete 메서드 =====================
		
//		QnaBoardDAOImpl impl = new QnaBoardDAOImpl();
//		
//		QnaBoardDTO dto = new QnaBoardDTO();
//
//		int qna_no = 31;
//		
//		try {
//			impl.delete(qna_no);
//			System.out.println("게시물 삭제 성공");
//		} catch (RemoveException e) {
//			System.out.println("게시물 삭제 실패");
//			e.printStackTrace();
//		} // try-catch
		
		// =============== selectAll 메서드 테스트 ======================
		
//	    QnaBoardDAO dao = new QnaBoardDAOImpl(); // DAO 객체 생성
//
//	    int teamNo = 9999; // 팀 번호 (원하는 팀 번호로 설정)
//	    int currentPage = 2; // 가져올 페이지 번호 (1페이지)
//
//	    try {
//	        // selectAll 메서드 호출
//	        List<QnaBoardDTO> qnaList = dao.selectAll(teamNo, currentPage, currentPage);
//
//	        // 결과 출력
//	        for (QnaBoardDTO qna : qnaList) {
////	            System.out.println("게시글 번호: " + qna.getQna_no());
//	            System.out.println("게시글 제목: " + qna.getTitle());
//	            // 필요한 정보들을 출력하거나 활용할 수 있습니다.
//	        }
//	    } catch (FindException e) {
//	        System.out.println("게시글 조회 실패");
//	        e.printStackTrace();
//	    }
		
		// =============== selectByQnaNo 메서드 테스트 ======================
        // 테스트를 위한 팀 번호와 게시글 번호 설정
		
//	    QnaBoardDAO dao = new QnaBoardDAOImpl(); // DAO 객체 생성
//		
//        Integer teamNo = 9999;
//        Integer qnaNo = 33; // 테스트할 게시글 번호
//        
//        try {
//			QnaBoardDTO dto = dao.selectByQnaNo(teamNo, qnaNo);
//			
//			System.out.println("dto : " + dto);
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
        
        // ============= delete 메서드 테스트 =======================
        
	    QnaBoardDAO dao = new QnaBoardDAOImpl(); // DAO 객체 생성
		
        Integer teamNo = 65; // 팀 번호
        Integer qnaNo = 115; // 게시글 번호
        
        try {
			int result = dao.delete(teamNo, qnaNo);
			
			System.out.println("result : " + result);
			System.out.println(" 삭제 되었습니다 ");
			
		} catch (ModifyException e) {
			
			e.printStackTrace();
			System.out.println(" 삭제 실패 ");
			
		}
		
	} // main(test)
	
} // end class
