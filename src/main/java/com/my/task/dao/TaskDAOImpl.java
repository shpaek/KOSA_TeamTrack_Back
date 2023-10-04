package com.my.task.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.sql.MyConnection;
import com.my.task.dto.MemberTask;
import com.my.task.dto.Task;

public class TaskDAOImpl implements TaskDAO {

	public List<Task> selectAllTaskList(Integer teamNo, int startRow, int endRow) throws FindException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=MyConnection.getConnection();
		} catch (Exception e) {
			// e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		
		String selectAllTaskListSQL="SELECT t.task_no, t.id, t.title\n"
									+ "FROM ( SELECT rownum rn, a.*\n"
									+ "	FROM ( SELECT *\n"
									+ "		FROM task_"+teamNo+"\n"
									+ "		ORDER BY regdate DESC\n"
									+ "		) a\n"
									+ "	) t\n"
									+ "WHERE rn BETWEEN ? AND ?";
		List<Task> list=new ArrayList<>();
		
		try {
			pstmt=conn.prepareStatement(selectAllTaskListSQL);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Task task=new Task();
				task.setTaskNo(rs.getInt("task_no"));
				task.setTaskId(rs.getString("id"));
				task.setTitle(rs.getString("title"));
				list.add(task);
			}
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new FindException("전체 과제 리스트 페이지 조회 실패");
		} finally {
			MyConnection.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public int selectAllTaskCount(Integer teamNo) throws FindException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=MyConnection.getConnection();
		} catch (Exception e) {
			// e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		
		String selectAllTaskCountSQL="SELECT COUNT(*) FROM task_"+teamNo;
		
		try {
			pstmt=conn.prepareStatement(selectAllTaskCountSQL);
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			 //e.printStackTrace();
			 throw new FindException("전체 과제 개수 조회 실패");
		} finally {
			MyConnection.close(conn, pstmt, rs);
		}
	}
	
	public static void main(String[] args) {
		TaskDAOImpl t=new TaskDAOImpl();
		try {
			int cnt=t.selectAllTaskCount(9999);
			System.out.println(cnt);
			List<Task> list=t.selectAllTaskList(9999, 1, 2);
			for(int i=0;i<list.size();i++) System.out.println(list.get(i).getTitle()+":"+list.get(i).getTaskId());
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
