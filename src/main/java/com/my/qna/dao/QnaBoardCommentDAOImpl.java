package com.my.qna.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.exception.AddException;
import com.my.qna.dto.QnaBoardCommentDTO;

public class QnaBoardCommentDAOImpl implements QnaBoardCommentDAO {

	// Mybatis에서 db와 연결하고 sql문을 실행 할 SqlSessionFactory 인터페이스 선언
	private SqlSessionFactory sqlSessionFactory;

	public QnaBoardCommentDAOImpl() {

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
	public Integer insertComment(QnaBoardCommentDTO dto) throws AddException {

		SqlSession session = null;

		try {
			
			Map<String, Object> map = new HashMap<>();
			
//			String tableName = "QNABOARD_"+ String.valueOf(teamNo);
			
//			map.put("tableName", tableName);
			map.put("qna_no", dto.getQnaNo());
			map.put("title", dto.getContent());
			map.put("content", dto.getTeammemberId());

			session = sqlSessionFactory.openSession();
			session.insert("com.my.qna.QnaboardCommentMapper.insertComment", map);
			
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
		return null;

	} // insertComment
	
	public static void main(String[] args) {
		
	} // main(test)

} // end class
