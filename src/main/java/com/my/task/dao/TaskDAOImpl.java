package com.my.task.dao;

import java.io.InputStream;
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
import com.my.task.dto.MemberTask;
import com.my.task.dto.Task;

public class TaskDAOImpl implements TaskDAO {
	private SqlSessionFactory sqlSessionFactory;

	public TaskDAOImpl() {
		String resource="com/my/sql/mybatis-config.xml";
		InputStream inputStream;

		try {
			inputStream=Resources.getResourceAsStream(resource);
			sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Task> selectAllTaskList(Integer teamNo, int startRow, int endRow, boolean desc) throws FindException {
		SqlSession session=null;

		try {
			session=sqlSessionFactory.openSession();
			Map<String, Object> map=new HashMap<>();
			map.put("tableName", "task_"+teamNo);
			map.put("desc", desc);
			map.put("start", startRow);
			map.put("end", endRow);
			List<Task> list=session.selectList("com.my.task.TaskMapper.selectAllTaskList", map);
			return list;
		} catch(Exception e) {
			throw new FindException("전체 과제 리스트 페이지 조회 실패");
		} finally {
			if(session!=null) session.close();
		}
	}

	public int selectAllTaskCount(Integer teamNo) throws FindException {
		SqlSession session=null;

		try {
			session=sqlSessionFactory.openSession();
			String tableName="task_"+teamNo;
			int count=session.selectOne("com.my.task.TaskMapper.selectAllTaskCount", tableName);
			return count;
		} catch(Exception e) {
			throw new FindException("전체 과제 개수 조회 실패");
		} finally {
			if(session!=null) session.close();
		}
	}

	public List<MemberTask> selectCompleteTaskList(Integer teamNo, String id, int startRow, int endRow, boolean desc) throws FindException {
		SqlSession session=null;

		try {
			session=sqlSessionFactory.openSession();
			Map<String, Object> map=new HashMap<>();
			map.put("tableName1", "memberscore_"+teamNo);
			map.put("tableName2", "task_"+teamNo);
			map.put("id", id);
			map.put("desc", desc);
			map.put("start", startRow);
			map.put("end", endRow);
			List<MemberTask> list=session.selectList("com.my.task.TaskMapper.selectCompleteTaskList", map);
			return list;
		} catch(Exception e) {
			throw new FindException("완료한 과제 리스트 페이지 조회 실패");
		} finally {
			if(session!=null) session.close();
		}
	}

	public int selectCompleteTaskCount(Integer teamNo, String id) throws FindException {
		SqlSession session=null;

		try {
			session=sqlSessionFactory.openSession();
			Map<String, Object> map=new HashMap<>();
			map.put("tableName", "memberscore_"+teamNo);
			map.put("id", id);
			int count=session.selectOne("com.my.task.TaskMapper.selectCompleteTaskCount", map);
			return count;
		} catch(Exception e) {
			throw new FindException("완료한 과제 개수 조회 실패");
		} finally {
			if(session!=null) session.close();
		}
	}
	
	public List<Task> selectMyTaskList(Integer teamNo, String id, int startRow, int endRow, boolean desc) throws FindException {
		SqlSession session=null;

		try {
			session=sqlSessionFactory.openSession();
			Map<String, Object> map=new HashMap<>();
			map.put("tableName", "task_"+teamNo);
			map.put("id", id);
			map.put("desc", desc);
			map.put("start", startRow);
			map.put("end", endRow);
			List<Task> list=session.selectList("com.my.task.TaskMapper.selectMyTaskList", map);
			return list;
		} catch(Exception e) {
			throw new FindException("출제한 과제 리스트 페이지 조회 실패");
		} finally {
			if(session!=null) session.close();
		}
	}
	
	public int selectMyTaskCount(Integer teamNo, String id) throws FindException {
		SqlSession session=null;

		try {
			session=sqlSessionFactory.openSession();
			Map<String, Object> map=new HashMap<>();
			map.put("tableName", "task_"+teamNo);
			map.put("id", id);
			int count=session.selectOne("com.my.task.TaskMapper.selectMyTaskCount", map);
			return count;
		} catch(Exception e) {
			throw new FindException("출제한 과제 개수 조회 실패");
		} finally {
			if(session!=null) session.close();
		}
	}


	// main test
	public static void main(String[] args) {
		TaskDAOImpl t=new TaskDAOImpl();
		try {
			System.out.println("======================\n전체과제리스트");
			int cnt=t.selectAllTaskCount(9999);
			System.out.println(cnt);
			List<Task> list=t.selectAllTaskList(9999, 1, 3, true);
			for(int i=0;i<list.size();i++) System.out.println(list.get(i).getTitle()+":"+list.get(i).getId());
			System.out.println("======================\n완료과제리스트");
			int cnt2=t.selectCompleteTaskCount(9999, "nwh2023");
			System.out.println(cnt2);
			List<MemberTask> list2=t.selectCompleteTaskList(9999, "nwh2023", 1, 2, true);
			for(int i=0;i<list2.size();i++) System.out.println(list2.get(i).getTitle()+":"+list2.get(i).getId()+":"+list2.get(i).getHwScore());
			System.out.println("======================\n출제과제리스트");
			int cnt3=t.selectMyTaskCount(9999, "khb2023");
			System.out.println(cnt3);
			List<Task> list3=t.selectMyTaskList(9999, "khb2023", 1, 2, true);
			for(int i=0;i<list3.size();i++) System.out.println(list3.get(i).getTitle()+":"+list3.get(i).getAvgReviewscore());
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
