package com.my.member.dao;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.exception.FindException;
import com.my.member.dto.Emp;

public class empDAOImpl implements empDAO {
	
	private SqlSessionFactory sqlSessionFactory;
	
	public empDAOImpl() {
		
		String resource = "com/my/sql/mybatis-config.xml";
		InputStream inputStream;
		try {
			
			inputStream = Resources.getResourceAsStream(resource);
			
			// sqlSessionFactory를 멤버변수로 만듦
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Emp select(Integer id) throws FindException {

//		String resource = "org/mybatis/example/mybatis-config.xml";
//		InputStream inputStream; 생성자로 옮김
		SqlSession session = null;
		
		try {
			
//			inputStream = Resources.getResourceAsStream(resource); 생성자로 옮김
//			SqlSessionFactory sqlSessionFactory =
//			new SqlSessionFactoryBuilder().build(inputStream);
			
			session = sqlSessionFactory.openSession();
			// selectOne의 인자를 mapper의 namespace 부분과
			// select id를 넣음
			System.out.println("id : " + id);
			Emp c = (Emp)session.selectOne("com.my.member.EmpMapper.select", id);
			
			System.out.println("Emp c : " + c);
			
			if(c != null) {
				return c;
			} else {
				// select한 결과(id가 없다)가 null이면
				throw new FindException("고객없음");	
			} // if-else
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		} // try-catch-finally
		
	} // selectById
	
	
	// ============================== db연결 테스트 =================================

	public static void main(String[] args) {
	    // empDAO 객체 생성
	    empDAO empDao = new empDAOImpl();

	    try {

	        Integer targetId = 2;

	        Emp emp = empDao.select(targetId);

	        // 조회 결과 출력
	        if (emp != null) {
	            System.out.println("사용자 ID: " + emp.getEmp_id());
	            System.out.println("사용자 이름: " + emp.getEmp_name());

	        } else {
	            System.out.println("사용자가 존재하지 않습니다.");
	        }

	    } catch (FindException e) {

	        System.err.println("사용자 조회 오류: " + e.getMessage());
	        e.printStackTrace();
	    }// try-catch
	} // main
	
} // end class


