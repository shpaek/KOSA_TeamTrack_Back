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
	public Integer delete(Integer qna_no) throws RemoveException {
		
		SqlSession session = null;
		
		try {
			
			session = sqlSessionFactory.openSession();
			session.delete("com.my.customer.QnaBoardMapper.delete", qna_no);
			
			session.commit();
			
		} catch(Exception e) {
			session.rollback();
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		} finally {
			if(session!=null) {
				session.close();
			}
		} // try-catch-finally
		
		return null;
	} // delete
	
	// ===================  메서드 테스트 =======================
	public static void main(String[] args) {
		
		// ================== create 메서드 =====================
		
		QnaBoardDAOImpl impl = new QnaBoardDAOImpl();
		
		QnaBoardDTO dto = new QnaBoardDTO();

		dto.setId("게시판test6");
		dto.setTitle("tset입니다6");
		dto.setContent("test입니다6");
		
		try {
			impl.create(dto);
			System.out.println("게시물 등록 완료");
		} catch (AddException e) {
			 System.out.println("게시물 등록 실패");
			e.printStackTrace();
		}
		
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
		
	} // main(test)
	
} // end class
